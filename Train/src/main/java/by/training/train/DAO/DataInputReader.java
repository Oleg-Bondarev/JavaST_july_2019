package by.training.train.DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataInputReader {
    /**Logger.*/
    private static final Logger LOGGER =
            LogManager.getLogger(DataInputReader.class);
    /**
     * Read information.
     * @param path - path to the file.
     * @return list of carriages.
     * @throws IOException in readLine.
     */
    public ArrayList<String> readInformatIonFromFile(final String path)
            throws IOException {
        ArrayList<String> informationList = new ArrayList<>();
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new FileReader(path));
            String tempStr;

            while ((tempStr = bufferedReader.readLine()) != null) {
                informationList.add(tempStr);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(e);
        }
        return informationList;
    }

}
