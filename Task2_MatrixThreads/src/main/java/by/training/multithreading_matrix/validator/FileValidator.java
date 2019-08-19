package by.training.multithreading_matrix.validator;

import java.io.File;

/**
 * File validator class.
 * */
public class FileValidator {
    /**
     * @param pathNew - path to file.
     * @return boolean value.
     * */
    public boolean validatePath(final String pathNew) {
        return ((pathNew != null) && (!"".equals(pathNew)));
    }
    /**
     * @param fileNew - file.
     * @return boolean value.
     * */
    public boolean validateFile(final File fileNew) {
        return ((fileNew != null) && (fileNew.length() != 0));
    }
}
