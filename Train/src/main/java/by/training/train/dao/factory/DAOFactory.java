package by.training.train.dao.factory;

import by.training.train.dao.CarriageDAO;
import by.training.train.dao.impl.PassengerCarriageDAO;

public final class DAOFactory {
    /**
     * Instance of dao factory.
     * */
    private static final DAOFactory INSTANCE = new DAOFactory();
    /**
     * Object references class that implements the required dao interfaces.
     * */
    private final CarriageDAO fileCarriageDAO = new PassengerCarriageDAO();
    /**
     * Default constructor DAOFactory.
     * */
    private DAOFactory() { }
    /**
     * @return instance of the dao factory.
     * */
    public static DAOFactory getInstance() {
        return INSTANCE;
    }
    /**
     * @return object references class that implements
     *  the required dao interfaces.
     *  */
    public CarriageDAO getCarriageDAO() {
        return fileCarriageDAO;
    }
}
