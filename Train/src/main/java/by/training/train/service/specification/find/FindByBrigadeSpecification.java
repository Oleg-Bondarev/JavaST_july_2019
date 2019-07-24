package by.training.train.service.specification.find;

import java.util.ArrayList;
import java.util.List;

import by.training.train.service.specification.interfaces.FindSpecification;
import by.training.train.entity.carriage.PassengerCarriage;

public class FindByBrigadeSpecification
        implements FindSpecification<PassengerCarriage> {
    /**
     * Count of the carriage staff.
     * */
    private int brigade;
    /**
     * @param brigadeNew - id that we want to find.
     * */
    public FindByBrigadeSpecification(final int brigadeNew) {
        this.brigade = brigadeNew;
    }
    /**
     * @param list - list of carriages for search.
     * @return the result of search.
     * */
    @Override
    public List<PassengerCarriage> find(final List<PassengerCarriage> list) {
        List<PassengerCarriage> resultList = new ArrayList<>(0);
        for (PassengerCarriage obj : list) {
            if (obj.getBrigade() == this.brigade) {
                resultList.add(obj);
            }
        }
        return resultList;
    }
}
