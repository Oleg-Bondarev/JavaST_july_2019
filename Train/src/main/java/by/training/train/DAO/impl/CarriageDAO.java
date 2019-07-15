package by.training.train.DAO.impl;

import by.training.train.entity.carriage.TrainCarriage;

public interface CarriageDAO {
    /**
     * Add carriage to train.
     * @param carriage - object of TrainCarriage.
     * */
    void addCarriage(TrainCarriage carriage);
    /**
     * Delete carriage from train by ID.
     * @param id - train carriage ID.
     * */
    void deleteCarriageByID(int id);
    /**
     * Delete carriage.
     * @param carriage - carriage of the train.
     * */
    void deleteCarriage(TrainCarriage carriage);
    /**
     * Find the carriage by ID.
     * @param id - carriage ID.
     * @return carriage.
     * */
    TrainCarriage findCarriageyID(int id);
    /**
     * Find the carriage by ID.
     * @param carriage - carriage of the train.
     * @return carriage.
     * */
    TrainCarriage findCarriage(TrainCarriage carriage);
}
