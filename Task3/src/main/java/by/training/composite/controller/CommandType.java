package by.training.composite.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Commands enum.
 * */
public enum CommandType {
    /**Command.*/
    LOAD_FROM_FILE_TEXT("load_from_file_text"),
    /**Command.*/
    PRINT_TEXT("print_text"),
    /**Command.*/
    SORT_PARAGRAPHS_BY_COUNT_OF_SENTENCE(
            "sort_paragraphs_by_count_of_sentence"),
    /**Command.*/
    SORT_WORDS_IN_SENTENCES_BY_LENGTH(
            "sort_words_in_sentences_by_length"),
    /**Command.*/
    SORT_SENTENCES_IN_PARAGRAPH_BY_COUNT_OF_WORD(
            "sort_sentences_in_paragraph_by_count_of_word"),
    /**Command.*/
    SORT_LEXEMES_IN_TEXT_BY_ENTERING_SYMBOL(
            "sort_lexemes_in_text_by_entering_symbol"),
    /**Command.*/
    EXIT_COMMAND("exit_command"),
    /**Command.*/
    WRONG_COMMAND("wrong_command");
    /**
     * Command type.
     * */
    private String command;
    /**
     * Logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Map of commands.
     * */
    private static final Map<Integer, CommandType> ORDINAL_MAP;
    static {
        ORDINAL_MAP = new HashMap<>();
        for (CommandType command : values()) {
            ORDINAL_MAP.put(command.ordinal(), command);
        }
    }
    /**
     * Constructor.
     * @param commandName -command name.
     * */
    CommandType(final String commandName) {
        this.command = commandName;
    }
    /**
     * Check if the string contains in the enum.
     * @param label -
     * @return result of checking.
     * */
    public static boolean isInEnum(final String label) {
        for (CommandType e : values()) {
            if (e.command.equalsIgnoreCase(label)) {        //toLowerCase
                return true;
            }
        }
        return false;
    }
    /**
     * Getter.
     * @param commandId -command id in map.
     * @return command type.
     * @throws IncorrectIndexException -if have incorrect index.
     * */
    public static CommandType getFromMap(final int commandId)
                                    throws IncorrectIndexException {
        if (commandId < 0) {
            LOGGER.log(Level.WARN, "Incorrect index: {}", commandId);
            throw new IncorrectIndexException();
        }
        return ORDINAL_MAP.get(commandId);
    }
    /**
     * Getter.
     * @return string command type.
     * */
    public String getCommand() {
        return this.command;
    }
    /**
     * Getter.
     * @return copy map.
     * */
    public static Map<Integer, CommandType> getOrdinalMap() {
        return new HashMap<>(ORDINAL_MAP);
    }
}
