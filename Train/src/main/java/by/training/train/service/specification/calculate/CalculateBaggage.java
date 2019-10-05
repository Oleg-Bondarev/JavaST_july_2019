package by.training.train.service.specification.calculate;

import java.util.List;
import java.util.Optional;

import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.entity.carriage.RestaurantCarriage;

public class CalculateBaggage {
    /**
     * @param list - list of carriages.
     * @return weight of baggage.
     * */
    public double calculateBaggage(final
                                    Optional<List<PassengerCarriage>> list) {
        if (list.isPresent()) {
            double weigh = 0;
            for (PassengerCarriage obj : list.get()) {
                if (obj instanceof RestaurantCarriage) {
                    continue;
                } else {
                    weigh += obj.getBaggageOnPassenger()
                            * obj.getCountOfPassengers();
                }
            }
            return weigh;
        } else {
            return 0.0;
        }
    }
}
