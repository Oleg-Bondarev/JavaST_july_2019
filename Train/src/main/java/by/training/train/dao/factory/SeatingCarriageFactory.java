package by.training.train.dao.factory;

import by.training.train.dao.exception.DAOException;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.entity.carriage.SeatingCarriage;
import by.training.train.entity.enums.ServiceEnum;
import by.training.train.validator.Validator;

public final class SeatingCarriageFactory implements CarriageFactoryInterface {
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
    private static final SeatingCarriageFactory INSTANCE =
            new SeatingCarriageFactory();
    private SeatingCarriageFactory() { }
    /**
     * @return instance.
     * */
    public static SeatingCarriageFactory getInstance() {
        return INSTANCE;
    }
    @Override
    public PassengerCarriage createTrainCarriage(final String[] initParameters)
            throws DAOException {
        Validator validator = new Validator();

        if (!validator.validateSeating(initParameters)) {
            throw new DAOException("Incorrect data to create an object of "
                    + "seating carriage.");
        }

        int brigade = Integer.parseInt(initParameters[1]);
        int countPassengers = Integer.parseInt(initParameters[2]);
        ServiceEnum serviceType =
                ServiceEnum.valueOf(initParameters[THREE].toUpperCase());
        double baggage = Double.parseDouble(initParameters[FOUR]);
        boolean hasConditioner =
                Boolean.parseBoolean(initParameters[FIVE].toLowerCase());
        boolean hasTV = Boolean.parseBoolean(initParameters[SIX]);
        return new SeatingCarriage(brigade, serviceType, countPassengers,
                baggage, hasConditioner, hasTV);
    }
}
