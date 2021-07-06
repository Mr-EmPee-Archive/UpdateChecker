package tk.empee.updateChecker;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Project {

    private final JarFile projectJar;
    private final String currentVersion;

    /**
     * @throws IOException if an I/O error has occurred
     * @throws SecurityException if access to the file is denied by the SecurityManager
     */
    public Project(File project) throws IOException {
        projectJar = new JarFile(project);

        Manifest projectManifest = projectJar.getManifest();
        currentVersion = projectManifest.getMainAttributes().getValue("Implementation-Version");
    }

    public String getName() {
        return projectJar.getName();
    }
    public String getCurrentVersion() {
        return currentVersion;
    }

    public String toString() {
        return getName() + " (" + getCurrentVersion() +  ")";
    }

}
