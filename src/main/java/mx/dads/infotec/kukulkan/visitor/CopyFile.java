package mx.dads.infotec.kukulkan.visitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;

import mx.dads.infotec.kukulkan.util.FileUtil;

public class CopyFile extends AbstractFileVisitor {

    @Override
    public FileVisitResult processVisitFile(Path file) throws IOException {
        Path from = file;
        Path to = Paths.get(from.toString().replaceFirst("/git/", "/archetype/").replaceFirst("mx/infotec/dads/archetype", "package"));
        System.out.println("save to: " + to.toString());
        FileUtil.copy(from, to);
        return FileVisitResult.CONTINUE;
    }

}