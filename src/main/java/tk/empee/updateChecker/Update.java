package tk.empee.updateChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Update {

    private final List<String> bugFixes = new ArrayList<>();
    private final List<String> otherInfos = new ArrayList<>();

    private final Project project;

    Update(Project project, URL changelogURL) {
        this.project = project;

        loadChangelog(changelogURL);
    }

    public String getVersion() {
        return project.getLatestVersion();
    }
    public URL getDownloadURL() {
        return project.getLatestJarDownloadURL();
    }

    public List<String> getBugFixes() {
        return bugFixes;
    }

    public List<String> getOtherInfos() {
        return otherInfos;
    }

    private void loadChangelog(URL changelogURL) {

        String buffer, oldBuffer = null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(changelogURL.openStream()))) {

            while((buffer = reader.readLine()) != null || oldBuffer != null) {

                if(oldBuffer != null) {
                    if (buffer == null || !buffer.startsWith("\t")) {
                        logMessage(oldBuffer);
                    } else {
                        buffer = oldBuffer + " " + buffer;
                    }
                }

                oldBuffer = buffer;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void logMessage(String message) {

        if(message.startsWith("!")) {
            bugFixes.add(message);
        } else {
            otherInfos.add(message);
        }

    }

}
