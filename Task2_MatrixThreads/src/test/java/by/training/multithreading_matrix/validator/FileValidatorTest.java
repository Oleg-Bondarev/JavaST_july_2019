package by.training.multithreading_matrix.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.*;

public class FileValidatorTest {
    private FileValidator fileValidator = new FileValidator();

    @DataProvider(name = "NegativeDateProviderForValidateFile")
    public Object[] createPositiveForvalidateStr() {
        return new File[] {
                new File("path.txt"),
                new File("Train\\data\\empty_file.txt")
        };
    }

    @Test(description = "Test incorrect path to file: null.")
    public void testValidatePathOnNull() {
        String path = null;
        boolean actual = fileValidator.validatePath(path);
        assertFalse(actual);
    }

    @Test(description = "Test incorrect path to file: empty string.")
    public void testValidatePathOnEmptyString() {
        String path = "";
        boolean actual = fileValidator.validatePath(path);
        assertFalse(actual);
    }

    @Test(dataProvider = "NegativeDateProviderForValidateFile",
            description = "Test incorrect file: file does not exist.")
    public void testValidateFileNotExist(File file) {
        boolean actual = fileValidator.validateFile(file);
        assertFalse(actual);
    }
}