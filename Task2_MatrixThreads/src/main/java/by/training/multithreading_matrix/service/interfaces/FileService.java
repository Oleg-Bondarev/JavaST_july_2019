package by.training.multithreading_matrix.service.interfaces;

import by.training.multithreading_matrix.service.ServiceException;

import java.util.List;

/**File service interface.*/
public interface FileService {
    /**
     * @param pathToFile - path to file from what we want to read information.
     * @return list of strings. One string - information to create one object.
     * @throws ServiceException - fail in file reading.
     * */
    List<String> read(String pathToFile) throws ServiceException;
    /**
     * @param pathToFile - path to file in what we want to write.
     * @param listNew - list with some information that we want to write.
     * @throws ServiceException -fail in file writing.
     * */
    void write(String pathToFile, List<String> listNew) throws ServiceException;
}
