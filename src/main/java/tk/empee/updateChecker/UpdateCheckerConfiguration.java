package tk.empee.updateChecker;

import java.io.InputStream;

public class UpdateCheckerConfiguration {

    private boolean isEnabled = true;
    private boolean printImportantNotification = true;
    private boolean printOtherNotification = false;

    public UpdateCheckerConfiguration() {}

    public UpdateCheckerConfiguration(InputStream inputStream) {
        //TODO Load from file yml
    }

    public boolean isEnabled() {
        return isEnabled;
    }
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
    public boolean doesItPrintsImportantNotification() {
        return printImportantNotification;
    }
    public void setImportantNotification(boolean printImportantNotification) {
        this.printImportantNotification = printImportantNotification;
    }
    public boolean doesItPrintsOtherNotification() {
        return printOtherNotification;
    }
    public void setOtherNotification(boolean printOtherNotification) {
        this.printOtherNotification = printOtherNotification;
    }

}
