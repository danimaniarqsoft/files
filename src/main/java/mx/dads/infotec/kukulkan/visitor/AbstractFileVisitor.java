package mx.dads.infotec.kukulkan.visitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import mx.dads.infotec.kukulkan.util.FileUtil;

public abstract class AbstractFileVisitor extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (FileUtil.isIgnoreFile(file.getFileName().toString())) {
            return FileVisitResult.CONTINUE;
        } else {
            return processVisitFile(file);
        }
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if (FileUtil.isExcludedFolder(dir.getFileName().toString())) {
            return FileVisitResult.SKIP_SUBTREE;
        } else {
            return FileVisitResult.CONTINUE;
        }
    }

    public abstract FileVisitResult processVisitFile(Path file) throws IOException;

}