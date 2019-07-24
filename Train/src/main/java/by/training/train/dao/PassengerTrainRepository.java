package by.training.train.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.training.train.observer.Observer;
import by.training.train.service.factory.ServiceFactory;
import by.training.train.service.interfaces.TrainService;
import by.training.train.service.specification.find.FindByIdSpecification;
import by.training.train.service.specification.interfaces.FindSpecification;
import by.training.train.service.specification.interfaces.Specification;
import by.training.train.entity.carriage.PassengerCarriage;
import by.training.train.entity.carriage.TrainCarriage;

public final class PassengerTrainRepository implements CarriageDAO,
        Observer<PassengerCarriage> {
    /***/
    private static final PassengerTrainRepository INSTANCE =
            new PassengerTrainRepository();
    /**
     * List of the carriages.
     * */
    private List<PassengerCarriage> trainCarriageList = new ArrayList<>();
    /**
     * Total passangers.
     * */
    private int totalPassengers;
    /**
     * Total baggage.
     * */
    private double totalBaggage;
    /***/
    private ArrayList<PassengerCarriage> list = new ArrayList<>();
    /**
     * Default constructor.
     */
    private PassengerTrainRepository() { }
    /**
     * @param dataNew -
     * */
    /*private PassengerTrainRepository(final Subject dataNew) {
        this.passengerAndBaggageData = dataNew;
        dataNew.addObsserver(this);
    }*/
    /**
     * Constructor.
     * @param trainCarriageListNew - list of the new train repository.
     */
    private PassengerTrainRepository(final
               Optional<List<PassengerCarriage>> trainCarriageListNew) {
        if (trainCarriageListNew.isPresent()) {
            this.trainCarriageList = trainCarriageListNew.get();
        }
    }
    /**
     * Get instance.
     * @return class instance.
     * */
    public static PassengerTrainRepository getInstance() {
        return INSTANCE;
    }
    /**
     * @return list.
     * */
    public List<PassengerCarriage> getTrainCarriageList() {
        return trainCarriageList;
    }
    /**
     * @return total passengers.
     * */
    public int getTotalPassengers() {
        return totalPassengers;
    }
    /**
     * @return total baggage.
     * */
    public double getTotalBaggage() {
        return totalBaggage;
    }
    /**
     * @param specificationNew - common interfaces type.
     * */
    public void removePassengerCarriage(final Specification specificationNew) {
        if (specificationNew instanceof FindSpecification) {
            List<PassengerCarriage> foundPassengerCarriages =
                    query(specificationNew);
            trainCarriageList.removeAll(foundPassengerCarriages);
        }
    }
    //query
    @Override
    public void addCarriage(final Optional<PassengerCarriage> newCarriage) {
        if (newCarriage.isPresent()) {
            trainCarriageList.add(newCarriage.get());
        }
    }
    @Override
    public void removeCarriage(final Optional<PassengerCarriage> carriage) {
        if (carriage.isPresent()) {
            trainCarriageList.remove(carriage.get());
        }
    }
    @Override
    public List<PassengerCarriage> query(final Specification newSpecification) {
        List<PassengerCarriage> resultList = new ArrayList<>();
        if (newSpecification instanceof FindSpecification) {
            FindSpecification<? extends TrainCarriage> findSpecification =
                    (FindSpecification) newSpecification;
            resultList = findSpecification.find(trainCarriageList);
        }
        return resultList;
    }
    /**
     * @param trainCarriageNew -
     * */
    public void replacePassengerCarriage(final PassengerCarriage
                                                            trainCarriageNew) {
        if (trainCarriageNew != null) {
            long identificator = trainCarriageNew.getIdentificator();
            List<PassengerCarriage> trainCarriageFound =
            query(new FindByIdSpecification(identificator));
        }
    }
    /**
     * @param passengerCarriageNew - object to update.
     * */
   /* @Override
    public void update(final PassengerCarriage passengerCarriageNew) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TrainService trainService = serviceFactory.getTrainService();
        totalPassengers = trainService.getTotalPassengers();
        totalBaggage = trainService.getTotalBaggage();
    }*/
   /**
    * @param passRepos - adding observable object.
    * */
   public void addObservable(final PassengerCarriage passRepos) {
       if (passRepos != null) {
           list.add(passRepos);
       }
   }
   /**
    * @param passRepos - removed observable object.
    * */
   public void removeObservable(final PassengerCarriage passRepos) {
       if (passRepos != null) {
           int index = list.indexOf(passRepos);
           if (index > -1) {
               list.remove(passRepos);
           }
       }
   }
    /**
     * @param event -
     * */
    @Override
    public void handleEvent(final PassengerCarriage event) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TrainService trainService = serviceFactory.getTrainService();
        totalPassengers = trainService.getTotalPassengers();
        totalBaggage = trainService.getTotalBaggage();
    }
}
