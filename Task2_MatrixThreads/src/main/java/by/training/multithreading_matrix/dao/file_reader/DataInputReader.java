package by.training.multithreading_matrix.dao.file_reader;

import by.training.multithreading_matrix.dao.exceptions.FileReaderException;
import by.training.multithreading_matrix.validator.FileValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**Read information from file.*/
public class DataInputReader {
    /**File validator.*/
    private FileValidator fileValidator = new FileValidator();
    /**Reader from file.
     * @param path -path to file.
     * @return list of strings, that were rad from file.
     * @throws FileReaderException if have some problems in reading.*/
    public List<String> readInformationFromFile(final String path)
            throws FileReaderException {
        File file = new File(path);
        if (!fileValidator.validateFile(file)) {
            throw new FileReaderException("Incorrect input file: " + file);
        }
        List<String> information;
        Path path1 = Paths.get(path);
        try (Stream<String> lineStream =
                     Files.newBufferedReader(path1).lines()) {
            information = lineStream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileReaderException("Incorrect input file: " + file, e);
        }
        information.removeAll(Arrays.asList("", null)); ////!!!!!
        return information;
    }
}
