package by.training.flowers.service.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Represent reading properties from property file.
 * */
public final class PropertiesReader {
    /**
     * Class instance.
     * */
    private static final PropertiesReader INSTANCE = new PropertiesReader();
    /**
     * Constructor.
     * */
    private PropertiesReader() { }
    /**
     * @return instance.
     * */
    public static PropertiesReader getInstance() {
        return INSTANCE;
    }
    /**
     * @param propertyFile -prop. file.
     * @param key -prop. key.
     * @return properties.
     * @throws PropertyException -if have problems.
     * */
    public static String takeProperty(final String propertyFile,
                                  final String key) throws PropertyException {
        ClassLoader classLoader = PropertiesReader.class.getClassLoader();
        Properties property = new Properties();
        try (InputStream inputStream = classLoader
                .getResourceAsStream(propertyFile)) {
            if (inputStream == null) {
                throw new PropertyException("Can't find file " + propertyFile);
            }
            property.load(inputStream);
        } catch (IOException e) {
            throw new PropertyException("Exception during reading property"
                                        + " file " + propertyFile);
        }
        return property.getProperty(key);
    }
}
