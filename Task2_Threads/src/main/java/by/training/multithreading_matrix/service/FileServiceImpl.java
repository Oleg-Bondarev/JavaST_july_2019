package by.training.multithreading_matrix.service;

import by.training.multithreading_matrix.dao.exceptions.FileReaderException;
import by.training.multithreading_matrix.dao.exceptions.FileWriterException;
import by.training.multithreading_matrix.dao.file_reader.DataInputReader;
import by.training.multithreading_matrix.dao.file_writer.DataOutputWriter;
import by.training.multithreading_matrix.validator.FileValidator;

import java.util.List;

/**Realisation of the file service interface*/
public class FileServiceImpl implements FileService {
    /**File validator.*/
    private FileValidator fileValidator = new FileValidator();
    /**Read from file
     * @param pathToFile -path.
     * @return list of read information.
     * @throws ServiceException fail in file reading.*/
    @Override
    public List<String> read(final String pathToFile)
            throws ServiceException {
        if (!fileValidator.validatePath(pathToFile)) {
            throw new ServiceException("Incorrect path to file. It must be"
                    + " not null and not empty string.");
        }
        DataInputReader reader = new DataInputReader();
        List<String> inputInformation;
        try {
            inputInformation = reader.readInformationFromFile(pathToFile);
        } catch (FileReaderException eNew) {
            throw new ServiceException("Problems with reading file." + eNew);
        }
        return inputInformation;
    }
    /**Write information in file
     * @param pathToFile -path.
     * @exception ServiceException fail in file writing.*/
    @Override
    public void write(final String pathToFile, final List<String> listNew)
            throws ServiceException {
        if (!fileValidator.validatePath(pathToFile)) {
            throw new ServiceException("Incorrect path to file. It must be"
                    + " not null string.");
        }
        DataOutputWriter writer = new DataOutputWriter();
        try {
            writer.writeInformationToFile(pathToFile, listNew);
        } catch (FileWriterException eNew) {
            throw new ServiceException("Problems with writing file." + eNew);
        }
    }
}
