package json;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class FileHandler {

    private Path saveFilePath = Paths.get(System.getProperty("user.home"), ".flashcardzz_savefile.json");

    public void setSaveFile(String saveFile) {
        this.saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);
    }

    public void writeToFile(String decklist) throws IOException {
        FileWriter file = new FileWriter(saveFilePath.toString());
        file.write(decklist);
        file.close();
    }

    public String loadSavefile() throws IOException {
        return new String(Files.readAllBytes(saveFilePath));
    }
}
