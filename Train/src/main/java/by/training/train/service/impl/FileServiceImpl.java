package by.training.train.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.training.train.dao.exception.FileReaderException;
import by.training.train.dao.exception.FileWriterException;
import by.training.train.dao.file_reader.DataInputReader;
import by.training.train.dao.file_writer.DataOutputWriter;
import by.training.train.service.exception.ServiceException;
import by.training.train.service.interfaces.FileService;
import by.training.train.validator.FileValidator;

public class FileServiceImpl implements FileService {
    /***/
    private FileValidator fileValidator = new FileValidator();
    /***/
    @Override
    public List<String> read(final String pathToFile)
            throws ServiceException {
        if (!fileValidator.validatePath(pathToFile)) {
            throw new ServiceException("Incorrect path to file. It must be"
                    + " not null and not empty string.");
        }
        DataInputReader reader = new DataInputReader();
        List<String> inputInformation = new ArrayList<>();
        try {
            inputInformation = reader.readInformatIonFromFile(pathToFile);
        } catch (FileReaderException eNew) {
            throw new ServiceException("Problems with reading file." + eNew);
        }
        return inputInformation;
    }
    /***/
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
