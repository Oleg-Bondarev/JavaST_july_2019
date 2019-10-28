package by.training.final_task.service.validator;

public class PropertyValidator {
    public static boolean isValidIntegerPropParameters(final int startSize,
                                                       final int maxSize,
                                                       final int timeout) {
        if ((startSize <= 0) || (maxSize <= 0) || (timeout < 0)
                || (startSize > maxSize)) {
            return false;
        }
        return true;
    }
}
