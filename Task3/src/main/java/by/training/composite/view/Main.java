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
        Controller controller = new Controller();
        controller.execute("LOAD_FROM_FILE_TEXT");
        Menu localMenu = new Menu();
        ResourceManager resourceManager = ResourceManager.getInstance();
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
