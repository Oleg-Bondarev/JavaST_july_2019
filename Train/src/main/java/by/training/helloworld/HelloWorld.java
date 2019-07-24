package by.training.helloworld;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *   @author Oleg Bondarev
 *   @version 1.0
 */
final class HelloWorld {
    private HelloWorld() { }
    /**Logger.*/
    private static final Logger LOGGER = LogManager.getLogger(HelloWorld.class);
    /**
     * @param args argument string parameters
     */
    public static void main(final String[] args) {
        LOGGER.debug("Debug Message Logged !!!");
        LOGGER.info("Info Message Logged !!! Hello World !!!");
        LOGGER.error("Error Message Logged !!!",
                        new NullPointerException("NullError"));
    }
}
