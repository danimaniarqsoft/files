package mx.dads.infotec.kukulkan.action;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import mx.dads.infotec.kukulkan.domain.Technology;

/** Recursive listing with SimpleFileVisitor in JDK 7. */
public final class TestPattern {
    public static void main(String... aArgs) throws IOException {
        FileVisitor<Path> fileProcessor = new ProcessFile();
        Files.walkFileTree(Technology.JAVA_SPRING_MONGO.getInputPath(), fileProcessor);
    }

    private static final class ProcessFile extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (file.getFileName().toString().equals("LoggingConfiguration.java")) {
                String sContent = new String(Files.readAllBytes(file));
                sContent = sContent.replaceAll("(\\$\\{.*?\\})", "\\${r\"$1\"}");
                System.out.println(sContent);
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
