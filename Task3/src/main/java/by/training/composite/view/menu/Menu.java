package by.training.composite.view.menu;

import by.training.composite.entity.ResourceManager;
import by.training.composite.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Menu class.
 * */
public class Menu {
    /**
     * Logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Entry list.
     * */
    private List<MenuEntry> menuEntryList = new ArrayList<>();
    /**
     * Flag for exit.
     * */
    private boolean exitFlag = false;
    /**
     * Constructor.
     * */
    public Menu() {
        ResourceManager resourceManager = ResourceManager.getInstance();
        menuEntryList.add(new MenuEntry(resourceManager
                .getString("exit")) {
            @Override
            public void run() {
                exitFlag = true;
            }
        });
    }
    /**
     * Run method.
     * */
    public void run() {
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(System.in));
        int choiceIndex = 0;
        MenuEntry menuEntry;
        String tempStr = "";
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        while (!exitFlag) {
            try {
                serviceFactory.getConsoleService().print(toString());
                tempStr = reader.readLine();
                if (!isNumber(tempStr)) {
                    throw new NumberFormatException();
                }
                choiceIndex = Integer.parseInt(tempStr);
                if (!isInRange(choiceIndex)) {
                    throw new IndexOutOfBoundsException();
                }
                menuEntry = menuEntryList.get(choiceIndex);
                menuEntry.run();
            } catch (IndexOutOfBoundsException e) {
                LOGGER.log(Level.WARN, "Incorrect index, doesn't match"
                        + " menu items: {}.", choiceIndex);
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARN, "Incorrect index type: {}."
                        + " It mast be integer number.", tempStr);
            } catch (IOException e) {
                LOGGER.log(Level.WARN, e);
            }
        }
    }
    /**
     * Check input index.
     * @param number -string.
     * @return is number integer.
     * */
    private boolean isNumber(final String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    /**
     * Check if chosen number in range of menuEntryList indexes.
     * @param index -index.
     * @return boo;ean value.
     * */
    private boolean isInRange(final int index) {
        if (index < 0) {
            return false;
        }
        if (index >= menuEntryList.size()) {
            return false;
        }
        return true;
    }
    /**
     * Add to entry menu method.
     * @param newEntry -menu.
     * */
    public void addEntryMenu(final MenuEntry newEntry) {
        if (newEntry != null) {
            menuEntryList.add(newEntry);
        }
    }
    /**
     * To string method.
     * @return string menu.
     * */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < menuEntryList.size(); i++) {
            builder.append(String.format("%d- %s;%n", i,
                    menuEntryList.get(i).getMenuTitle()));
        }
        return builder.toString();
    }
}
