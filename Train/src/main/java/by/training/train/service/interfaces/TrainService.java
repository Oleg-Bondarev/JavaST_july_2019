package by.training.train.service.interfaces;

import java.util.List;

import by.training.train.entity.carriage.PassengerCarriage;

public interface TrainService {
    /**
     * @return tain - list of carriages.
     * */
    List<PassengerCarriage> getTrain();
    /**
     * @return total count of passengers.
     * */
    int getTotalPassengers();
    /**
     * @return total weight of baggage.
     * */
    double getTotalBaggage();
}
