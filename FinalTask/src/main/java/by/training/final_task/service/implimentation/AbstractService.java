package by.training.final_task.service.implimentation;

import by.training.final_task.dao.interfases.DaoFactory;
import by.training.final_task.dao.sql.DaoFactoryImpl;
import by.training.final_task.service.interfaces.Service;

/**
 * Abstract class for service.
 * */
class AbstractService implements Service {
    /**
     * Factory of DAO.
     * */
    private DaoFactory daoFactory;

    /**
     * Default constructor.
     * */
    AbstractService() {
        daoFactory = new DaoFactoryImpl();
    }
    /**
     * Constructor.
     * @param newDaoFactory factory to be set to {@link #daoFactory}
     * */
    AbstractService(final DaoFactory newDaoFactory) {
        daoFactory = newDaoFactory;
    }
    /**
     * @return {@link #daoFactory}
     * */
    DaoFactory getDaoFactory() {
        return daoFactory;
    }
}
