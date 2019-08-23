package by.training.composite.service.interfaces;

import by.training.composite.service.ServiceException;

/**
 * File service interface.
 * */
public interface FileService {
    /**
     * Reading text from file.
     * @param pathToFile -path to file.
     * @return text in string.
     * @throws ServiceException -if have some problems in reading.
     * */
    String read(String pathToFile) throws ServiceException;
}
