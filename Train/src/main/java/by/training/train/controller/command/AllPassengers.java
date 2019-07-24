package by.training.train.controller.command;

import java.util.List;
import java.util.Optional;

import by.training.train.controller.interfaces.Command;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.service.factory.ServiceFactory;
import by.training.train.service.interfaces.TrainService;
import by.training.train.service.specification.calculate.CalculatePassengers;

public class AllPassengers implements Command {
    /***/
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    /**
     * @return total baggage.
     */
    @Override
    public String executeCommand(final String request) {
        StringBuilder report =
                new StringBuilder("Total count of passengers: ");
        TrainService trainService = serviceFactory.getTrainService();
        List<PassengerCarriage> carriageList = trainService.getTrain();
        CalculatePassengers calc = new CalculatePassengers();
        int passengers = calc.calculatePassengers(Optional.of(carriageList));
        report.append(passengers);
        return report.toString();
    }
}
