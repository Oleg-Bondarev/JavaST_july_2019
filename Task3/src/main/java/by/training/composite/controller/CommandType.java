package by.training.composite.controller;

/**
 * Commands enum.
 * */
public enum CommandType {
    /**Command.*/
    LOAD_FROM_FILE_TEXT("load_from_file_text"),
    /**Command.*/
    PRINT_TEXT("print_text"),
    /**Command.*/
    SORT_PARAGRAPHS_BY_COUNT_OF_SENTENCE("sort_paragraphs_by_count_of_sentence"),
    /**Command.*/
    SORT_WORDS_IN_SENTENCES_BY_LENGTH("sort_words_in_sentences_by_length"),
    /**Command.*/
    SORT_SENTENCES_IN_PARAGRAPH_BY_COUNT_OF_WORD("sort_sentences_in_paragraph_by_count_of_word"),
    /**Command.*/
    SORT_LEXEMES_IN_TEXT_BY_ENTERING_SYMBOL("sort_lexemes_in_text_by_entering_symbol"),
    /**Command.*/
    WRONG_COMMAND("wrong_command");
    /**
     * Command type.
     * */
    private String command;
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
            if (e.command.equals(label.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
