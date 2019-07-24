package by.training.train.service.interfaces;

import java.util.List;

import by.training.train.service.exception.ServiceException;

public interface FileService {
    /**
     * @param pathToFile - path to file from what we want to read information.
     * @return list of strings. One string - information to create one object.
     * @throws ServiceException -
     * */
    List<String> read(String pathToFile) throws ServiceException;
    /**
     * @param pathToFile - path to file in what we want to write.
     * @param listNew - list with some information that we want to write.
     * @throws ServiceException -
     * */
    void write(String pathToFile, List<String> listNew) throws ServiceException;
}
