package mx.danimaniarqsoft.files;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import mx.danimaniarqsoft.files.visitor.ListFiles;
import mx.danimaniarqsoft.files.visitor.ReplaceMetaData;

@SpringBootApplication
public class FilesApplication {

	public static void main(String[] args) throws IOException {
		// SpringApplication.run(FilesApplication.class, args);
		// Replace in place metaData
		System.out.println("Running files in " + System.getProperty("user.dir"));
		System.out.println("data:" + args[0]);
		FileVisitor<Path> replaceMetaDataProcessor = new ListFiles();
		System.out.println("************************");
		Files.walkFileTree(Paths.get(System.getProperty("user.dir")), replaceMetaDataProcessor);
	}
}
