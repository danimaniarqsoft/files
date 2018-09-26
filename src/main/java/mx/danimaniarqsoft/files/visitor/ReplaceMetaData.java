package mx.danimaniarqsoft.files.visitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReplaceMetaData extends AbstractFileVisitor {

	@Override
	public FileVisitResult processVisitFile(Path file) throws IOException {

		return FileVisitResult.CONTINUE;
	}
}