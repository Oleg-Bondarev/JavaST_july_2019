package by.training.train.dao;

import java.util.HashMap;
import java.util.Map;

import by.training.train.dao.exception.DAOException;
import by.training.train.dao.factory.CarriageFactoryInterface;
import by.training.train.dao.factory.CompartmentCarriageFactory;
import by.training.train.dao.factory.EconomClassCarriageFactory;
import by.training.train.dao.factory.RestaurantCarriageFactory;
import by.training.train.dao.factory.SeatingCarriageFactory;
import by.training.train.entity.enums.PassengerCarriageEnum;

public class PassengerCarriageProvider {
    /**
     * Map to use special factory.
     */
    private final Map<PassengerCarriageEnum, CarriageFactoryInterface>
            mapOfCarriages = new HashMap<>();
    /**
     * Input our classes in map.
     * */
    public PassengerCarriageProvider() {
        mapOfCarriages.put(PassengerCarriageEnum.COMPARTMENT,
                CompartmentCarriageFactory.getInstance());
        mapOfCarriages.put(PassengerCarriageEnum.ECONOMCLASS,
                EconomClassCarriageFactory.getInstance());
        mapOfCarriages.put(PassengerCarriageEnum.SEATING,
                SeatingCarriageFactory.getInstance());
        mapOfCarriages.put(PassengerCarriageEnum.RESTAURANT,
                RestaurantCarriageFactory.getInstance());
    }
    /**
     * @param carriageType - carriage type factory which we want to get.
     * @return some carriage factory.
     * @throws DAOException if we get unknown name of passenger carriage class.
     * */
    public CarriageFactoryInterface getCarriageFactory(final
                                                        String carriageType)
            throws DAOException {
        PassengerCarriageEnum typeOfCarriage;
        CarriageFactoryInterface carriageFactory;
        try {
            typeOfCarriage = PassengerCarriageEnum.
                    valueOf(carriageType.toUpperCase());
            carriageFactory = mapOfCarriages.get(typeOfCarriage);
        } catch (NullPointerException | IllegalArgumentException exc) {
            throw new DAOException("Unknown type of passenger carriage: "
                    + carriageType);
        }
        return carriageFactory;
    }
}
