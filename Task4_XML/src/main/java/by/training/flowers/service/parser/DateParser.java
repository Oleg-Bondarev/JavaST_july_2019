package by.training.flowers.service.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for parsing data.
 * */
final class DateParser {
    /**
     * Constructor.
     * */
    private DateParser() {
    }

    /**
     * Parsing data.
     * @param date -date.
     * @return LocalDate object.
     * */
    static LocalDate parseDate(final String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }
}
