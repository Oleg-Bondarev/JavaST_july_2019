package by.training.train.entity.carriage;

import java.util.Objects;

import by.training.train.entity.enums.ServiceEnum;

public class CompartmentCarriage extends PassengerCarriage {
    /**Number of compartments in the carriage.*/
    private int countCompartments;
    /**Default count places in the compartment.*/
    public static final int DEFAULT_PLACES_IN_COMPARTMENT = 4;
    /**Default count of the compartment in the carriage.*/
    public static final int DEFAULT_COUNT_OF_COMPARTMENT = 9;
    /**Maximum baggage weighth(in kg).*/
    public static final double MAX_BAGGAGE_WEIGHTH = 50.5;
    /**Def constructor.*/
    public CompartmentCarriage() {
        super();
        countCompartments = DEFAULT_COUNT_OF_COMPARTMENT;
    }
    /**@param newBrigade - count of the carriage stuff.
     * @param newBaggage - allowed weight of baggage per person(in kg)
     * @param newService - type of the service in carriage.
     * @param hasConditioner - conditioner in the carriage.
     * @param newCompartments - count oof compartments in the train.
     * @param countPassengersNew - count of passengers in the carriage.*/
    public CompartmentCarriage(final int newBrigade,
            final ServiceEnum newService, final int countPassengersNew,
            final double newBaggage, final boolean hasConditioner,
            final int newCompartments) {
        super(newBrigade, countPassengersNew, newBaggage, hasConditioner,
                newService);
        this.countCompartments = newCompartments;
    }
    /**@param compartCarriageNew -*/
    public CompartmentCarriage(final CompartmentCarriage compartCarriageNew) {
        super(compartCarriageNew.getBrigade(),
                compartCarriageNew.getCountOfPassengers(),
                compartCarriageNew.getBaggageOnPassenger(),
                compartCarriageNew.isConditioner(),
                compartCarriageNew.getServiceEnum());
        this.countCompartments = compartCarriageNew.countCompartments;
    }
    /**@return count of compartments in the carriage.*/
    public int getCountCompartments() {
        return countCompartments;
    }
    /**@param compartments - count of compartments in the carriage.*/
    public void setCountCompartments(final int compartments) {
        this.countCompartments = compartments;
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
        CompartmentCarriage that = (CompartmentCarriage) o;
        return countCompartments == that.countCompartments;
    }
    /**@return hashCode*/
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), countCompartments);
    }
    /**@return string*/
    @Override
    public String toString() {
        return "CompartmentCarriage{ "
                + super.toString()
                + "countCompartments=" + countCompartments
                + "} ";
    }
}
