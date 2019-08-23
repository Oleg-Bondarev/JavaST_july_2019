package by.training.composite.view.menu;

import by.training.composite.entity.ResourceManager;

/**
 * Sort text entry.
 * */
public class SortTextEntry extends MenuEntry {
    /**
     * Constructor.
     * @param newTitle -new title.
     * */
    public SortTextEntry(final String newTitle) {
        super(newTitle);
    }
    /**
     * Run method.
     * */
    @Override
    public void run() {
        ResourceManager resourceManager = ResourceManager.INSTANCE;
        Menu menu = new Menu();
        menu.addEntryMenu(new SortParagraphsByCountSentenceEntry(
                resourceManager.getString("sort_par_by_count_sent")));
        menu.addEntryMenu(new SortWordsInSentenceByLengthEntry(
                resourceManager.getString("sort_words_in_sent_by_len")));
        menu.addEntryMenu(new SortSentenceInParagraphByCountWordEntry(
                resourceManager.getString(
                        "sort_sent_in_par_by_count_words")));
        menu.run();
    }
}
