import tk.empee.updateChecker.Project;
import tk.empee.updateChecker.UpdateChecker;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class DemoApp {

    private static UpdateChecker updateChecker = UpdateChecker.getInstance();

    public static void main(String[] args) {

        System.out.println(" --- Starting application");

        File testJar = null;
        try {
            testJar = new File(DemoApp.class.getResource("testJar.jar").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        assert testJar != null;

        try {
            updateChecker.registerProject(new Project(testJar));
            updateChecker.checkUpdates();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(" --- Ending application");

    }

}
