package by.training.multithreading_matrix.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertFalse;

/**
 * Class for testing file validation.
 * */
public class FileValidatorTest {
    /**
     * Object of file validator.
     * */
    private FileValidator fileValidator = new FileValidator();
    /**
     * Data provider with negative scenario for file validator.
     * @return array with data for testing.
     * */
    @DataProvider(name = "NegativeDateProviderForValidateFile")
    public Object[] createPositiveForValidateStr() {
        return new File[] {
                new File("path.txt"),
                new File("data\\empty_file.txt")
        };
    }
    /**
     * Testing the null path for file.
     * */
    @Test(description = "Test incorrect path to file: null.")
    public void testValidatePathOnNull() {
        boolean actual = fileValidator.validatePath(null);
        assertFalse(actual);
    }
    /**
     * Testing empty string as file path.
     * */
    @Test(description = "Test incorrect path to file: empty string.")
    public void testValidatePathOnEmptyString() {
        String path = "";
        boolean actual = fileValidator.validatePath(path);
        assertFalse(actual);
    }
    /**
     * Testing nonexistent and empty file.
     * @param file -testing file.
     * */
    @Test(dataProvider = "NegativeDateProviderForValidateFile",
            description = "Test incorrect file: file does not exist.")
    public void testValidateFileNotExist(final File file) {
        boolean actual = fileValidator.validateFile(file);
        assertFalse(actual);
    }
}
