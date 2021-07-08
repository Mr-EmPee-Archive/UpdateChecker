package tk.empee.updateChecker.utils;

public class Version {

    public static int compare(String version1, String version2) {

        return Integer.compare(
                Integer.parseInt(version1.replace(".", "")),
                Integer.parseInt(version2.replace(".", ""))
        );

    }

}
