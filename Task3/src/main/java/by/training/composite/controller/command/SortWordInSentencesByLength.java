package by.training.composite.controller.command;

import by.training.composite.controller.Command;
import by.training.composite.entity.Component;
import by.training.composite.entity.TextStorage;
import by.training.composite.service.interfaces.SortSpecification;
import by.training.composite.service.sort.SortWordsInSentByLength;

/**
 * Sorting words in paragraph sentences by their length.
 * */
public class SortWordInSentencesByLength implements Command {
     /**
     * Execute method.
     * */
    @Override
    public void execute() {
        TextStorage textStorage = TextStorage.getInstance();
        Component component = textStorage.getTextComponent();
        SortSpecification sortWords = new SortWordsInSentByLength();
        sortWords.sort(component);
    }
}
