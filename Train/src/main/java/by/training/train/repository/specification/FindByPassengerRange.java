package by.training.train.repository.specification;

import java.util.ArrayList;
import java.util.List;

import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.entity.carriage.RestaurantCarriage;

public class FindByPassengerRange
        implements FindSpecification<PassengerCarriage> {
    /**
     * Lower bound of the number of passengers.
     * */
    private int lowerPassengerBound;
    /**
     * Upper bound of the number of passengers.
     * */
    private int upperPassengerBound;
    /**
     * @param lowerNew - lower bound of the number of passengers.
     * @param upperNew - upper bound of the number of passengers.
     * */
    public FindByPassengerRange(final int lowerNew, final int upperNew) {
        this.lowerPassengerBound = lowerNew;
        this.upperPassengerBound = upperNew;
    }
    /**
     * @param list - list of carriages for search.
     * @return the result of search.
     * */
    @Override
    public List<PassengerCarriage> find(final List<PassengerCarriage> list) {
        List<PassengerCarriage> resultList = new ArrayList<>(0);
        for (PassengerCarriage obj : list) {
            if (this.lowerPassengerBound < 0 || this.upperPassengerBound < 0) {
                continue;
            }
            if ((obj.getCountOfPassengers() >= this.lowerPassengerBound)
                && (obj.getCountOfPassengers() >= this.upperPassengerBound)) {
                resultList.add(obj);
            }
        }
        return resultList;
    }
}
