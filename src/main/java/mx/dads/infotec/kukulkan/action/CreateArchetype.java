package mx.dads.infotec.kukulkan.action;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;

import mx.dads.infotec.kukulkan.domain.Technology;
import mx.dads.infotec.kukulkan.util.FileUtil;
import mx.dads.infotec.kukulkan.visitor.ReplaceMetaData;

public final class CreateArchetype {

    public static void main(String... aArgs) throws IOException {
        Technology technology = Technology.JAVA_SPRING_JPA;
        FileUtil.EXCLUDED_FILES.addAll(technology.getExcludedFiles());
        FileUtil.EXCLUDED_FOLDERS.addAll(technology.getExcludedFolders());
        FileUtil.NO_PROCESSED_AND_COPY_FILES.addAll(technology.getNoProcessedAndCopyFiles());
        // Delete target folder
        FileUtil.delete(technology.getOutputPath());
        // Replace in place metaData
        FileVisitor<Path> replaceMetaDataProcessor = new ReplaceMetaData(technology);
        System.out.println("************************");
        Files.walkFileTree(technology.getInputPath(), replaceMetaDataProcessor);
    }
}
