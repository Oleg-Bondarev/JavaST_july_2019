package by.training.composite.view.menu;

/**
 * Menu entry class.
 * */
public abstract class MenuEntry {
    /**
     * Menu entry name.
     * */
    private String menuTitle;
    /**
     * Constructor.
     * @param newTitle -new menu title.
     * */
    MenuEntry(final String newTitle) {
        this.menuTitle = newTitle;
    }
    /**
     * Getter.
     * @return menu title.
     * */
    public String getMenuTitle() {
        return menuTitle;
    }
    /**
     * Setter.
     * @param newTitle -new menu title.
     * */
    void setMenuTitle(final String newTitle) {
        this.menuTitle = newTitle;
    }
    /**
     * Abstract method run.
     * */
    public abstract void run();
}
