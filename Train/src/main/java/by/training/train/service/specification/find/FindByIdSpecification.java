package by.training.train.service.specification.find;

import java.util.ArrayList;
import java.util.List;

import by.training.train.service.specification.interfaces.FindSpecification;
import by.training.train.entity.carriage.PassengerCarriage;

public class FindByIdSpecification
        implements FindSpecification<PassengerCarriage> {
    /**
     * Identificator.
     * */
    private long id;
    /**
     * @param idNew - id that we want to find.
     * */
    public FindByIdSpecification(final long idNew) {
        this.id = idNew;
    }
    /**
     * @param list - list of carriages for search.
     * @return the result of search.
     * */
    @Override
    public List<PassengerCarriage> find(final List<PassengerCarriage> list) {
        List<PassengerCarriage> resultList = new ArrayList<>(0);
        for (PassengerCarriage obj : list) {
            if (obj.getIdentificator() == this.id) {
                resultList.add(obj);
            }
        }
        return resultList;
    }
}
