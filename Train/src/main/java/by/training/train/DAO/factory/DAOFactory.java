package by.training.train.DAO.factory;

import by.training.train.DAO.FileCarriageDAO;
import by.training.train.DAO.impl.CarriageDAO;

public final class DAOFactory {
    /**
     * Instance of DAO factory.
     * */
    private static final DAOFactory INSTANCE = new DAOFactory();
    /**
     * Object references class that implements the required DAO interface.
     * */
    private final CarriageDAO fileCarriageImpl = new FileCarriageDAO();
    /**
     * Default constructor DAOFactory.
     * */
    private DAOFactory() { }
    /**
     * @return instance of the DAO factory.
     * */
    public static DAOFactory getInstance() {
        return INSTANCE;
    }
    /**
     * @return object references class that implements
     *  the required DAO interface.
     *  */
    public CarriageDAO getCarriageDAO() {
        return fileCarriageImpl;
    }
}
