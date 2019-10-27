package by.training.final_task.service.parser;

import by.training.final_task.action.Action;
import by.training.final_task.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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

    //min date 2000-01-01
    public static LocalDate parseDate(final String format,
                                      final String stringDate)
            throws ServiceException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //is it normal?
        LocalDate localDate = LocalDate.now();
        try {
            if (isValidDate(df, stringDate)) {
                String[] arrayForDate = stringDate.split("-");
                int year = Integer.parseInt(arrayForDate[0]);
                int month = Integer.parseInt(arrayForDate[1]);
                int day = Integer.parseInt(arrayForDate[2]);
                localDate = LocalDate.of(year, month, day);
                return localDate;
            }
        } catch (InvalidFormDataException newE) {
            throw new ServiceException(newE.getMessage(), newE);
        }
        return localDate;
    }

    private static boolean isValidDate(final DateFormat dateFormat,
                                       final String newDate)
            throws InvalidFormDataException {
        String minDate = "2000-01-01";
        Calendar startDate = Calendar.getInstance();
        Calendar currentDate = Calendar.getInstance();
        try {
            startDate.setTime(dateFormat.parse(minDate));
            currentDate.setTime(dateFormat.parse(newDate));
        } catch (ParseException newE) {
            LOGGER.log(Level.WARN, "Incorrect date format.");
            throw new InvalidFormDataException("incorrectDateFormat");
        }
        return (startDate.compareTo(currentDate) != 1);
    }
}
