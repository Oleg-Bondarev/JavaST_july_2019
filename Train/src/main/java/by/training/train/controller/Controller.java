package by.training.train.controller;

import by.training.train.controller.interfaces.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {
    /***/
    static final Logger LOGGER = LogManager.getLogger(Controller.class);
    /***/
    private static final String REGIX_FOR_SPLIT = ";";
    /***/
    private static final String WRONG_REQUEST =
                                        "Incorrect request or unknown command.";
    /***/
    private final ControllerProvider contrrolProvider =
            new ControllerProvider();
    /**
     * @param request - request to execute.
     * @return report about request executing.
     * */
    public String executeeRequest(final String request) {
        if (request == null || request.isEmpty()) {
            LOGGER.log(Level.ERROR, "Incorrect parameter:"
            + " request. It should be different from null and empty string.");
            return WRONG_REQUEST;
        }
        String commandName =
                request.substring(0, request.indexOf(REGIX_FOR_SPLIT));
        Command command =
                contrrolProvider.findCommand(commandName.toUpperCase());
        return command.executeCommand(request);
    }
}
