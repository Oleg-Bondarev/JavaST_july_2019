package by.training.flowers.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for parsing data.
 * */
public class DataParser {
    /**
     * Parsing data.
     * @param date -date.
     * @return LocalDate object.
     * */
    public static LocalDate parseDate(final String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }
}
