package by.training.multithreading_matrix.dao.file_writer;

import by.training.multithreading_matrix.dao.exceptions.FileWriterException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class DataOutputWriter {
    /**
     * Read information.
     * @param path - path to the file.
     * @param list - list with output information.
     * @throws FileWriterException -problems with writing file.
     */
    public void writeInformationToFile(final String path,
            final List<String> list) throws FileWriterException {
        Date date = new Date();
        String spliter = "==========================";
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedWriter writer =
                    new BufferedWriter(new FileWriter(file, true))) {
                writer.write(spliter + "\n" + date.toString() + "\n");
                int len = list.size();
                for (int i = 0; i < len; i++) {
                    writer.write(list.get(i) + "\n");
                }
            } catch (IOException e) {
                throw new FileWriterException(e);
            }
        } catch (IOException e) {
            throw new FileWriterException("Problem with creating file. " + e);
        }
    }
}
