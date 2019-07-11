package by.training.train.repository;

public final class DAOFactory {
    /***/
    private static final DAOFactory INSTANCE = new DAOFactory();
    /***/
    private final CarriageDAO trainCarriageImpl = new TrainCarriageDAO();
    /***/
    private DAOFactory() { }
    /**@return instance of the DAO factory.*/
    public static DAOFactory getInstance() {
        return INSTANCE;
    }
    /**@return object references class that implements
     *  the required DAO interface*/
    public CarriageDAO getCarriageDAO() {
        return trainCarriageImpl;
    }
}
