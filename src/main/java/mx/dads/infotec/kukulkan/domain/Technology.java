package mx.dads.infotec.kukulkan.domain;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import mx.dads.infotec.kukulkan.util.ExcludeFiles;

public enum Technology {

    ANGULAR_JS("angularjs", "@NULL", "ANGULAR_JS_TEMPLATE"), 
    JAVA_SPRING_JPA("angularjs-spring-jpa", "mx/infotec/dads/archetype", "ANGULAR_SPRING_JPA_TEMPLATE"), 
    JAVA_SPRING_MONGO("angularjs-spring-mongo", "mx/infotec/dads/archetype", "ANGULAR_SPRING_MONGO_TEMPLATE"),
    ANTLR4("antlr4", "mx/dads/infotec/archetype", "ANTLR4_TEMPLATE");

    private String folderName;
    private String packaging;
    private String constant;

    public static final String INPUT_FOLDER_NAME = "/git/";
    public static final String OUTPUT_FOLDER_NAME = "/archetypes/";
    public static final String HOME_FOLDER = System.getProperty("user.home");

    private static final Path INPUT_FOLDER = Paths.get(HOME_FOLDER, INPUT_FOLDER_NAME);
    private static final Path OUTPUT_FOLDER = Paths.get(HOME_FOLDER, OUTPUT_FOLDER_NAME);

    private Technology(String folderName, String packaging, String constant) {
        this.folderName = folderName;
        this.packaging = packaging;
        this.constant = constant;
    }

    public String getFolderName() {
        return this.folderName;
    }

    public String getConstant() {
        return this.constant;
    }

    public Path getOutputPath() {
        return OUTPUT_FOLDER.resolve(folderName);
    }

    public Path getInputPath() {
        return INPUT_FOLDER.resolve(folderName);
    }

    public String getPackaging() {
        return this.packaging;
    }

    public List<String> getExcludedFiles() {
        if (this.equals(JAVA_SPRING_JPA) || this.equals(JAVA_SPRING_MONGO)) {
            return ExcludeFiles.getAngularExclusion();
        } else {
            return new ArrayList<>();
        }
    }

    public List<String> getExcludedFolders() {
        if (this.equals(JAVA_SPRING_JPA) || this.equals(JAVA_SPRING_MONGO)) {
            return ExcludeFiles.getWebAppExclusion();
        } else {
            return new ArrayList<>();
        }
    }

    public List<String> getNoProcessedAndCopyFiles() {
        return new ArrayList<>();
    }
}
