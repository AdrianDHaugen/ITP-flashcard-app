package json;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileHandlerTest {

    private FileHandler fileHandler;
    private String sampleDecklist = "{\"Flashcard\":[{\"test 1\":\"one test\"},{\"another test\":\"test another\"}]}";
    private String filename = "removable_temporary_flashcarzz_test_savefile.json";
    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() {
        fileHandler = new FileHandler();
        fileHandler.setSaveFile(filename);
    }

    @Test
    public void writeToFileAndLoadSavefileTest() throws IOException {
        fileHandler.writeToFile(sampleDecklist);

        String loadedData = fileHandler.loadSavefile();

        assertEquals(sampleDecklist, loadedData, "Expected the loaded data to match the written data");
    }

    @AfterEach
    public void tearDown() {
        fileHandler = null;
        Paths.get(System.getProperty("user.home"), filename).toFile().delete();
    }
}
