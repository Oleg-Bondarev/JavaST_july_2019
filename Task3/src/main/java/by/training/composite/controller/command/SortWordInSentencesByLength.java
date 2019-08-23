package by.training.composite.controller.command;

import by.training.composite.controller.Command;
import by.training.composite.dao.TextRepository;
import by.training.composite.entity.Component;
import by.training.composite.service.ServiceFactory;
import by.training.composite.service.interfaces.SortSpecification;
import by.training.composite.service.sort.SortWordsInSentByLength;

/**
 * Sorting words in paragraph sentences by their length.
 * */
public class SortWordInSentencesByLength implements Command {
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
        SortSpecification sortWords = new SortWordsInSentByLength();
        sortWords.sort(component);
    }
}
