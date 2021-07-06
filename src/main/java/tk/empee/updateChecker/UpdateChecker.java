package tk.empee.updateChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UpdateChecker {
    private static UpdateChecker instance;
    public static UpdateChecker getInstance() {

        if(instance == null) {
            instance = new UpdateChecker();
        }

        return instance;
    }


    private final List<Project> projects = new ArrayList<>();
    private UpdateListener updateListener = new DefaultUpdateListener(Logger.getLogger("UpdateChecker"));

    private UpdateChecker() {

    }

    UpdateListener getUpdateListener() {
        return updateListener;
    }

    public void registerProject(Project project) {
        projects.add(project);
    }
    public void setListener(UpdateListener updateListener) {
        this.updateListener = updateListener;
    }

}
