package by.training.train.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.entity.carriage.TrainCarriage;
import by.training.train.repository.specification.FindByIdSpecification;
import by.training.train.repository.specification.FindSpecification;
import by.training.train.repository.specification.Specification;

public class TrainRepository {
    /***/
    public static final  TrainRepository INSTANCE = new TrainRepository();
    /**
     * List of the carriages.
     * */
    private List<PassengerCarriage> trainCarriageList = new ArrayList<>();
    /**
     * Default constructor.
     */
    private TrainRepository() { }
    /**
     * Constructor.
     * @param trainCarriageListNew - list of the new train repository.
     */
    private TrainRepository(final
               Optional<List<PassengerCarriage>> trainCarriageListNew) {
        if (trainCarriageListNew.isPresent()) {
            this.trainCarriageList = trainCarriageListNew.get();
        }
    }
    /**
     * Get instance.
     * @return class instance.
     * */
    public static TrainRepository getInstance() {
        return INSTANCE;
    }
    /**
     * @return list.
     * */
    public List<PassengerCarriage> getTrainCarriageList() {
        return trainCarriageList;
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
    public void removePassengerCarriage(final
            Optional<PassengerCarriage> passengerCarriageNew) {
        if (passengerCarriageNew.isPresent()) {
            trainCarriageList.remove(passengerCarriageNew.get());
        }
    }

    /**
     * @param specificationNew - common interface type.
     * */
    public void removePassengerCarriage(final Specification specificationNew) {
        if (specificationNew instanceof FindSpecification) {
            List<PassengerCarriage> foundPassengerCarriages =
                    queryPassengerCarriage(specificationNew);
            trainCarriageList.removeAll(foundPassengerCarriages);
        }
    }

    //query
    /**
     * @param specification - specification to find.
     * @return result.
     * */
    public List<PassengerCarriage> queryPassengerCarriage(final
                                                Specification specification) {
        List<PassengerCarriage> resultList = new ArrayList<>();
        if (specification instanceof FindSpecification) {
            FindSpecification<? extends TrainCarriage> findSpecification =
                    (FindSpecification) specification;
            resultList = findSpecification.find(trainCarriageList);
        }
        return resultList;
    }
    /**
     * @param trainCarriageNew -
     * */
    public void replacePassengerCarriage(final TrainCarriage trainCarriageNew) {
        if (trainCarriageNew != null) {
            long identificator = trainCarriageNew.getIdentificator();
            List<PassengerCarriage> trainCarriageFound =
            queryPassengerCarriage(new FindByIdSpecification(identificator));
        }
    }
}
