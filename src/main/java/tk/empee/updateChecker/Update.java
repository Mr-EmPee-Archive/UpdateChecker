package tk.empee.updateChecker;

import java.util.ArrayList;
import java.util.List;

public class Update {

    private final String version;

    public Update(String version) {
        this.version = version;
    }
    public String getVersion() {
        return version;
    }

    private String downloadLink;
    public String getDownloadLink() {
        return downloadLink;
    }
    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    private final List<String> bugFixes = new ArrayList<>();
    public void addBugFix(String bugFix) {
        bugFixes.add(bugFix);
    }
    public List<String> getBugFixes() {
        return bugFixes;
    }

    private final List<String> otherInfos = new ArrayList<>();
    public void addOtherInfo(String info) {
        otherInfos.add(info);
    }
    public List<String> getOtherInfos() {
        return otherInfos;
    }
}
