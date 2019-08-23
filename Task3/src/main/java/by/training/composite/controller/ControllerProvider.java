package by.training.composite.controller;

import by.training.composite.controller.command.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.Map;

/**
 * Controller provider.
 * */
public class ControllerProvider {
    /**
     * Factory like a map.
     * */
    private final Map<CommandType, Command> commandMap
                        = new EnumMap<>(CommandType.class);
    /**
     * Logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Constructor.
     * */
    public ControllerProvider() {
        commandMap.put(CommandType.WRONG_COMMAND, new WrongCommand());
        commandMap.put(CommandType.LOAD_FROM_FILE_TEXT, new LoadText());
        commandMap.put(CommandType.PRINT_TEXT, new PrintText());
        commandMap.put(CommandType.SORT_PARAGRAPHS_BY_COUNT_OF_SENTENCE,
                new SortParagraphsByCountOfSentence());
        commandMap.put(CommandType.SORT_WORDS_IN_SENTENCES_BY_LENGTH,
                new SortWordInSentencesByLength());
        commandMap.put(CommandType.SORT_SENTENCES_IN_PARAGRAPH_BY_COUNT_OF_WORD,
                new SortSentencesInParagraphByCountWord());
    }
    /**
     * @param commandName -command name.
     * @return command to execute.
     * */
    public Command findCommand(final String commandName) {
        Command command;
        CommandType commandType;

        if (!CommandType.isInEnum(commandName.toUpperCase())) {
            LOGGER.log(Level.ERROR, "Unknown command name: {}", commandName);
            command = commandMap.get(CommandType.WRONG_COMMAND);
        } else {
            commandType = CommandType.valueOf(commandName.toUpperCase());
            command = commandMap.get(commandType);
        }
        return command;
    }
}
