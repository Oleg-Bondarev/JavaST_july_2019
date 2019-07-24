package by.training.train.validator;

import by.training.train.entity.carriage.CompartmentCarriage;
import by.training.train.entity.carriage.EconomClassCarriage;
import by.training.train.entity.carriage.SeatingCarriage;
import by.training.train.entity.enums.EconomClassEnum;
import by.training.train.entity.enums.PassengerCarriageEnum;
import by.training.train.entity.enums.ServiceEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Validator {
    /**
     * Logger.
     * */
    static final Logger LOGGER = LogManager.getLogger(Validator.class);
    /**
     * Regex for string: name of the carriage type, comfort type of
     * the carriage, is TV on a board(boolean True/False), type of
     * the econom-class carriage.
     */
    private static final String REGEX_FOR_STRING = "[a-zA-Z]+";
    /**
     * Regex for integer parameter: carriage brigade, count of passengers in
     * the carriage, count of compartments, count of bays, count of siting
     * places in the restaurant carriage.
     */
    private static final String REGEX_FOR_INTEGER = "\\d+";
    /**
     * Regex for double parameter: weighth of baggage per passenger.
     */
    private static final String REGEX_FOR_DOUBLE = "\\d+(\\.\\d+)?";
    /**
     * Regex for string with train route.
     * */
    private static final String REGEX_FOR_ROUTE = "[a-zA-Z-]+";
    /**
     * Count of parameters in compartment carriage.
     */
    private static final int COMPARTMENT_COUNT = 7;
    /**
     * Count of parameters in econom-class carriage.
     */
    private static final int ECONOM_COUNT = 8;
    /**
     * Count of parameters in siting carriage.
     */
    private static final int SEATING_COUNT = 7;
    /**
     * Count of parameters in restaurant carriage.
     */
    private static final int RESTAURANT_COUNT = 7;
    /**
     * MAX count of staff in the carriage.
     */
    private static final int MAX_COUNT_STAFF = 15;
    /***/
    private static final int THREE = 3;
    /***/
    private static final int FOUR = 4;
    /***/
    private static final int FIIVE = 5;
    /***/
    private static final int SIX = 6;
    /***/
    private static final int SEVEN = 7;

    /**@param testStr -
     * @return result.*/
    public boolean validateStr(final String testStr) {
        return testStr.matches(REGEX_FOR_STRING);
    }
    /**@param testStr -
     * @return result.*/
    public boolean validateInt(final String testStr) {
        return testStr.matches(REGEX_FOR_INTEGER);
    }
    /**@param testStr -
     * @return result.*/
    public boolean validateDouble(final String testStr) {
        return testStr.matches(REGEX_FOR_DOUBLE);
    }
    /**@param testStr -
     * @return result.*/
    public boolean validateBoolean(final String testStr) {
        return testStr.toLowerCase().equals("true")
                || testStr.toLowerCase().equals("false");
    }
    /**@param testStr -
     * @return result.*/
    public boolean validateRoute(final String testStr) {
        return testStr.matches(REGEX_FOR_ROUTE);
    }

    /**
     * Validator for passenger carriage.
     * @param inputInformation - array with innpur information for create
     *                         the object.
     * @return boolean result of string validate.
     */
    public boolean validatePassengerCarriage(final String[] inputInformation) {
        if (!validateStr(inputInformation[0])) {
            LOGGER.error("Incorrect parameter: type of the"
                    + " passenger carriage. It must be string.");
            return false;
        }
        if (!PassengerCarriageEnum.isInEnum(inputInformation[0])) {
            LOGGER.error("Unknown parameter of passenger carriage"
                    + " class type: " + inputInformation[0]);
            return false;
        }
        if (!validateInt(inputInformation[1])) {
            LOGGER.error("Incorrect parameter: count of the carriage"
                    + " brigade: " + inputInformation[1]
                    + " It must be an integer number greater zero.");
            return false;
        }
        if (Integer.parseInt(inputInformation[1]) >= MAX_COUNT_STAFF) {
            LOGGER.error("Count of carriage brigade must be lower than "
                    + MAX_COUNT_STAFF);
            return false;
        }
        if (!validateStr(inputInformation[THREE])) {
            LOGGER.error("Incorrect parameter: service type: "
                    + inputInformation[THREE]
                    + ". It must be string.");
            return false;
        }
        if (!ServiceEnum.isInEnum(inputInformation[THREE])) {
            LOGGER.error("Unknown parameter of service type: "
                    + inputInformation[THREE]);
            return false;
        }
        if (!validateInt(inputInformation[2])) {
            LOGGER.error("Incorrect parameter: count of passengers: "
                    + inputInformation[2]
                    + "It must be an integer number greater zero.");
            return false;
        }
        if (Integer.parseInt(inputInformation[2]) < 0) {
            LOGGER.error("Incorrect parameter: count of passengers: "
                    + inputInformation[2]
                    + ". It must be an integer number greater zero.");
            return false;
        }
        if (!validateDouble(inputInformation[FOUR])) {
            LOGGER.error("Incorrect parameter: " + inputInformation[FOUR]
                    + "It must be double number greater zero.");
            return false;
        }
        if (Double.parseDouble(inputInformation[FOUR]) < 0) {
            LOGGER.error("Incorrect parameter: " + inputInformation[FOUR]
                    + "It must be double number greater zero.");
            return false;
        }
        if (!validateBoolean(inputInformation[FIIVE])) {
            LOGGER.error("Incorrect parameter: "
                    + inputInformation[FIIVE]
                    + ". It must be an boolean value.");
            return false;
        }
        return true;
    }

    /**
     * Validator for compartment carriage.
     * @param inputInformation - input information for create the object.
     * @return boolean result of string validate.
     */
    public boolean validateCompartment(final String[] inputInformation) {
        if (inputInformation.length == COMPARTMENT_COUNT) {
            if (validatePassengerCarriage(inputInformation)
                    && validateInt(inputInformation[SIX])) {
                if (Integer.parseInt(inputInformation[2])
                        <= CompartmentCarriage.DEFAULT_COUNT_OF_COMPARTMENT
                        * CompartmentCarriage.DEFAULT_PLACES_IN_COMPARTMENT
                        && Double.parseDouble(inputInformation[FOUR])
                        <= CompartmentCarriage.MAX_BAGGAGE_WEIGHTH
                        && Integer.parseInt(inputInformation[SIX])
                        <= CompartmentCarriage.DEFAULT_COUNT_OF_COMPARTMENT
                        && Integer.parseInt(inputInformation[SIX]) > 0) {
                    return true;
                } else {
                    LOGGER.error("Check parameters:"
                            + " count of passengers=" + inputInformation[2]
                            + " and baggage weight=" + inputInformation[SIX]);
                    return false;
                }
            }
        }
        LOGGER.error("Incorrect number of parameters to create"
                + " compartment carriage. Must be " + COMPARTMENT_COUNT
                + ". Found " + inputInformation.length);
        return false;
    }

    /**
     * Validator for econom-class carriage.
     * @param inputInformation - innpur information for create
     *              the object.
     * @return boolean result of string validate.
     */
    public boolean validateEconomClass(final String[] inputInformation) {
        int countPassengers;
        if (inputInformation.length == ECONOM_COUNT) {
            if (validatePassengerCarriage(inputInformation)
                    && validateInt(inputInformation[SIX])
                    && validateStr(inputInformation[SEVEN])) {
                if (Double.parseDouble(inputInformation[FOUR])
                        > EconomClassCarriage.MAX_BAGGAGE_WEIGHTH) {
                    LOGGER.error("Incorrect parameter: baggage="
                        + inputInformation[FOUR] + ". It must be lower than "
                        + EconomClassCarriage.MAX_BAGGAGE_WEIGHTH);
                    return false;
                }
                if (Integer.parseInt(inputInformation[SIX])
                        > EconomClassCarriage.DEFAULT_COUNT_OF_BAYS) {
                    LOGGER.error("Incorrect parameter: count of bays="
                        + inputInformation[SIX] + ". It must be lower than "
                        + EconomClassCarriage.DEFAULT_COUNT_OF_BAYS);
                    return false;
                }
                countPassengers = EconomClassCarriage.
                        defaultCountOfPassengers(EconomClassEnum.
                                getType(inputInformation[SEVEN]))
                        * Integer.parseInt(inputInformation[SIX]);
                if (Integer.parseInt(inputInformation[2])
                        > countPassengers) {
                    LOGGER.error("Incorrect parameter: count of"
                            + " passengers:" + inputInformation[2]
                            + ". It must be lower than " + countPassengers);
                    return false;
                }
                return true;
            }
        }
        LOGGER.error("Incorrect number of parameters to create"
                + " econom-class carriage. Must be " + ECONOM_COUNT
                + ". Found " + inputInformation.length);
        return false;
    }
    /**
     * Validator for seating carriage.
     * @param inputInformation -  innpur information for create
     * the object.
     * @return boolean result of string validate.
     * */
    public boolean validateSeating(final String[] inputInformation) {
        if (inputInformation.length == SEATING_COUNT) {
            if (validatePassengerCarriage(inputInformation)
                    && validateBoolean(inputInformation[SIX])) {
                if (Double.parseDouble(inputInformation[FOUR])
                        > SeatingCarriage.MAX_BAGGAGE_WEIGHTH) {
                    LOGGER.error("Incorrect parameter: "
                            + inputInformation[FOUR] + ". It must be lower"
                            + " than " + SeatingCarriage.MAX_BAGGAGE_WEIGHTH);
                    return false;
                }
                return true;
            } else {
                LOGGER.error("Incorrect parameter: "
                        + inputInformation[SIX]);
                return false;
            }
        }
        LOGGER.error("Incorrect number of parameters to create"
                + " seating carriage. Must be " + SEATING_COUNT
                + ". Found " + inputInformation.length);
        return false;
    }
    /**
     * Validator for restaurant carriage.
     * @param inputInformation - innpur information for create
     * the object.
     * @return boolean result of string validate.
     * */
    public boolean validateRestaurant(final String[] inputInformation) {
        if (inputInformation.length == RESTAURANT_COUNT) {
            if (validatePassengerCarriage(inputInformation)
                    && validateInt(inputInformation[SIX])) {
                if (Integer.parseInt(inputInformation[2]) == 0
                        && Double.parseDouble(inputInformation[FOUR]) == 0.0
                        && Integer.parseInt(inputInformation[SIX]) > 0) {
                    return true;
                } else {
                    LOGGER.error("Check parameters: count of passengers "
                            + inputInformation[2] + " and baggage weight "
                            + inputInformation[SIX] + " they must be equals"
                            + " zero. And parameter count of seating places "
                            + inputInformation + " must be greater zero.");
                    return false;
                }
            }
        }
        LOGGER.error("Incorrect number of parameters to create"
                + " restaurant carriage. Must be " + RESTAURANT_COUNT
                + ". Found " + inputInformation.length);
        return false;
    }
    /**
     * @param inputInformation - innpur information for create
     * the object.
     * @return boolean result of string validate.
     * */
    public boolean validateInformation(final String[] inputInformation) {
        boolean flag = false;
        if (!validateStr(inputInformation[0])) {
            LOGGER.error("Incorrect parameter: type of the"
                    + " passenger carriage. It must be string.");
            return false;
        }
        if (!PassengerCarriageEnum.isInEnum(inputInformation[0])) {
            LOGGER.error("Unknown parameter of passenger carriage"
                    + " class type: " + inputInformation[0]);
            return false;
        }
        switch (inputInformation[0].toUpperCase()) {
            case "COMPARTMENT":
                flag = validateCompartment(inputInformation);
                break;
            case "ECONOMCLASS":
                flag = validateEconomClass(inputInformation);
                break;
            case "SEATING":
                flag = validateSeating(inputInformation);
                break;
            case "RESTAURANT":
                flag = validateRestaurant(inputInformation);
                break;
                default:
                    break;
        }
        return flag;
    }
}
