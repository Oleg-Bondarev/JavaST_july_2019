package by.training.train.service.specification.interfaces;

import java.util.Comparator;
import java.util.List;

import by.training.train.entity.carriage.PassengerCarriage;

public interface SortSpecification<T> extends Specification<T> {
    /**
     * Getter for comparator.
     * @return comparator.
     * */
    Comparator<T> getComparator();
    /**
     * Method of sorting.
     * @param list - list.
     * @return sorting list.
     * */
    List<PassengerCarriage> sort(List<PassengerCarriage> list);
}
