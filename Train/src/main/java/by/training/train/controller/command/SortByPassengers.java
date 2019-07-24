package by.training.train.controller.command;

import java.util.List;

import by.training.train.controller.interfaces.Command;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.service.factory.ServiceFactory;
import by.training.train.service.interfaces.CarriageService;
import by.training.train.service.specification.sort.SortByCountPassengersSpecification;

public class SortByPassengers implements Command {
    /***/
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    /***/
    @Override
    public String executeCommand(final String request) {
        StringBuilder report = new StringBuilder();
        CarriageService carriageService = serviceFactory.getCarriageService();
        List<PassengerCarriage> sortByPassengers = carriageService.
                query(new SortByCountPassengersSpecification());
        report.append("Sort by passengers is ready.");
        return report.toString();
    }
}
