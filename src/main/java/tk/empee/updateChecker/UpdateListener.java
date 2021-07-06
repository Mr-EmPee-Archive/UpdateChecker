package tk.empee.updateChecker;

public interface UpdateListener {

    void onOutdated(Project project);
    void onUpdated(Project project);

}
