package tk.empee.updateChecker;

import tk.empee.updateChecker.utils.Version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

public class Update {

    private final List<String> bugFixes = new ArrayList<>();
    private final List<String> otherInfos = new ArrayList<>();

    private final String latestVersion;
    private final URL changelogFolderURl;
    private final String changelogNamingConvention;

    private final URL latestJarDownloadURL;

    private final Project project;

    /**
     * @throws RuntimeException if it occurs a problem while parsing a manifest.
     * The cause can be retrieved using #getCause()
     */
    Update(Project project) {
        this.project = project;

        try {
            Manifest latestManifest = getManifestFromURL(project.getLatestManifestURL());
            latestVersion = latestManifest.getMainAttributes().getValue("Specification-Version");
            changelogFolderURl = new URL(latestManifest.getMainAttributes().getValue("Changelog-FolderURL"));
            changelogNamingConvention = latestManifest.getMainAttributes().getValue("Changelog-NamingConvention");
            latestJarDownloadURL = new URL(latestManifest.getMainAttributes().getValue("Jar-DownloadURL"));

            if(isOutdated()) {
                loadChangelog(buildChangelogURL(latestVersion));
            }

        } catch (Exception e) {
            throw new RuntimeException("Error while parsing the latest manifest", e);
        }

    }

    private static Manifest getManifestFromURL(String urlString) throws MalformedURLException {

        try {
            URL manifestURL = new URL(urlString);
            return new Manifest(manifestURL.openStream());
        } catch (IOException e) {
            throw new MalformedURLException("The manifest URL is malformed");
        }

    }

    private URL buildChangelogURL(String version) throws MalformedURLException {
        String[] splittedVersion = version.split("\\.");
        return buildChangelogURL(splittedVersion[0], splittedVersion[1], splittedVersion[2]);
    }
    private URL buildChangelogURL(String x, String y, String z) throws MalformedURLException {

        String fileName = changelogNamingConvention
                .replace("x", x)
                .replace("y", y)
                .replace("z", z);

        return new URL(changelogFolderURl, fileName);

    }

    public boolean isOutdated() {
        return Version.compare(project.getCurrentVersion(), latestVersion) < 0;
    }

    public String getLatestVersion() {
        return latestVersion;
    }
    public URL getDownloadURL() {
        return latestJarDownloadURL;
    }
    public List<String> getBugFixes() {
        return bugFixes;
    }
    public List<String> getOtherInfos() {
        return otherInfos;
    }

    public void loadChangelog(URL changelogURL) {

        String buffer, oldBuffer = null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(changelogURL.openStream()))) {

            while((buffer = reader.readLine()) != null || oldBuffer != null) {

                if(oldBuffer != null) {
                    if (buffer == null || !buffer.startsWith("\t")) {
                        logMessage(oldBuffer);
                    } else {
                        buffer = oldBuffer + " " + buffer;
                    }
                }

                oldBuffer = buffer;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void logMessage(String message) {

        if(message.startsWith("!")) {
            bugFixes.add(message);
        } else {
            otherInfos.add(message);
        }

    }

}
