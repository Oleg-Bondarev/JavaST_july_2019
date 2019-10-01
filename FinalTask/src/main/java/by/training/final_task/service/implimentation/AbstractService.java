package by.training.final_task.service.implimentation;

import by.training.final_task.dao.interfases.DaoFactory;
import by.training.final_task.dao.sql.DaoFactoryImpl;
import by.training.final_task.service.interfaces.Service;

public class AbstractService implements Service {
    private DaoFactory daoFactory;

    public AbstractService() {
        daoFactory = new DaoFactoryImpl();
    }

    public AbstractService(final DaoFactory newDaoFactory) {
        daoFactory = newDaoFactory;
    }

    public DaoFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(final DaoFactory newDaoFactory) {
        daoFactory = newDaoFactory;
    }
}
