package by.training.train.service.impl;

import java.util.List;

import by.training.train.dao.PassengerTrainRepository;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.service.interfaces.TrainService;

public class TrainServiceImpl implements TrainService {
    /***/
    private PassengerTrainRepository passengerTrainRepository =
            PassengerTrainRepository.getInstance();
    /**
     * @return train - list of carriages.
     * */
    @Override
    public List<PassengerCarriage> getTrain() {
        return passengerTrainRepository.getTrainCarriageList();
    }
    /**
     * @return total count of passengers.
     * */
    @Override
    public int getTotalPassengers() {
        return passengerTrainRepository.getTotalPassengers();
    }
    /**
     * @return total count of passengers.
     * */
    @Override
    public double getTotalBaggage() {
        return passengerTrainRepository.getTotalBaggage();
    }
}
