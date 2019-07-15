package by.training.train.validator;

import by.training.train.entity.carriage.CompartmentCarriage;
import by.training.train.entity.carriage.EconomClassCarriage;
import by.training.train.entity.carriage.SeatingCarriage;
import by.training.train.entity.enums.EconomClassEnum;
import by.training.train.entity.enums.PassengerCarriageEnum;
import by.training.train.entity.enums.ServiceEnum;
import by.training.train.validator.validExc.AmountException;
import by.training.train.validator.validExc.InformationInputException;

public class Validator {
    /**
     * Regex for string: name of the carriage type, comfort type of
     * the carriage, is TV on a board(boolean True/False), type of
     * the econom-class carriage.
     * */
    private static final String REGEX_FOR_STRING = "[a-zA-Z]+";
    /**
     * Regex for integer parameter: carriage brigade, count of passengers in
     * the carriage, count of compartments, count of bays, count of siting
     * places in the restaurant carriage.*/
    private static final String REGEX_FOR_INTEGER = "\\d+";
    /**
     * Regex for double parameter: weighth of baggage per passenger.
     * */
    private static final String REGEX_FOR_DOUBLE = "\\d+(\\.\\d+)?";
    /**
     * Regex for split of the text file.
     * */
    private static final String REGEX_FOR_SPLIT = ",";
    /**
     * Count of parameters in compartment carriage.
     * */
    private static final int COMPARTMENT_COUNT = 7;
    /**
     * Count of parameters in econom-class carriage.
     * */
    private static final int ECONOM_COUNT = 8;
    /**
     * Count of parameters in siting carriage.
     * */
    private static final int SITING_COUNT = 7;
    /**
     * Count of parameters in restaurant carriage.
     * */
    private static final int RESTAURANT_COUNT = 7;
    /**
     * Check String.
     * @param testStr - string that we want to check.
     * @exception InformationInputException - incorrect data in source.
     * @return boolean result of string validate.
     * */
    public boolean validateStr(final String testStr)
            throws InformationInputException {
        if (!testStr.matches(REGEX_FOR_STRING)) {
            throw new InformationInputException(testStr
                    + " must match the string pattern.");
        } else {
            return true;
        }
    }
    /**
     * Check integer.
     * @param testStr - string that we want to check.
     * @exception InformationInputException - incorrect data in source.
     * @return boolean result of string validate.
     * */
    public boolean validateInt(final String testStr)
            throws InformationInputException {
        if (!testStr.matches(REGEX_FOR_INTEGER)) {
            throw new InformationInputException(testStr
                    + " must match the integer pattern.");
        } else {
            return true;
        }
    }
    /**
     * Check double.
     * @param testStr - string that we want to check.
     * @exception InformationInputException - incorrect data in source.
     * @return boolean result of string validate.
     * */
    public boolean validateDouble(final String testStr)
            throws InformationInputException {
        if (!testStr.matches(REGEX_FOR_DOUBLE)) {
            throw new InformationInputException(testStr
                    + " must match the double pattern.");
        } else {
            return true;
        }
    }
    /**
     * Check boolean.
     * @param testStr - string that we want to check.
     * @exception InformationInputException - incorrect data in source.
     * @return boolean result of string validate.
     * */
    public boolean validateBoolean(final String testStr)
            throws InformationInputException {
        if (!testStr.matches(REGEX_FOR_STRING)) {
            throw new InformationInputException(testStr
                    + " must match the string pattern.");
        } else if (testStr.toLowerCase().equals("true")
                || testStr.toLowerCase().equals("false")) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Validator for passenger carriage.
     * @param inputInformation - array with innpur information for create
     * the object.
     * @return boolean result of string validate.
     * @throws InformationInputException incorrect parameters of the object.
     * @throws IllegalArgumentException if some enum doesn't contain the string.
     * */
    public boolean validatePassengerCarriage(final String[] inputInformation)
            throws InformationInputException {
        if ((validateStr(inputInformation[0])
            && PassengerCarriageEnum.isInEnum(inputInformation[0]))
                && validateInt(inputInformation[1])
                && validateInt(inputInformation[2])
                && (validateStr(inputInformation[3])
                    && ServiceEnum.isInEnum(inputInformation[3]))
                && validateDouble(inputInformation[4])
                && validateBoolean(inputInformation[5])) {
            return true;
        } else {
            throw new InformationInputException("Incorrect input information.");
        }
    }
    /**
     * Validator for compartment carriage.
     * @param input - input information for create the object.
     * @throws AmountException if we have incorrect number of parameters.
     * @throws InformationInputException incorrect parameters of the object.
     * @return boolean result of string validate.
     * */
    public boolean validateCompartment(final String input)
            throws AmountException, InformationInputException {
        String[] inputInformation = input.split(REGEX_FOR_SPLIT);
        if (inputInformation.length == COMPARTMENT_COUNT) {
            if (validatePassengerCarriage(inputInformation)
                    && validateInt(inputInformation[6])) {
                if (Double.parseDouble(inputInformation[4])
                        <= CompartmentCarriage.MAX_BAGGAGE_WEIGHTH
                        && Integer.parseInt(inputInformation[6])
                        <= CompartmentCarriage.DEFAULT_COUNT_OF_COMPARTMENT
                        && Integer.parseInt(inputInformation[6]) > 0) {
                    return true;
                }
            }
        } else {
            throw new AmountException("Incorrect count of paramrters for for"
                    + " object of compartment carriage.");
        }
        throw new InformationInputException("Incorrect input information.");
    }
    /**
     * Validator for econom-class carriage.
     * @param input - innpur information for create
     * the object.
     * @throws AmountException if we have incorrect number of parameters.
     * @throws InformationInputException incorrect parameters of the object.
     * @return boolean result of string validate.
     * */
    public boolean validateEconomClass(final String input)
            throws AmountException, InformationInputException {
        String[] inputInformation = input.trim().split(REGEX_FOR_SPLIT);
        if (inputInformation.length == ECONOM_COUNT) {
            if (validatePassengerCarriage(inputInformation)
                && validateInt(inputInformation[6])
                && validateStr(inputInformation[7])) {
                if (Double.parseDouble(inputInformation[4])
                        <= EconomClassCarriage.MAX_BAGGAGE_WEIGHTH
                    && Integer.parseInt(inputInformation[6])
                        <= EconomClassCarriage.DEFAULT_COUNT_OF_BAYS
                    && EconomClassEnum.isInEnum(inputInformation[7])) {
                    return true;
                }
            }
        } else {
            throw new AmountException("Incorrect count of paramrters for for"
                    + " object of econom-class carriage.");
        }
        throw new InformationInputException("Incorrect input information.");
    }
    /**
     * Validator for seating carriage.
     * @param input -  innpur information for create
     * the object.
     * @throws AmountException if we have incorrect number of parameters.
     * @throws InformationInputException incorrect parameters of the object.
     * @return boolean result of string validate.
     * */
    public boolean validateSeating(final String input)
            throws AmountException, InformationInputException {
        String[] inputInformation = input.trim().split(REGEX_FOR_SPLIT);
        if (inputInformation.length == SITING_COUNT) {
            if (validatePassengerCarriage(inputInformation)
                    && validateBoolean(inputInformation[6])) {
                if (Double.parseDouble(inputInformation[4])
                        <= SeatingCarriage.MAX_BAGGAGE_WEIGHTH) {
                    return true;
                }
            }
        } else {
            throw new AmountException("Incorrect count of paramrters for for"
                    + " object of siting carriage.");
        }
        throw  new InformationInputException("Incorrect input information.");
    }
    /**
     * Validator for restaurant carriage.
     * @param input - innpur information for create
     * the object.
     * @throws AmountException if we have incorrect number of parameters.
     * @throws InformationInputException incorrect parameters of the object.
     * @return boolean result of string validate.
     * */
    public boolean validateRestaurant(final String input)
            throws AmountException, InformationInputException {
        String[] inputInformation = input.trim().split(REGEX_FOR_SPLIT);
        if (inputInformation.length == COMPARTMENT_COUNT) {
            if (validatePassengerCarriage(inputInformation)
                    && validateInt(inputInformation[6])) {
                if (Integer.parseInt(inputInformation[2]) == 0
                    && Double.parseDouble(inputInformation[4]) == 0.0
                    && Integer.parseInt(inputInformation[6]) > 0) {
                    return true;
                }
            }
        } else {
            throw new AmountException("Incorrect count of paramrters for for"
                    + " object of restaurant carriage.");
        }
        throw new InformationInputException("Incorrect input information.");
    }
}
