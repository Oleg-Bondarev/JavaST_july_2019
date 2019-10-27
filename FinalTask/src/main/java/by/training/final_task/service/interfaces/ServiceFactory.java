package by.training.final_task.service.interfaces;

import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.service.ServiceException;

public interface ServiceFactory {
    Service createService(DAOEnum key) throws ServiceException;

    void close() throws ServiceException;
}
