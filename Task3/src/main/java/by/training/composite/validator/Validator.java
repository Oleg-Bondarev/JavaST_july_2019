package by.training.composite.validator;

/**
 * Validator class.
 * */
public final class Validator {
    /**
     * Constructor.
     * */
    private Validator() { }
    /**
     * Is correct input for sorting lexemes by character.
     * @param tempStr -string for check.
     * @return boolean value.
     * */
    public static boolean isCorrectInputCharacter(final String tempStr) {
        boolean flag = false;
        if (tempStr.length() == 1) {
            flag = true;
        }
        return flag;
    }
}
