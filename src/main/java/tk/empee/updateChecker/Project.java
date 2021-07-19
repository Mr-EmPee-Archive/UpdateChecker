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
            latestManifest = getLatestManifest(projectManifest.getMainAttributes().getValue("Latest-ManifestURL"));
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

    private Manifest getLatestManifest(String urlString) throws MalformedURLException {

        try {
            URL latestManifestURL = new URL(urlString);
            return new Manifest(latestManifestURL.openStream());
        } catch (IOException e) {
            throw new MalformedURLException("The latest manifest URL is malformed");
        }

    }

    public String getName() {
        return projectJar.getName();
    }
    public String getCurrentVersion() {
        return currentVersion;
    }

    public Update isOutdated() {

        Update update = null;

        if(!(Version.compare(currentVersion, latestVersion) < 0)) {
            update = new Update(latestVersion, latestJarDownloadURL);
            update.loadChangelogs(changelogFolderURl, changelogNamingConvention);
        }

        return update;

    }

    public String toString() {
        return getName() + " (" + getCurrentVersion() +  ")";
    }

}
