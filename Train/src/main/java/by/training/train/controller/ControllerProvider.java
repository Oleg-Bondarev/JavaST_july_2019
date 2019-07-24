package by.training.train.controller;

import java.util.HashMap;
import java.util.Map;

import by.training.train.controller.command.AddCarriage;
import by.training.train.controller.command.ReadCarriages;
import by.training.train.controller.command.AllBaggage;
import by.training.train.controller.command.AllPassengers;
import by.training.train.controller.command.DeleteCarriage;
import by.training.train.controller.command.FindByPassRange;
import by.training.train.controller.command.ShowTrain;
import by.training.train.controller.command.SortByBaggage;
import by.training.train.controller.command.SortByID;
import by.training.train.controller.command.SortByPassengers;
import by.training.train.controller.command.SortByPassengersThenBaggege;
import by.training.train.controller.command.WrongCommand;
import by.training.train.controller.enums.Commands;
import by.training.train.controller.interfaces.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControllerProvider {
    /**
     * Factory that organized like a map.
     * */
    private final Map<Commands, Command> commands = new HashMap<>();
    /**Logger.*/
    static final Logger LOGGER = LogManager.getLogger(ControllerProvider.class);
    /***/
    public ControllerProvider() {
        commands.put(Commands.READ_CARRIAGES, new ReadCarriages());
        commands.put(Commands.ALL_BAGGAGE, new AllBaggage());
        commands.put(Commands.ALL_PASSENGERS, new AllPassengers());
        commands.put(Commands.DELETE_CARRIAGE, new DeleteCarriage());
        commands.put(Commands.FIND_BY_PASSENGER_RANGE, new FindByPassRange());
        commands.put(Commands.SORT_BY_BAGGAGE, new SortByBaggage());
        commands.put(Commands.SORT_BY_ID, new SortByID());
        commands.put(Commands.SORT_BY_COUNT_PASSENGERS, new SortByPassengers());
        commands.put(Commands.SORT_BY_PASSENGERS_THEN_BY_BAGGAGE,
                new SortByPassengersThenBaggege());
        commands.put(Commands.SHOW_TRAIN, new ShowTrain());
        commands.put(Commands.ADD_CARRIAGE, new AddCarriage());
        commands.put(Commands.WRONG_COMMAND, new WrongCommand());
    }
    /**
     * @param commandNameNew - command name.
     * @return command to execute.
     * */
    public Command findCommand(final String commandNameNew) {
        Command command;
        Commands commandName;

        if (!Commands.isInEnum(commandNameNew.toUpperCase())) {
            LOGGER.log(Level.ERROR, "Unknown command name: " + commandNameNew);
            command = commands.get(Commands.WRONG_COMMAND);
        } else {
            commandName = Commands.valueOf(commandNameNew.toUpperCase());
            command = commands.get(commandName);
        }
        return command;
    }
}
