package by.training.final_task.service.implimentation;

import by.training.final_task.controller.servlet.ServletConfiguration;
import by.training.final_task.dao.PersistentException;
import by.training.final_task.dao.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

/**
 * Initialize connection pool for app. test. Util class.
 */
public final class ServiceInitializer {
    /**
     * Class logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Default private constructor.
     */
    private ServiceInitializer() {
    }

    /**
     * Connect to database.
     *
     * @param newBundle resource bundle with parameter for database.
     */
    public static void init(final ResourceBundle newBundle) {
        ServletConfiguration servletConfiguration =
                new ServletConfiguration(newBundle);
        try {
            ConnectionPool.getInstance().initialize(
                    servletConfiguration.getDbDriverClass(),
                    servletConfiguration.getDbURL(),
                    servletConfiguration.getDbUserLogin(),
                    servletConfiguration.getDbUserPassword(),
                    servletConfiguration.getDbPoolStartSize(),
                    servletConfiguration.getDbPoolMaxSize(),
                    servletConfiguration.getDbPoolCheckConnectionTimeout());
        } catch (PersistentException newE) {
            LOGGER.log(Level.ERROR, "It is impossible to initialize" +
                    " application. " + newE.getMessage(), newE);
            throw new RuntimeException();
        }
        LOGGER.log(Level.DEBUG, "Successful init test servlet.");
    }
}
