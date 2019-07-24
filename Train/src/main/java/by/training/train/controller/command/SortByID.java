package by.training.train.controller.command;

import java.util.List;

import by.training.train.controller.interfaces.Command;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.service.factory.ServiceFactory;
import by.training.train.service.interfaces.CarriageService;
import by.training.train.service.specification.sort.SortByIDSpecification;

public class SortByID implements Command {
    /***/
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    /***/
    @Override
    public String executeCommand(final String request) {
        StringBuilder report = new StringBuilder();
        CarriageService carriageService = serviceFactory.getCarriageService();
        List<PassengerCarriage> sortByID = carriageService.
                query(new SortByIDSpecification());
        report.append("Sort by ID is ready.");
        return report.toString();
    }
}
