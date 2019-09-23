package by.training.final_task.service.interfaces;

import by.training.final_task.service.ServiceException;

import java.sql.Connection;

public interface ServiceFactory {
    <T extends Service> T getService(Class<T> key) throws ServiceException;
    Connection getConnection();
    void close() throws ServiceException;
}
