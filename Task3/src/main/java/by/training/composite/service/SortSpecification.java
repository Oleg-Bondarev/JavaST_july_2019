package by.training.composite.service;

import java.util.Comparator;

/**
 * Sort specification interface.
 * @param <T> -type.
 * */
public interface SortSpecification<T> extends Specification<T> {
    /**
     * Getter for comparator.
     * @return comparator.
     * */
    Comparator<T> getComparator();
    /**
     * Sorting.
     * //TODO sort
     * */
}
