package mx.danimaniarqsoft.files.visitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class ListFiles extends AbstractFileVisitor {

	final Pattern p = Pattern.compile(".*\\.md$");

	@Override
	public FileVisitResult processVisitFile(Path file) throws IOException {
		if (p.matcher(file.toAbsolutePath().toString()).matches()) {
			System.out.println(file.toAbsolutePath().toString());
		}
		return FileVisitResult.CONTINUE;
	}
}