package by.training.multithreading_matrix.dao.file_reader;

import by.training.multithreading_matrix.dao.exceptions.FileReaderException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class DataInputReaderTest {
    private DataInputReader dataInputReader = new DataInputReader();
    private List<String> expectList;
    private List<String> actualList;
    private final static String passToFile = "src\\test\\data\\reader_test.txt";
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

    @Test
    public void testReadInformationFromFile() throws FileReaderException {
        actualList = dataInputReader.readInformationFromFile(passToFile);
        assertEquals(expectList, actualList);
    }
}