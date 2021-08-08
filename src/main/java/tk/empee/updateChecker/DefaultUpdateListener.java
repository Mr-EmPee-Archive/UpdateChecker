package tk.empee.updateChecker;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class DefaultUpdateListener implements UpdateListener {

    private final UpdateCheckerConfiguration configuration;

    private Logger logger;
    public DefaultUpdateListener(UpdateChecker updateChecker, Logger logger) {
        configuration = updateChecker.getConfiguration();
        this.logger = logger;
    }
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void onOutdated(Project project, Update update) {
        logger.warning("The project " + project + " is outdated!");
        logger.warning("The newest version is the " + update.getLatestVersion() );

        String downloadLink = update.getDownloadURL().toString();
        if(downloadLink != null) {
            logger.warning("You can download it from: " + downloadLink);
        }

        printChangelog(update);

    }

    private void printChangelog(Update update) {
        printBugFixes(update);
        printOtherInfos(update);
    }

    private void printBugFixes(Update update) {

        if(configuration.doesItPrintsBugFixes()) {
            List<String> bugFixes = update.getBugFixes();
            if (!bugFixes.isEmpty()) {
                logger.warning("\n");
                Collections.sort(bugFixes);
                logger.warning(buildBugFixes(bugFixes));
            }
        }

    }
    private String buildBugFixes(List<String> bugFixes) {

        StringBuilder string = new StringBuilder(" ! Bug-Fixes");
        for(String bugFix : bugFixes) {
            string.append("\n ").append(bugFix);
        }

        return string.toString();

    }

    private void printOtherInfos(Update update) {
        if(configuration.doesItPrintsOtherNotification()) {
            List<String> otherInfos = update.getOtherInfos();
            if (!otherInfos.isEmpty()) {
                logger.warning("\n");
                Collections.sort(otherInfos);
                logger.warning(buildOtherInfos(otherInfos));
            }
        }
    }
    private String buildOtherInfos(List<String> otherInfos) {
        StringBuilder string = new StringBuilder(" - Other");
        for(String info : otherInfos) {
            string.append("\n ").append(info);
        }

        return string.toString();
    }

    @Override
    public void onUpdated(Project project) {
        logger.info("The project " + project + " is at it latest version!");
    }
}
