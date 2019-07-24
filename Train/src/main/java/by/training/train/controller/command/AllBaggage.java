package by.training.train.controller.command;

import java.util.List;
import java.util.Optional;

import by.training.train.controller.interfaces.Command;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.service.factory.ServiceFactory;
import by.training.train.service.interfaces.TrainService;
import by.training.train.service.specification.calculate.CalculateBaggage;

public class AllBaggage implements Command {
    /***/
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    /**
     * @return total baggage.
     */
    @Override
    public String executeCommand(final String request) {
        StringBuilder report =
                new StringBuilder("Total weight of baggage(kg): ");
        TrainService trainService = serviceFactory.getTrainService();
        List<PassengerCarriage> carriageList = trainService.getTrain();
        CalculateBaggage calc = new CalculateBaggage();
        double baggage = calc.calculateBaggage(Optional.of(carriageList));
        report.append(baggage);
        return report.toString();
    }
}
