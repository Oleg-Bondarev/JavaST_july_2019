package by.training.train.entity.carriage;

import java.util.Objects;

import by.training.train.entity.enums.ServiceEnum;

public class SeatingCarriage extends PassengerCarriage {
    /**The presence in the carriage TV.*/
    private boolean television;
    /**Maximum baggage weighth(in kg).*/
    private static final double MAX_BAGGAGE_WEIGHTH = 30.5;

    /**Def constructor.*/
    public SeatingCarriage() {
        super();
        television = false;
    }
    /**@param newBrigade - count of the carriage stuff.
     * @param newBaggage - allowed weight of baggage per person(in kg)
     * @param newService - type of the service in carriage.
     * @param hasConditioner - conditioner in the carriage.
     * @param newPassengers - count of siting places.
     * @param hasTV - TV in the carriage.*/
    public SeatingCarriage(final int newBrigade,
            final ServiceEnum newService, final int newPassengers,
            final double newBaggage, final Boolean hasConditioner,
            final boolean hasTV) {
        super(newBrigade, newPassengers, newBaggage, hasConditioner,
                newService);
        this.television = hasTV;
    }
    /**@return does TV on the board.*/
    public boolean isTelevision() {
        return television;
    }
    /**@param newTV - */
    public void setTelevision(final boolean newTV) {
        this.television = television;
    }
    /**@param o -*/
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
    /***/
    @Override
    public String toString() {
        return "SeatingCarriage{"
                + "television=" + television
                + '}';
    }
}
