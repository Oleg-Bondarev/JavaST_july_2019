package by.training.composite.controller.command;

import by.training.composite.controller.Command;
import by.training.composite.dao.TextRepository;
import by.training.composite.entity.Component;
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
        TextRepository textRepository = serviceFactory.getTextRepository();
        Component component = textRepository.getTextComponent();
        System.out.println(component.compose());
    }
}
