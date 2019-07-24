package by.training.train.controller.interfaces;

public interface Command {
    /**
     * @param request - request to execute some command.
     * @return the report about executing some command.
     * */
    String executeCommand(String request);
}
