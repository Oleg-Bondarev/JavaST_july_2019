package by.training.composite.dao;

import by.training.composite.dao.exceptions.FileReaderException;
import by.training.composite.service.ServiceException;
import by.training.composite.service.ServiceFactory;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * Tests for data input reader.
 * */
public class DataInputReaderTest {
    /**
     * Path to empty file.
     * */
    private final String pathEmptyFile = "/data/empty_file.txt";
    /**
     * Service factory.
     * */
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    /**
     * Data reader.
     * */
    private DataInputReader reader = new DataInputReader();
    /**
     * Test on reading from not empty file.
     * @throws ServiceException -exception.
     * @throws FileReaderException -exception.
     * */
    @Test(description = "Test on reading from not empty file.",
            groups = {"Data read"})
    public void testReadInformationFromFile() throws ServiceException,
            FileReaderException {
        String path = serviceFactory.getPropertiesReader()
                .takeProperty("property/config.properties",
                        "inputFile");
        String textFromFile = reader.readInformationFromFile(path);
        assertFalse(textFromFile.isEmpty());
    }
    /**
     * Test on reading empty and file.
     * @throws FileReaderException -exception.
     * */
    @Test(description = "Empty file and null", groups = {"Data read"},
            expectedExceptions = FileReaderException.class)
    public void testReadEmptyFile() throws FileReaderException {
        String pathForEmptyFile = "/data/empty_file.txt";
        String fullPath = fullPath(pathForEmptyFile);
        reader.readInformationFromFile(pathForEmptyFile);
    }
    /**
     * Get full path method.
     * @param path -path.
     * @return path.
     * */
    private String fullPath(final String path) {
        String temp = getClass().getResource(path).getPath();
        File file = new File(temp);
        return file.getAbsolutePath();
    }
    /**
     * Test on equals reading information.
     * @throws FileReaderException -exception.
     * */
    @Test(description = "Equals reading info", groups = {"Data read"})
    public void testEqualsReadingInfo() throws FileReaderException {
        String pathForEmptyFile = "/data/reader_test.txt";
        String fullPath = fullPath(pathForEmptyFile);
        String expectString = "\n\n       Some text.\n       New paragraph.\n"
                + "\n\n";
        String actual = reader.readInformationFromFile(fullPath);
        System.out.println(actual);
        assertEquals(actual, expectString);
    }
}
