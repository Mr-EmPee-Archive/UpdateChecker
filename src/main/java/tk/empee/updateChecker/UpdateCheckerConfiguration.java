package tk.empee.updateChecker;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

class UpdateCheckerConfiguration {
    private final boolean isEnabled;
    private final boolean printBugFixes;
    private final boolean printOtherNotification;

    public UpdateCheckerConfiguration() {

        isEnabled = true;
        printBugFixes = true;
        printOtherNotification = true;

    }

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
    public boolean doesItPrintsBugFixes() {
        return printBugFixes;
    }
    public boolean doesItPrintsOtherNotification() {
        return printOtherNotification;
    }

}
