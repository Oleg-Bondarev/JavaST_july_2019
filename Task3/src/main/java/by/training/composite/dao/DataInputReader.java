package by.training.composite.dao;

import by.training.composite.dao.exceptions.FileReaderException;
import by.training.composite.validator.FileValidator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Represent reading text from file in string.
 * */
public class DataInputReader {
    /**
     * Object of file validator.
     * */
    private FileValidator fileValidator = new FileValidator();
    /**
     * Reader from file.
     *
     * @param path -path to file.
     * @return string with text.
     * @throws FileReaderException if have some problems in reading.
     * */
    public String readInformationFromFile(final String path)
            throws FileReaderException {
        File file = new File(path);
        if (!fileValidator.validateFile(file)) {
            throw new FileReaderException("Incorrect input file: " + file
                                + "\t File must be not null and not empty.");
        }
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                stringBuilder.append(tempStr + "\n");
            }
        } catch (IOException e) {
            throw new FileReaderException("Incorrect input file: " + file, e);
        }
        return stringBuilder.toString();
    }
}
