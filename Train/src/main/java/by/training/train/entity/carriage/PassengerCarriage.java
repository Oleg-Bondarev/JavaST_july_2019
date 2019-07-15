package by.training.train.entity.carriage;

import java.util.Objects;

import by.training.train.entity.enums.ServiceEnum;

public abstract class PassengerCarriage extends TrainCarriage {
    /**The maximum number of passengers for which the carriage is designed.*/
    private int countOfPassengers;
    /**Maximum baggage allowance per passenger (in kilograms).*/
    private double bagageOnPassenger;
    /**Air conditioner in the carriage.*/
    private boolean conditioner;
    /**Classes of servise.*/
    private ServiceEnum serviceEnum;
    /**Default count of passengers.*/
    private static final int DEFAULT_COUNT_PASSENGERS = 56;
    /**Default weight of baggage allowance per passenger (in kilograms).*/
    private static final double DEFAULT_WEIGHTHT = 20;
    /**Default constructor.*/
    public PassengerCarriage() {
        super();
        countOfPassengers = DEFAULT_COUNT_PASSENGERS;
        bagageOnPassenger = DEFAULT_WEIGHTHT;
        conditioner = false;
        serviceEnum = ServiceEnum.ECONOM;
    }
    /**Constructor with parameters.
     * @param newBrig - count of carriage staff.
     * @param newCountPassengers - count places for passengers.
     * @param newBaggageWeight - baggage allowance per passenger(in kg)
     * @param hasConditioner - is conditioner in the carriage.
     * @param newServiceEnum - class of setvice in the carriage.*/
    public PassengerCarriage(final int newBrig, final int newCountPassengers,
            final double newBaggageWeight, final boolean hasConditioner,
            final ServiceEnum newServiceEnum) {
        super(newBrig);
        this.countOfPassengers = newCountPassengers;
        this.bagageOnPassenger = newBaggageWeight;
        this.conditioner = hasConditioner;
        this.serviceEnum = newServiceEnum;
    }

    /**@param passengerCarriageNew - */
    public PassengerCarriage(final PassengerCarriage passengerCarriageNew) {
        super(passengerCarriageNew.getBrigade());
        this.countOfPassengers = passengerCarriageNew.countOfPassengers;
        this.bagageOnPassenger = passengerCarriageNew.bagageOnPassenger;
        this.conditioner = passengerCarriageNew.conditioner;
        this.serviceEnum = passengerCarriageNew.serviceEnum;
    }

    /**@return count of passengers.*/
    public int getCountOfPassengers() {
        return countOfPassengers;
    }
    /**@param newCountOfPassengers - count of passengers in carriage.*/
    public void setCountOfPassengers(final int newCountOfPassengers) {
        this.countOfPassengers = newCountOfPassengers;
    }
    /**@return weighth of baggage allowance per passenger(in kg)*/
    public double getBagageOnPassenger() {
        return bagageOnPassenger;
    }
    /**@param newBaggageOnPassenger - baggage weighth.*/
    public void setBagageOnPassenger(final double newBaggageOnPassenger) {
        this.bagageOnPassenger = newBaggageOnPassenger;
    }
    /**@return does the carriage have air conditioning.*/
    public boolean isConditioner() {
        return conditioner;
    }
    /**@param hasConditioner -*/
    public void setConditioner(final boolean hasConditioner) {
        this.conditioner = hasConditioner;
    }
    /**@return service in the carriage.*/
    public ServiceEnum getServiceEnum() {
        return serviceEnum;
    }
    /**@param newServiceEnum -*/
    public void setServiseEnum(final ServiceEnum newServiceEnum) {
        this.serviceEnum = serviceEnum;
    }

    /**
     * @param o - The reference object with which to compare.
     * @return result of the method.
     * */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PassengerCarriage that = (PassengerCarriage) o;
        return countOfPassengers == that.countOfPassengers
                && Double.compare(that.bagageOnPassenger, bagageOnPassenger)
                    == 0
                && conditioner == that.conditioner
                && serviceEnum == that.serviceEnum;
    }
    /**@return object hashCode.*/
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), countOfPassengers,
                bagageOnPassenger, conditioner, serviceEnum);
    }
    /**@return object in string representation.*/
    @Override
    public String toString() {
        return "PassengerCarriage{"
                + "countOfPassengers=" + countOfPassengers
                + ", bagageOnPassenger=" + bagageOnPassenger
                + ", conditioner=" + conditioner
                + ", serviceEnum=" + serviceEnum
                + '}';
    }
}
