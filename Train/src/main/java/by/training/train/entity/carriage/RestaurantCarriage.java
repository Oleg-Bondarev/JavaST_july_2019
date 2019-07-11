package by.training.train.entity.carriage;

import java.util.Objects;

import by.training.train.entity.enums.EconomClassEnum;
import by.training.train.entity.enums.ServiceEnum;

public class RestaurantCarriage extends PassengerCarriage {
    /**Count of siting places.*/
    private int sitingPlaces;
    /***/
    private static final int DEFAULT_PLACES = 50;
    /**def constructor.*/
    public RestaurantCarriage() {
        super();
        this.sitingPlaces = DEFAULT_PLACES;
    }
    /**@param newBrigade - count of the carriage stuff.
     * @param newBaggage - allowed weight of baggage per person(in kg)
     * @param newService - type of the service in carriage.
     * @param hasConditioner - conditioner in the carriage.
     * @param countPlaces - count of places in the train.
     * @param sitingPlaces - count of the siting places.
     */
    public RestaurantCarriage(final int newBrigade,
            final ServiceEnum newService, final double newBaggage,
            final boolean hasConditioner, final int countPlaces,
            final int sitingPlaces) {
        super(newBrigade, countPlaces, newBaggage, hasConditioner, newService);
        this.sitingPlaces = sitingPlaces;
    }
    /**@return places in the carriage.*/
    public int getSitingPlaces() {
        return sitingPlaces;
    }
    /**@param newPlaces -*/
    public void setSitingPlaces(final int newPlaces) {
        this.sitingPlaces = newPlaces;
    }
    /**@param o -object.*/
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
        RestaurantCarriage that = (RestaurantCarriage) o;
        return sitingPlaces == that.sitingPlaces;
    }
    /**@return hash-code.*/
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sitingPlaces);
    }
    /**@return string.*/
    @Override
    public String toString() {
        return "RestaurantCarriage{"
                + "sitingPlaces=" + sitingPlaces
                + '}';
    }
}
