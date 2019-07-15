package by.training.train.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.entity.carriage.TrainCarriage;

public class TrainRepository {
    /***/
    public static final  TrainRepository INSTANCE = new TrainRepository();
    /**
     * List of the carriages.
     * */
    private List<TrainCarriage> trainCarriageList = new ArrayList<>();
    /**
     * Default constructor.
     */
    private TrainRepository() { }
    /**
     * Get instance.
     * @return class instance.
     * */
    public static TrainRepository getInstance() {
        return INSTANCE;
    }
    /**
     * Add passenger carriage in repository.
     * @param passengerCarriageNew - new passenger carriage.
     **/
    public void addPassengerCarriage(final
    Optional<PassengerCarriage> passengerCarriageNew) {
        if (passengerCarriageNew.isPresent()) {
            trainCarriageList.add(passengerCarriageNew.get());
        }
    }
    /**
     * Remove carriage from repository.
     * @param passengerCarriageNew - carriage that we want to remove
     * from repository.
     * */
    public void removePassengerarriage(final
            Optional<PassengerCarriage> passengerCarriageNew) {
        if (passengerCarriageNew.isPresent()) {
            trainCarriageList.remove(passengerCarriageNew.get());
        }
    }

}
