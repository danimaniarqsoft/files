package mx.dads.infotec.kukulkan.visitor;

import static mx.dads.infotec.kukulkan.util.FileUtil.ROOT;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Pattern;

/** Recursive listing with SimpleFileVisitor in JDK 7. */
public final class RemoveUnderScore {
    public static final Pattern UNDERSCORE_PATTERN = Pattern.compile("^_[\\w.-]*$");

    public static void main(String... aArgs) throws IOException {
        FileVisitor<Path> fileProcessor = new ProcessFile();
        Files.walkFileTree(Paths.get(ROOT), fileProcessor);
    }

    private static final class ProcessFile extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (UNDERSCORE_PATTERN.matcher(file.getFileName().toString()).matches()) {
                String ftlWord = file.getFileName().toString().replaceFirst("^_", "") + ".ftl";
                newName(file, ftlWord);
            } else {
                System.out.println("no change: " + file.toString());
            }

            return FileVisitResult.CONTINUE;
        }
    }

    private static Path newName(Path oldName, String newNameString) throws IOException {
        return Files.move(oldName, oldName.resolveSibling(newNameString));
    }
}