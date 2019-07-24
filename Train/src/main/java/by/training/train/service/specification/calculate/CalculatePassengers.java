package by.training.train.service.specification.calculate;

import java.util.List;
import java.util.Optional;

import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.entity.carriage.RestaurantCarriage;

public class CalculatePassengers {
    /**
     * @param list - list of carriages.
     * @return total count of passengers.
     * */
    public int calculatePassengers(final
    Optional<List<PassengerCarriage>> list) {
        if (list.isPresent()) {
            int count = 0;
            for (PassengerCarriage obj : list.get()) {
                if (obj instanceof RestaurantCarriage) {
                    continue;
                } else {
                    count += obj.getCountOfPassengers();
                }
            }
            return count;
        } else {
            return 0;
        }
    }
}
