package by.training.composite.controller.command;

import by.training.composite.controller.Command;
import by.training.composite.entity.Component;
import by.training.composite.entity.TextStorage;
import by.training.composite.service.interfaces.ConsoleService;
import by.training.composite.service.ServiceFactory;

/**
 * Print text.
 * */
public class PrintText implements Command {
    /**
     * Service factory.
     * */
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    /**
     * Execute method.
     * */
    @Override
    public void execute() {
        TextStorage textStorage = TextStorage.getInstance();
        Component component = textStorage.getTextComponent();
        ConsoleService consoleService = serviceFactory.getConsoleService();
        consoleService.print(component.compose());
    }
}
