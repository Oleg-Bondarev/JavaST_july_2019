package by.training.train.repository.specification;

import java.util.ArrayList;
import java.util.List;

import by.training.train.entity.carriage.PassengerCarriage;

public class FindByConditionerSpecification
                            implements FindSpecification<PassengerCarriage> {
    /**
     * Is conditioner on the board.
     * */
    private boolean isConditioner;
    /**
     * @param isConditionerNew - is conditione on the board.
     * */
    public FindByConditionerSpecification(final boolean isConditionerNew) {
        this.isConditioner = isConditionerNew;
    }
    /**
     * @param list - list of carriages for search.
     * @return the result of search.
     * */
    @Override
    public List<PassengerCarriage> find(final List<PassengerCarriage> list) {
        List<PassengerCarriage> resultList = new ArrayList<>(0);
        for (PassengerCarriage obj : list) {
            if (obj.isConditioner() == isConditioner) {
                resultList.add(obj);
            }
        }
        return resultList;
    }
}
