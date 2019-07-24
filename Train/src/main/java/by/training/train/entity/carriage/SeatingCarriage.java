package by.training.train.entity.carriage;

import java.util.Objects;

import by.training.train.entity.enums.ServiceEnum;

public class SeatingCarriage extends PassengerCarriage {
    /**The presence in the carriage TV.*/
    private boolean television;
    /**Maximum baggage weighth(in kg).*/
    public static final double MAX_BAGGAGE_WEIGHTH = 30.5;

    /**Def constructor.*/
    public SeatingCarriage() {
        super();
        television = false;
    }
    /**@param newBrigade - count of the carriage stuff.
     * @param newBaggage - allowed weight of baggage per person(in kg)
     * @param newService - type of the service in carriage.
     * @param hasConditioner - conditioner in the carriage.
     * @param hasTV - TV in the carriage.
     * @param newCountPassengers - count of passengers in the carriage.*/
    public SeatingCarriage(final int newBrigade,
            final ServiceEnum newService, final int newCountPassengers,
            final double newBaggage, final Boolean hasConditioner,
            final boolean hasTV) {
        super(newBrigade, newCountPassengers, newBaggage, hasConditioner,
                newService);
        this.television = hasTV;
    }
    /**@param seatingCarriageNew -*/
    public SeatingCarriage(final SeatingCarriage seatingCarriageNew) {
        super(seatingCarriageNew.getBrigade(),
                seatingCarriageNew.getCountOfPassengers(),
                seatingCarriageNew.getBagageOnPassenger(),
                seatingCarriageNew.isConditioner(),
                seatingCarriageNew.getServiceEnum());
        this.television = seatingCarriageNew.television;
    }
    /**@return does TV on the board.*/
    public boolean isTelevision() {
        return television;
    }
    /**@param newTV - */
    public void setTelevision(final boolean newTV) {
        this.television = television;
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
        SeatingCarriage that = (SeatingCarriage) o;
        return television == that.television;
    }
    /**@return hashCode.*/
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), television);
    }
    /**@return object in string representation.*/
    @Override
    public String toString() {
        return "SeatingCarriage{ "
                + super.toString()
                + ", television=" + television
                + "} ";
    }
}
