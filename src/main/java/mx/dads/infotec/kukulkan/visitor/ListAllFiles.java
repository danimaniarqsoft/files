package mx.dads.infotec.kukulkan.visitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public final class ListAllFiles {

    public static void main(String... aArgs) throws IOException {
        FileVisitor<Path> fileProcessor = new ProcessFile();
        Files.walkFileTree(
                Paths.get(
                        "/home/daniel/git/kukulkan-generator-angularjs/src/main/resources/templates/archetypes/paths.txt"),
                fileProcessor);
    }

    private static final class ProcessFile extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("templates.add(\"" + line + "\");");
                }
            }
            return FileVisitResult.CONTINUE;
        }
    }
}