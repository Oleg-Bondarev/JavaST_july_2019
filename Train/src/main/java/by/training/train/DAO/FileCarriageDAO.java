package by.training.train.DAO;

import by.training.train.entity.carriage.TrainCarriage;
import by.training.train.DAO.impl.CarriageDAO;

public class FileCarriageDAO implements CarriageDAO {
    /**
     * @param carriage - the carriage we want to add.
     * */
    @Override
    public void addCarriage(final TrainCarriage carriage) {

    }
    /**
     * @param id - carriage ID that we want to delete.
     * */
    @Override
    public void deleteCarriageByID(final int id) {

    }
    /**
     * @param carriage - the carriage we want to delete from train.
     * */
    @Override
    public void deleteCarriage(final TrainCarriage carriage) {

    }
    /**
     * @param id - carriage ID that we want to find.
     * */
    @Override
    public TrainCarriage findCarriageyID(final int id) {
        return null;
    }
    /**
     * @param carriage - the carriage, we want to find.
     * */
    @Override
    public TrainCarriage findCarriage(final TrainCarriage carriage) {
        return null;
    }
}
