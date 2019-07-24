package by.training.train.dao.impl;

import java.util.List;
import java.util.Optional;

import by.training.train.dao.CarriageDAO;
import by.training.train.dao.PassengerTrainRepository;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.service.specification.interfaces.FindSpecification;
import by.training.train.service.specification.interfaces.SortSpecification;
import by.training.train.service.specification.interfaces.Specification;

public class PassengerCarriageDAO implements CarriageDAO {
    /**
     * Object of Train.
     * */
    private PassengerTrainRepository passTrain =
                    PassengerTrainRepository.getInstance();
    /**
     * Add carriage to train.
     * @param newCarriage - object of TrainCarriage.
     * */
    @Override
    public void addCarriage(final Optional<PassengerCarriage> newCarriage) {
        passTrain.addCarriage(newCarriage);
    }
    /**
     * Delete carriage from train.
     * @param carriage - carriage of the train.
     * */
    @Override
    public void removeCarriage(final Optional<PassengerCarriage> carriage) {
        passTrain.removeCarriage(carriage);
    }
    /**
     * Do some work that depends from type of specification.
     * @param newSpecification - type of specification.
     * @return list with carriages.
     * */
    @Override
    public List<PassengerCarriage> query(final Specification newSpecification) {
        List<PassengerCarriage> passengerCarriageList = null;

        if (newSpecification instanceof FindSpecification) {
            passengerCarriageList = ((FindSpecification) newSpecification).
                                find(passTrain.getTrainCarriageList());
        } else if (newSpecification instanceof SortSpecification) {
            passengerCarriageList = ((SortSpecification) newSpecification).
                                sort(passTrain.getTrainCarriageList());
        }
        return passengerCarriageList;
    }
}
