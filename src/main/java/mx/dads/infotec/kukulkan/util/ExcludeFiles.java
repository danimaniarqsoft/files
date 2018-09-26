package mx.dads.infotec.kukulkan.util;

import java.util.ArrayList;
import java.util.List;

public class ExcludeFiles {

    private ExcludeFiles() {

    }

    public static List<String> getExcludedFolders() {
        List<String> folder = new ArrayList<>();
        folder.add(".settings");
        folder.add(".git");
        folder.add("target");
        folder.add(".apt_generated");
        folder.add("node");
        folder.add("node_module");
        folder.add("node_modules");
        folder.add("bower_components");
        return folder;
    }

    public static List<String> getExcludedFiles() {
        List<String> files = new ArrayList<>();
        files.add(".classpath");
        files.add(".project");
        files.add(".factorypath");
        files.add(".apt_generated");
        files.add("node");
        files.add("node_module");
        files.add("node_modules");
        files.add("bower_components");
        return files;
    }

    public static List<String> getExcludedProcessFiles() {
        List<String> files = new ArrayList<>();
        files.add("maven-wrapper.properties");
        files.add("maven-wrapper.jar");
        files.add("logo-app.png");
        files.add("app2x.png");
        files.add("Dockerfile");
        files.add("banner.txt");
        files.add("creationEmail.html");
        files.add("activationEmail.html");
        files.add("passwordResetEmail.html");
        files.add("error.html");
        files.add("testEmail.html");
        files.add("mvnw.cmd");
        files.add("mvnw");
        files.add("app.png");
        files.add("favicon.ico");
        return files;
    }

    public static List<String> getWebAppExclusion() {
        List<String> folder = new ArrayList<>();
        folder.add("webapp");
        folder.add("gulp");
        folder.add("javascript");
        return folder;
    }

    public static List<String> getAngularExclusion() {
        List<String> folder = new ArrayList<>();
        folder.add("bower.json");
        folder.add("gulpfile.js");
        folder.add("package.json");
        folder.add("yarn.lock");
        folder.add(".bowerrc");
        folder.add(".editorconfig");
        folder.add(".eslintignore");
        folder.add(".eslintrc.json");
        folder.add(".gitattributes");
        folder.add(".yo-rc.json");
        return folder;
    }
}
