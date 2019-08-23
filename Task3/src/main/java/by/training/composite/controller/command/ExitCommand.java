package by.training.composite.controller.command;

import by.training.composite.controller.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Exit command.
 * */
public class ExitCommand implements Command {
    /**
     * Logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Execute method.
     * */
    @Override
    public void execute() {
        LOGGER.log(Level.INFO, "User terminated program.");
        System.exit(0);
    }
}
