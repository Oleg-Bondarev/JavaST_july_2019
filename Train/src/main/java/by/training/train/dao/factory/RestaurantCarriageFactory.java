package by.training.train.dao.factory;

import by.training.train.dao.exception.DAOException;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.entity.carriage.RestaurantCarriage;
import by.training.train.entity.enums.ServiceEnum;
import by.training.train.validator.Validator;

public final class RestaurantCarriageFactory
                    implements CarriageFactoryInterface {
    /***/
    private static final int THREE = 3;
    /***/
    private static final int FOUR = 4;
    /***/
    private static final int FIVE = 5;
    /***/
    private static final int SIX = 6;
    /**
     * Instance of factory.
     * */
    private static final RestaurantCarriageFactory INSTANCE =
            new RestaurantCarriageFactory();
    private RestaurantCarriageFactory() { }
    /**
     * @return instance.
     * */
    public static RestaurantCarriageFactory getInstance() {
        return INSTANCE;
    }
    @Override
    public PassengerCarriage createTrainCarriage(final String[] initParameters)
            throws DAOException {
        Validator validator = new Validator();

        if (!validator.validateRestaurant(initParameters)) {
            throw new DAOException("Incorrect data to create an object of "
                    + "restaurant carriage.");
        }

        int brigade = Integer.parseInt(initParameters[1]);
        ServiceEnum serviceType =
                ServiceEnum.valueOf(initParameters[THREE].toUpperCase());
        double baggage = Double.parseDouble(initParameters[FOUR]);
        boolean hasConditioner =
                Boolean.valueOf(initParameters[FIVE].toLowerCase());
        int countPlaces = Integer.parseInt(initParameters[SIX]);
        return new RestaurantCarriage(brigade, serviceType, baggage,
                hasConditioner, countPlaces, countPlaces);
    }
}
