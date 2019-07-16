package by.training.train.repository.specification;

import java.util.List;

import by.training.train.entity.carriage.PassengerCarriage;

public interface FindSpecification<T> extends Specification<T> {
    /**
     * @param list - start up list for search.
     * @return list with results of the search.
     * */
    List<PassengerCarriage> find(List<PassengerCarriage> list);
}
