package by.training.train.dao.file_reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import by.training.train.dao.exception.FileReaderException;
import by.training.train.validator.FileValidator;

public class DataInputReader {
    /***/
    private FileValidator fileValidator = new FileValidator();
    /**
     * Read information.
     * @param path - path to the file.
     * @return list of carriages.
     * @throws FileReaderException in readLine.
     */
    public ArrayList<String> readInformatIonFromFile(final String path)
            throws FileReaderException {
        ArrayList<String> informationList = new ArrayList<>();
        File file = new File(path);
        if (!fileValidator.validateFile(file)) {
            throw new FileReaderException("Incorrect input file: " + file);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                informationList.add(tempStr);
            }
        } catch (IOException e) {
            throw new FileReaderException("Incorrect input file: " + file, e);
        }
        return informationList;
    }
}
