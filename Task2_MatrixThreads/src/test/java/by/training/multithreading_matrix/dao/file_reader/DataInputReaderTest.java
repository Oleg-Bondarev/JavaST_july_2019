package by.training.multithreading_matrix.dao.file_reader;

import by.training.multithreading_matrix.dao.exceptions.FileReaderException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Class for testing data input reader.
 * */
public class DataInputReaderTest {
    /**
     * Data input reader object.
     * */
    private DataInputReader dataInputReader = new DataInputReader();
    /**
     * List of expect result.
     * */
    private List<String> expectList;
    /**
     * List of actual result.
     * */
    private List<String> actualList;
    /**
     * Path to file with data for test run.
     * */
    private final String passToFile = "src\\test\\data\\reader_test.txt";
    /**
     * Set the startup data.
     * */
    @BeforeMethod
    public void setUp() {
        String[] expectArray = {
                "3*4",
                "1 2 3 4",
                "1 2 3 4",
                "1 2 3 4"
        };
        expectList = Arrays.asList(expectArray);
        actualList = new ArrayList<>();
    }
    /**
     * Testing reading from file.
     * @throws FileReaderException -if have some problems with file.
     * */
    @Test
    public void testReadInformationFromFile() throws FileReaderException {
        actualList = dataInputReader.readInformationFromFile(passToFile);
        assertEquals(expectList, actualList);
    }
}
