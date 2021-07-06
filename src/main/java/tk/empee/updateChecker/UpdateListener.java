package tk.empee.updateChecker;

public interface UpdateListener {

    void onOutdated(Project project, Update update);
    void onUpdated(Project project);

}
