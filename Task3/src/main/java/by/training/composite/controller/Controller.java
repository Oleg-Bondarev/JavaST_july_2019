package by.training.composite.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Controller.
 * */
public class Controller {
    /**
     * Logger.
     * */
    static final Logger LOGGER = LogManager.getLogger();
    /**
     *
     * */
    private final ControllerProvider controlProvider =
            new ControllerProvider();
    /**
     * @param request - request to execute.
     * */
    public void execute(final String request) {
        if (request == null || request.isEmpty()) {
            LOGGER.log(Level.ERROR, "Incorrect parameter:"
                    + " request. It should be different from null and "
                    + "empty string.");
            throw new RuntimeException("Incorrect parameter:"
                    + " request. It should be different from null and "
                    + "empty string.");
        }
        Command command =
                controlProvider.findCommand(request.toUpperCase());
        command.execute();
    }
}
