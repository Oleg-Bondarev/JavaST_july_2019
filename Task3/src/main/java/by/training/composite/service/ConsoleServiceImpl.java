package by.training.composite.service;

import by.training.composite.dao.ConsolePrinter;
import by.training.composite.service.interfaces.ConsoleService;

/**
 * Console service realisation.
 * */
public class ConsoleServiceImpl implements ConsoleService {
    /**
     * Write in console text.
     * @param message -message to print.
     * */
    @Override
    public void print(final Object message) {
        ConsolePrinter.print(message);
    }
}
