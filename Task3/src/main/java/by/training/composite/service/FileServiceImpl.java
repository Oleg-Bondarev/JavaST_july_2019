package by.training.composite.service;

import by.training.composite.dao.DataInputReader;
import by.training.composite.dao.exceptions.FileReaderException;
import by.training.composite.validator.FileValidator;

/**
 * File service implementation.
 * */
public class FileServiceImpl implements FileService {
    /**
     * File validator.
     * */
    private FileValidator fileValidator = new FileValidator();
    /**
     * Exception message.
     * */
    private static final String MES = "Incorrect path to file. It must be"
            + " not null and not empty string.";
    /**
     * Reading text from file.
     * @param pathToFile -path.
     * @return text in string.
     * @throws ServiceException -if have some problems.
     * */
    @Override
    public String read(final String pathToFile) throws ServiceException {
        if (!fileValidator.validatePath(pathToFile)) {
            throw new ServiceException(MES);
        }
        DataInputReader reader = new DataInputReader();
        String text;
        try {
            text = reader.readInformationFromFile(pathToFile);
        } catch (FileReaderException e) {
            throw new ServiceException("Problems with reading file.", e);
        }
        return text;
    }
}
