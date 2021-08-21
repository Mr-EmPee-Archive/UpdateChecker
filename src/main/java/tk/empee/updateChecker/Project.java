package tk.empee.updateChecker;

import java.io.File;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Project {

    private final JarFile projectJar;

    private final String currentVersion;
    private final String latestManifestURL;


    /**
     * @param project It is the file that represent the project's jar
     * @throws RuntimeException if it occurs a problem while parsing a manifest.
     * The cause can be retrieved using #getCause()
     */
    public Project(File project) {

        try {
            projectJar = new JarFile(project);
            Manifest projectManifest = projectJar.getManifest();
            currentVersion = projectManifest.getMainAttributes().getValue("Specification-Version");
            latestManifestURL = projectManifest.getMainAttributes().getValue("Latest-ManifestURL");
        } catch (Exception e) {
            throw new RuntimeException("Error while parsing the jar manifest", e);
        }

    }

    String getLatestManifestURL() {return  latestManifestURL;}

    public String getName() {
        return projectJar.getName();
    }
    public String getCurrentVersion() {
        return currentVersion;
    }

    /**
     * Whenever you call this method the updater will download some files
     */
    public Update isOutdated() {

        Update update = new Update(this);

        if(update.isOutdated()) {
            return update;
        }

        return null;
    }

    public String toString() {
        return getName() + " (" + getCurrentVersion() +  ")";
    }
}
