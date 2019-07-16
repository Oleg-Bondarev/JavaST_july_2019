package by.training.train.repository.specification;

import java.util.Comparator;
import java.util.List;

import by.training.train.entity.carriage.PassengerCarriage;

public class SortByCountPassengersSpecification
                            implements SortSpecification<PassengerCarriage> {
    /**
     * Getter for comparator.
     * @return comparator.
     */
    @Override
    public Comparator<PassengerCarriage> getComparator() {
        return Comparator.comparing(PassengerCarriage::getCountOfPassengers);
    }

    /**
     * @param list - start up list for sorting.
     * @return sort list.
     **/
    @Override
    public List<PassengerCarriage> sort(final List<PassengerCarriage> list) {
        list.sort(this.getComparator());
        return list;
    }
}
