package mx.dads.infotec.kukulkan.util;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import mx.dads.infotec.kukulkan.domain.Tupla;

public class FileUtil {

    public static final String ROOT = "/home/daniel/git/migration/kukulkan-files/src/main/resources";

    public static final List<String> EXCLUDED_FOLDERS = ExcludeFiles.getExcludedFolders();
    public static final List<String> EXCLUDED_FILES = ExcludeFiles.getExcludedFiles();
    public static final List<String> NO_PROCESSED_AND_COPY_FILES = ExcludeFiles.getExcludedProcessFiles();

    private FileUtil() {

    }

    public static boolean saveToFile(Path pathToSave, String content) {
        createDirectories(pathToSave);
        try (final BufferedWriter out = Files.newBufferedWriter(pathToSave, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            out.write(content);
            return true;
        } catch (IOException ioe) {
            return false;
        }
    }

    public static boolean createDirectories(Path path) {
        try {
            Files.createDirectories(path.getParent());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void copy(Path from, Path to) throws IOException {
        createDirectories(to);
        Files.copy(from, to, REPLACE_EXISTING);
    }

    public static boolean delete(Path directory) {
        try {
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static List<Tupla> getTuplas() {
        List<Tupla> tuplas = new ArrayList<>();
        tuplas.add(new Tupla("mx.infotec.dads.archetype", "\\$\\{project.packaging\\}"));
        tuplas.add(new Tupla("kukulkan-antlr4", "\\${project.id}"));
        tuplas.add(new Tupla("kukulkan", "\\${project.id}"));
        tuplas.add(new Tupla("Kukulkan", "\\${project.id?cap_first}"));
        tuplas.add(new Tupla("MyGrammar", "\\${project.grammarName}"));
        tuplas.add(new Tupla("myExtension", "\\${project.grammarExtension}"));
        return tuplas;
    }

    public static boolean isExcludedFolder(String test) {
        for (String word : EXCLUDED_FOLDERS) {
            if (test.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIgnoreFile(String test) {
        for (String word : EXCLUDED_FILES) {
            if (test.equals(word)) {
                return true;
            }
        }
        return false;
    }
}
