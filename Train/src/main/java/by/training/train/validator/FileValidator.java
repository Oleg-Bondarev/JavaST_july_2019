package by.training.train.validator;

import java.io.File;

public class FileValidator {
    /**
     * @param pathNew - path to file.
     * @return boolean value.
     * */
    public boolean validatePath(final String pathNew) {
        return ((pathNew != null) && (!pathNew.isEmpty()));
    }
    /**
     * @param fileNew - file.
     * @return boolean value.
     * */
    public boolean validateFile(final File fileNew) {
        return ((fileNew != null) && fileNew.exists()
                && (fileNew.length() != 0));
    }
}
