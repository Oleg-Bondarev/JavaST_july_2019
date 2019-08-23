package by.training.composite.view.menu;

import by.training.composite.controller.Command;
import by.training.composite.controller.command.SortWordInSentencesByLength;

/**
 * Sort words in sentences by length entry.
 * */
public class SortWordsInSentenceByLengthEntry extends MenuEntry {
    /**
     * Constructor.
     * @param newTitle -new title.
     * */
    SortWordsInSentenceByLengthEntry(final String newTitle) {
        super(newTitle);
    }
    /**
     * Run method.
     * */
    @Override
    public void run() {
        Command command = new SortWordInSentencesByLength();
        command.execute();
    }
}
