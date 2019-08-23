package by.training.composite.view;

import by.training.composite.controller.Controller;
import by.training.composite.entity.ResourceManager;
import by.training.composite.view.menu.LocaleEntry;
import by.training.composite.view.menu.Menu;
import by.training.composite.view.menu.PrintTextEntry;
import by.training.composite.view.menu.SortTextEntry;

/**
 * Main class.
 * */
public final class Main {
    /**
     * Constructor.
     * */
    private Main() { }
    /**
     * Main method.
     * @param args -args.
     * */
    public static void main(final String[] args) {
//        Controller controller = new Controller();
//        controller.execute("LOAD_FROM_FILE_TEXT");
//        controller.execute("PRINT_TEXT");
//        System.out.println("==============================");
//        controller.execute("SORT_PARAGRAPHS_BY_COUNT_OF_SENTENCE");
//        controller.execute("PRINT_TEXT");

//        controller.execute("SORT_WORDS_IN_SENTENCES_BY_LENGTH");
//        controller.execute("PRINT_TEXT");

//        controller.execute("WRONG_COMMAND");

//        controller.execute("SORT_SENTENCES_IN_PARAGRAPH_BY_COUNT_OF_WORD");
//        controller.execute("PRINT_TEXT");

        Controller controller = new Controller();
        controller.execute("LOAD_FROM_FILE_TEXT");
        Menu localMenu = new Menu();
        ResourceManager resourceManager = ResourceManager.INSTANCE;
        localMenu.addEntryMenu(new LocaleEntry("english"));
        localMenu.addEntryMenu(new LocaleEntry("russian"));
        localMenu.addEntryMenu(new LocaleEntry("belorussian"));
        localMenu.run();
        Menu menu = new Menu();
        menu.addEntryMenu(new PrintTextEntry(resourceManager
                                            .getString("printing_text")));
        menu.addEntryMenu(new SortTextEntry(resourceManager
                                            .getString("sorting_text")));
        menu.run();
    }
}
