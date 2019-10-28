package by.training.final_task.service.parser;

import by.training.final_task.action.Action;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public abstract class FormParser<T> {

    private static final Logger LOGGER = LogManager.getLogger();

    public abstract T parse(final Action newAction,
                            final List<String> newParameters)
            throws InvalidFormDataException;

    public static int parsePageNumber(final String pageParameter,
                                      final int amountOfPages) {
        String numberRegex = "[1-9]+";
        if (pageParameter.matches(numberRegex)) {
            int pageNumber = Integer.parseInt(pageParameter);
            if (pageNumber > amountOfPages) {
                LOGGER.log(Level.DEBUG, "Page number greater then amount"
                        + " of pages.");
                return 1;
            } else {
                return pageNumber;
            }
        }
        LOGGER.log(Level.DEBUG, "Incorrect page number format.");
        return 1;
    }
}
