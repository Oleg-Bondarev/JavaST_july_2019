package by.training.train.repository.specification;

import java.util.ArrayList;
import java.util.List;

import by.training.train.entity.carriage.PassengerCarriage;

public class FindByBaggageSpecification
        implements FindSpecification<PassengerCarriage> {
    /**
     * Baggage lower border.
     * */
    private double lowerBorder;
    /**
     * Baggage upper border.
     * */
    private double upperBorder;
    /**
     * @param lower - lower border of baggage weight.
     * @param upper - upper border of baggage weight.
     * */
    public FindByBaggageSpecification(final double lower, final double upper) {
        this.lowerBorder = lower;
        this.upperBorder = upper;
    }
    /**
     * @param list - list of carriages for search.
     * @return the result of search.
     * */
    @Override
    public List<PassengerCarriage> find(final List<PassengerCarriage> list) {
        List<PassengerCarriage> resultList = new ArrayList<>(0);
        for (PassengerCarriage obj : list) {
            if (this.lowerBorder < 0 || this.upperBorder < 0) {
                continue;
            }
            if ((obj.getBagageOnPassenger() >= this.lowerBorder)
                    && (obj.getBagageOnPassenger() >= this.upperBorder)) {
                resultList.add(obj);
            }
        }
        return resultList;
    }
}
