package by.training.composite.controller.command;

import by.training.composite.controller.Command;
import by.training.composite.dao.TextRepository;
import by.training.composite.entity.Component;
import by.training.composite.service.ServiceFactory;
import by.training.composite.service.interfaces.SortSpecification;
import by.training.composite.service.sort.SortParByCountSent;

/**
 * Sorting paragraphs by count of sentence.
 * */
public class SortParagraphsByCountOfSentence implements Command {
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
        SortSpecification sortParagraphs = new SortParByCountSent();
        sortParagraphs.sort(component);
    }
}
