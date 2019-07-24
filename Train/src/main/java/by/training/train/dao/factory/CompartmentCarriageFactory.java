package by.training.train.dao.factory;

import by.training.train.dao.exception.DAOException;
import by.training.train.entity.carriage.CompartmentCarriage;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.entity.enums.ServiceEnum;
import by.training.train.validator.Validator;

public final class CompartmentCarriageFactory
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
     * Instance of the class.
     * */
    private static final CompartmentCarriageFactory INSTANCE =
            new CompartmentCarriageFactory();
    private CompartmentCarriageFactory() { }
    /**
     * @return class instance.
     * */
    public static CompartmentCarriageFactory getInstance() {
        return INSTANCE;
    }
    /**
     * @param initParameters parameters to create carriage.
     * @return carriage of the train.
     * */
    @Override
    public PassengerCarriage createTrainCarriage(final String[] initParameters)
            throws DAOException {
        Validator validator = new Validator();

        if (!validator.validateCompartment(initParameters)) {
            throw new DAOException("Incorrect data to create an object of "
                    + "compartment carriage.");
        }

        int brigade = Integer.parseInt(initParameters[1]);
        int countPassengers = Integer.parseInt(initParameters[2]);
        ServiceEnum servicetype =
                ServiceEnum.valueOf(initParameters[THREE].toUpperCase());
        double baggage = Double.parseDouble(initParameters[FOUR]);
        boolean hasConditioner =
                Boolean.valueOf(initParameters[FIVE].toLowerCase());
        int countCompartments = Integer.parseInt(initParameters[SIX]);
        return new CompartmentCarriage(brigade, servicetype, countPassengers,
                                    baggage, hasConditioner, countCompartments);
    }
}
