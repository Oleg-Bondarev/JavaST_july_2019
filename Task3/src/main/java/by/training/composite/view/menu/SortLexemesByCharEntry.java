package by.training.composite.view.menu;

import by.training.composite.controller.Command;
import by.training.composite.controller.command.SortLexemesByCharacter;
import by.training.composite.entity.ResourceManager;
import by.training.composite.service.ServiceFactory;
import by.training.composite.validator.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Sorting lexemes in sentences by entry character.
 * */
public class SortLexemesByCharEntry extends MenuEntry {
    /**
     * Logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Constructor.
     * @param newTitle -new title.
     * */
    SortLexemesByCharEntry(final String newTitle) {
        super(newTitle);
    }
    /**
     * Run method.
     * */
    @Override
    public void run() {
        ResourceManager resourceManager = ResourceManager.getInstance();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        serviceFactory.getConsoleService()
                .print(resourceManager.getString("input_character"));
        BufferedReader bufferedReader
                = new BufferedReader(new InputStreamReader(System.in));
        char inputCharacter;
        String tempStr = "";
        try {
            tempStr = bufferedReader.readLine();
            if (!Validator.isCorrectInputCharacter(tempStr)) {
                throw new IncorrectCharacterInput();
            }
            inputCharacter = tempStr.charAt(0);
            Command command = new SortLexemesByCharacter(inputCharacter);
            command.execute();
        } catch (IncorrectCharacterInput e) {
            LOGGER.log(Level.WARN, "Incorrect input character: {}", tempStr);
        } catch (IOException e) {
            LOGGER.log(Level.WARN, "Some problems in reading.", e);
        }

    }
}
