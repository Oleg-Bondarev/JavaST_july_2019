package by.training.composite.entity;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Resource manager.
 * */
public final class ResourceManager {
    /**
     * Instance.
     * */
    private static final ResourceManager INSTANCE = new ResourceManager();
    /**
     * Resource bundle.
     * */
    private ResourceBundle resourceBundle;
    /**
     * Resource name.
     * */
    private static final String RESOURCE = "property.text";
    /**
     * Constructor.
     * */
    private ResourceManager() {
        resourceBundle
                = ResourceBundle.getBundle(RESOURCE, new Locale("en", "US"));
    }
    /**
     * Changing locale.
     * @param newLocale -new locale.
     * */
    public void changeLocale(final Locale newLocale) {
        resourceBundle = ResourceBundle.getBundle(RESOURCE, newLocale);
    }
    /**
     * Getter.
     * @return instance.
     * */
    public static ResourceManager getInstance() {
        return INSTANCE;
    }
    /**
     * Getter.
     * @param key -key.
     * @return string.
     * */
    public String getString(final String key) {
        return resourceBundle.getString(key);
    }
}
