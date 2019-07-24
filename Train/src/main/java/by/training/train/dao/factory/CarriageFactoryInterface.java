package by.training.train.dao.factory;

import by.training.train.dao.exception.DAOException;
import by.training.train.entity.carriage.PassengerCarriage;

public interface CarriageFactoryInterface {
    /**
     * @param initParameters parameters to create carriage.
     * @throws DAOException - if we have some problems with input information.
     * @return carriage of the train.
     * */
    PassengerCarriage createTrainCarriage(String[] initParameters)
            throws DAOException;
}
