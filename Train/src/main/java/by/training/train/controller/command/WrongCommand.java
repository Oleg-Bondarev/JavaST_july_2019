package by.training.train.controller.command;

import by.training.train.controller.interfaces.Command;

public class WrongCommand implements Command {
    /**
     * @param request -
     * @return null.
     * */
    @Override
    public String executeCommand(final String request) {
        return null;
    }
}
