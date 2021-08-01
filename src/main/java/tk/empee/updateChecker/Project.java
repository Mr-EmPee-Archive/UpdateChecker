package tk.empee.updateChecker;

import tk.empee.updateChecker.utils.Version;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Project {

    private final JarFile projectJar;

    private final String currentVersion;
    private final String latestVersion;

    private final URL changelogFolderURl;
    private final String changelogNamingConvention;

    private final URL latestJarDownloadURL;

    /**
     * @throws IOException if it occurs a problem while parsing a manifest
     * it will be thrown a general IOException. The cause can be retrieved using #getCause()
     */
    public Project(File project) throws IOException {
        projectJar = new JarFile(project);

        Manifest latestManifest;
        try {
            Manifest projectManifest = projectJar.getManifest();
            currentVersion = projectManifest.getMainAttributes().getValue("Specification-Version");
            latestManifest = getManifestFromURL(projectManifest.getMainAttributes().getValue("Latest-ManifestURL"));
        } catch (Exception e) {
            throw new IOException("Error while parsing the jar manifest", e);
        }

        try {

            latestVersion = latestManifest.getMainAttributes().getValue("Specification-Version");
            changelogFolderURl = new URL(latestManifest.getMainAttributes().getValue("Changelog-FolderURL"));
            changelogNamingConvention = latestManifest.getMainAttributes().getValue("Changelog-NamingConvention");
            latestJarDownloadURL = new URL(latestManifest.getMainAttributes().getValue("Jar-DownloadURL"));

        } catch (Exception e) {
            throw new IOException("Error while parsing the latest manifest", e);
        }

    }

    private Manifest getManifestFromURL(String urlString) throws MalformedURLException {

        try {
            URL manifestURL = new URL(urlString);
            return new Manifest(manifestURL.openStream());
        } catch (IOException e) {
            throw new MalformedURLException("The manifest URL is malformed");
        }

    }

    public String getName() {
        return projectJar.getName();
    }
    public String getCurrentVersion() {
        return currentVersion;
    }
    public String getLatestVersion() { return latestVersion; }
    public URL getLatestJarDownloadURL() { return latestJarDownloadURL; }

    public Update isOutdated() {

        Update update = null;

        if(Version.compare(currentVersion, latestVersion) < 0) {
            update = new Update(this, buildLatestChangelogURL());
        }

        return update;

    }

    private URL buildLatestChangelogURL() {

        String[] version = latestVersion.split("\\.");
        String fileName = changelogNamingConvention
                .replace("x", version[0])
                .replace("y", version[1])
                .replace("z", version[2]);

        try {
            return new URL(changelogFolderURl, fileName);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error while retrieving the latest changelog", e);
        }

    }

    public String toString() {
        return getName() + " (" + getCurrentVersion() +  ")";
    }
}
