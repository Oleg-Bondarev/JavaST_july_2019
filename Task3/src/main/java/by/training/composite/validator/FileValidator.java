package by.training.composite.validator;

import java.io.File;

/**
 * Represents file validation.
 * */
public class FileValidator {
    /**
     * Validate path to file.
     *
     * @param pathNew - path to file.
     * @return boolean value.
     * */
    public boolean validatePath(final String pathNew) {
        return ((pathNew != null) && (!"".equals(pathNew)));
    }
    /**
     * Validate file.
     *
     * @param fileNew - file.
     * @return boolean value.
     * */
    public boolean validateFile(final File fileNew) {
        return ((fileNew != null) && (fileNew.length() != 0));
    }
}
