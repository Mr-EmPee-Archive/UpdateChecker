package tk.empee.updateChecker;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

class UpdateCheckerConfiguration {
    private boolean isEnabled = true;
    private boolean printBugFixes = true;
    private boolean printOtherNotification = false;

    public UpdateCheckerConfiguration() {}

    public UpdateCheckerConfiguration(InputStream inputStream) {

        Map<String, Object> config = new Yaml().load(inputStream);
        config =  (Map<String, Object>) config.get("updater");

        isEnabled = (boolean) config.get("checks-for-updates");
        Map<String, Object> notifySettings = (Map<String, Object>) config.get("notify-settings");
        printBugFixes = (boolean) notifySettings.get("bug-fixes");
        printOtherNotification = (boolean) notifySettings.get("other-info");

    }

    public boolean isEnabled() {
        return isEnabled;
    }
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
    public boolean doesItPrintsBugFixes() {
        return printBugFixes;
    }
    public void setBugFixes(boolean printImportantNotification) {
        this.printBugFixes = printImportantNotification;
    }
    public boolean doesItPrintsOtherNotification() {
        return printOtherNotification;
    }
    public void setOtherNotification(boolean printOtherNotification) {
        this.printOtherNotification = printOtherNotification;
    }

}
