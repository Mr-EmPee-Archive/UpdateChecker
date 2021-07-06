package tk.empee.updateChecker;

import java.util.ArrayList;
import java.util.List;

public class UpdateChecker {
    private static UpdateChecker instance;
    public static UpdateChecker getInstance() {

        if(instance == null) {
            instance = new UpdateChecker();
        }

        return instance;
    }


    private final List<Project> projects = new ArrayList<>();
    private UpdateChecker() {

    }


    public void registerProject(Project project) {
        projects.add(project);
    }



}
