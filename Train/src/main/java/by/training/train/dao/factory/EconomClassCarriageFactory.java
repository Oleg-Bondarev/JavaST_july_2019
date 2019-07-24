package by.training.train.dao.factory;

import by.training.train.dao.exception.DAOException;
import by.training.train.entity.carriage.EconomClassCarriage;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.entity.enums.EconomClassEnum;
import by.training.train.entity.enums.ServiceEnum;
import by.training.train.validator.Validator;

public final class EconomClassCarriageFactory
        implements CarriageFactoryInterface {
    /***/
    private static final int THREE = 3;
    /***/
    private static final int FOUR = 4;
    /***/
    private static final int FIVE = 5;
    /***/
    private static final int SIX = 6;
    /***/
    private static final int SEVEN = 7;
    /**
     * Instance of factory.
     * */
    private static final EconomClassCarriageFactory INSTANCE =
            new EconomClassCarriageFactory();
    private EconomClassCarriageFactory() { }
    /**
     * @return instance.
     * */
    public static EconomClassCarriageFactory getInstance() {
        return INSTANCE;
    }
    @Override
    public PassengerCarriage createTrainCarriage(final String[] initParameters)
            throws DAOException {
        Validator validator = new Validator();

        if (!validator.validateEconomClass(initParameters)) {
            throw new DAOException("Incorrect data to create an object of "
                    + "econom-class carriage.");
        }

        int brigade = Integer.parseInt(initParameters[1]);
        int countPassengers = Integer.parseInt(initParameters[2]);
        ServiceEnum serviceType =
                ServiceEnum.valueOf(initParameters[THREE].toUpperCase());
        double baggage = Double.parseDouble(initParameters[FOUR]);
        boolean hasConditioner =
                Boolean.valueOf(initParameters[FIVE].toLowerCase());
        int countBays = Integer.parseInt(initParameters[SIX]);
        EconomClassEnum economClassType =
                EconomClassEnum.valueOf(initParameters[SEVEN].toUpperCase());
        return new EconomClassCarriage(brigade, serviceType, countPassengers,
                                    baggage, hasConditioner, countBays,
                                    economClassType);
    }
}
