import tk.empee.updateChecker.Project;
import tk.empee.updateChecker.UpdateChecker;

import java.io.File;
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
        System.out.println("The jar file 'testJar exists'");

        updateChecker.registerProject(new Project(testJar));
        updateChecker.checkUpdates();

        System.out.println(" --- Ending application");

    }

}
