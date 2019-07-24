package by.training.train.dao;

import java.util.List;
import java.util.Optional;

import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.service.specification.interfaces.Specification;

public interface CarriageDAO {
    /**
     * Add carriage to the train.
     * @param newCarriage - new carriage.
     * */
    void addCarriage(Optional<PassengerCarriage> newCarriage);
    /**
     * Remove carriage from the train.
     * @param carriage - carriage that we want to remove.
     * */
    void removeCarriage(Optional<PassengerCarriage> carriage);
    /**
     * List of the query to do.
     * @param newSpecification - some specification.
     * @return result of some work that depends from specification.
     * */
    List<PassengerCarriage> query(Specification newSpecification);
}

