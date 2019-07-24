package by.training.train.controller.enums;

public enum Commands {
    /**command.*/
    READ_CARRIAGES("read_carriages"),
    /**command. Delete carriage by ID.*/
    DELETE_CARRIAGE("delete_carriage"),
    /***/
    ADD_CARRIAGE("add_carriage"),
    /**command.*/
    ALL_BAGGAGE("all_baggage"),
    /**command.*/
    ALL_PASSENGERS("all_passengers"),
    /**command.*/
    SORT_BY_BAGGAGE("sort_by_baggage"),
    /**command.*/
    SORT_BY_COUNT_PASSENGERS("sort_by_count_passengers"),
    /**command.*/
    SORT_BY_ID("sort_by_id"),
    /**command.*/
    SORT_BY_PASSENGERS_THEN_BY_BAGGAGE("sort_by_passengers_then_by_baggage"),
    /**command.*/
    FIND_BY_PASSENGER_RANGE("find_by_passenger_range"),
    /**command.*/
    SHOW_TRAIN("show_train"),
    /**command.*/
    WRONG_COMMAND("wrong_command");
    /**type of the command.*/
    private String command;
    /**
     * Constructor.
     * @param commandNew - new command.*/
    Commands(final String commandNew) {
        this.command = commandNew;
    }
    /**
     * Check if the string contains in the enum.
     * @param label -
     * @return result of checking.
     * */
    public static boolean isInEnum(final String label) {
        for (Commands e : values()) {
            if (e.command.equals(label.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
