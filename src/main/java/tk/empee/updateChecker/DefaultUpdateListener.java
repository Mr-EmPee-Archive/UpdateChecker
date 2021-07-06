package tk.empee.updateChecker;

import java.util.List;
import java.util.logging.Logger;

public class DefaultUpdateListener implements UpdateListener {

    private Logger logger;
    public DefaultUpdateListener(Logger logger) {
        this.logger = logger;
    }
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void onOutdated(Project project, Update update) {
        logger.warning("The project " + project + " is outdated!");
        logger.warning("The newest version is the " + update.getVersion() );

        String downloadLink = update.getDownloadLink();
        if(downloadLink != null) {
            logger.warning("You can download it from: " + downloadLink);
        }
        logger.warning("\n");
        List<String> bugFixes = update.getBugFixes();
        if(!bugFixes.isEmpty()) {
            logger.warning(buildBugFixes(bugFixes));
        }
        logger.warning("");
        List<String> otherInfos = update.getOtherInfos();
        if(!otherInfos.isEmpty()) {
            logger.warning(buildOtherInfos(otherInfos));
        }

    }

    private String buildBugFixes(List<String> bugFixes) {

        StringBuilder string = new StringBuilder(" ! Bug-Fixes");
        for(String bugFix : bugFixes) {
            string.append("\n * ").append(bugFix);
        }

        return string.toString();

    }
    private String buildOtherInfos(List<String> otherInfos) {
        StringBuilder string = new StringBuilder(" - Other");
        for(String info : otherInfos) {
            string.append("\n * ").append(info);
        }

        return string.toString();
    }

    @Override
    public void onUpdated(Project project) {
        logger.info("The project " + project + " is at it latest version!");
    }
}
