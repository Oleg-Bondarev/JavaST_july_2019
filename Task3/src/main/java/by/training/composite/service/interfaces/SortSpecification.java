package by.training.composite.service.interfaces;

import by.training.composite.entity.Component;

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
     * @param newComponent -new compnent.
     * */
    void sort(Component newComponent);
}
