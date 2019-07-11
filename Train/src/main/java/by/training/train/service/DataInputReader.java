package by.training.train.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataInputReader {
    /**Logger.*/
    private static final Logger LOGGER =
            LogManager.getLogger(DataInputReader.class);
    /**Read information.
     * @param path - path to the file.
     * @return list of carriages.
     */
    public List<String> readInformationFromFile(final String path) {
        List<String> content = null;
        try {
            Stream<String> lines = Files.lines(Paths.get(path));
            content = lines.collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Error with file!!!",
                   new IOException("IOException", e));
        }
        return content;
    }

}
