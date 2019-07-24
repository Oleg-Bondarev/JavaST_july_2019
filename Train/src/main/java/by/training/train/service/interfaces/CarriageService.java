package by.training.train.service.interfaces;

import java.util.List;

import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.service.exception.ServiceException;
import by.training.train.service.specification.interfaces.Specification;

public interface CarriageService {
    /**
     * Add carriage to train.
     * @param initArguments - parameters to create object of some super class of
     *                      the PassengerCarriage class.
     * @throws ServiceException -
     * */
    void addCarriage(String initArguments) throws ServiceException;
    /**
     * Remove carriage from train.
     * @param trainCarriageNew - object of some PassengerCarriage super class.
     * */
    void removeCarriage(PassengerCarriage trainCarriageNew);
    /**
     * List of the query.
     * @param specificationNew - some type of specification.
     * @return list with result of work according to some specification.
     * */
    List<PassengerCarriage> query(Specification specificationNew);
}
