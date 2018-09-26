package mx.dads.infotec.kukulkan.visitor;

import static mx.dads.infotec.kukulkan.domain.Technology.INPUT_FOLDER_NAME;
import static mx.dads.infotec.kukulkan.domain.Technology.OUTPUT_FOLDER_NAME;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import mx.dads.infotec.kukulkan.domain.Technology;
import mx.dads.infotec.kukulkan.domain.Tupla;
import mx.dads.infotec.kukulkan.util.FileUtil;

public class ReplaceMetaData extends AbstractFileVisitor {

    private Technology technology;

    public ReplaceMetaData(Technology technology) {
        this.technology = technology;
    }

    @Override
    public FileVisitResult processVisitFile(Path file) throws IOException {
        Path to = Paths.get(file.toString().replaceFirst(INPUT_FOLDER_NAME, OUTPUT_FOLDER_NAME)
                .replaceFirst(technology.getPackaging(), "package"));
        if (isTemplate(file)) {
            String sContent = new String(Files.readAllBytes(file)).replaceAll("(\\$\\{.*?\\})", "\\${r\"$1\"}");
            for (Tupla tupla : FileUtil.getTuplas()) {
                sContent = sContent.replaceAll(tupla.getFrom(), tupla.getTo());
            }
            formatOutput(to.toString() + ".ftl");
            FileUtil.saveToFile(Paths.get(to.toString() + ".ftl"), sContent);
        } else {
            formatOutput(to.toString());
            FileUtil.copy(file, to);
        }
        return FileVisitResult.CONTINUE;
    }

    private boolean isTemplate(Path file) {
        for (String word : FileUtil.NO_PROCESSED_AND_COPY_FILES) {
            if (file.getFileName().toString().equals(word)) {
                return false;
            }
        }
        return true;
    }

    private void formatOutput(String from) {
        String toReplace = Technology.HOME_FOLDER + OUTPUT_FOLDER_NAME + technology.getFolderName();
        String replacement = "";
        System.out.println("templates.add(" + technology.getConstant()+" + \""+ from.replaceFirst(toReplace, replacement) + "\");");
    }

}