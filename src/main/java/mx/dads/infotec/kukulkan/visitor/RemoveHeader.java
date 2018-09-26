package mx.dads.infotec.kukulkan.visitor;

import static mx.dads.infotec.kukulkan.util.FileUtil.ROOT;
import static mx.dads.infotec.kukulkan.util.FileUtil.saveToFile;

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

public final class RemoveHeader {

    public static void main(String... aArgs) throws IOException {
        FileVisitor<Path> fileProcessor = new ProcessFile();
        Files.walkFileTree(Paths.get(ROOT), fileProcessor);
    }

    private static final class ProcessFile extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            String sContent = null;
            try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))) {
                String line;
                String firstLine = br.readLine();
                if (firstLine != null && firstLine.matches("^<%#[\\w\n]*")) {
                    while (!(line = br.readLine()).matches("-%>")) {
                    }
                    StringBuilder content = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    sContent = content.toString();
                    saveToFile(file, sContent);
                    System.out.println("changed: " + file);
                } else {
                }
            }

            return FileVisitResult.CONTINUE;
        }
    }
}