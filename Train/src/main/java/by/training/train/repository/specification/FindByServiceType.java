package by.training.train.repository.specification;

import java.util.ArrayList;
import java.util.List;

import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.entity.enums.ServiceEnum;

public class FindByServiceType
        implements FindSpecification<PassengerCarriage> {
    /**
     * Count of the carriage staff.
     * */
    private ServiceEnum serviceEnum;
    /**
     * @param serviceEnumNew - carriage with comfort type that we want to find.
     * */
    public FindByServiceType(final ServiceEnum serviceEnumNew) {
        this.serviceEnum = serviceEnumNew;
    }
    /**
     * @param list - list of carriages for search.
     * @return the result of search.
     * */
    @Override
    public List<PassengerCarriage> find(final List<PassengerCarriage> list) {
        List<PassengerCarriage> resultList = new ArrayList<>(0);
        for (PassengerCarriage obj : list) {
            if (obj.getServiceEnum() == this.serviceEnum) {
                resultList.add(obj);
            }
        }
        return resultList;
    }
}
