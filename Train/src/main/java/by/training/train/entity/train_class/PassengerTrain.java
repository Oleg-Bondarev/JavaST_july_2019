package by.training.train.entity.train_class;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import by.training.train.entity.enums.TrainType;

/**Heir to the class Train.Presents the passenger train.*/
public abstract class PassengerTrain extends Train {
    /**Number of passengers in train.*/
    private int numberOfPassengers;
    /**Amount of luggage per seat in kg.*/
    private double luaggage;
    /**Travel time in hours.*/
    private double travelTime;
    /**Train station list.*/
    private List<String> stationList;


    /***/
    public PassengerTrain() {
        super();
        numberOfPassengers = 1;
        luaggage = 1.0;
        travelTime = 0.0;
        stationList = new ArrayList<>(0);
    }
    /**
     * @param passengers -count of passengers.
     * @param time -travel time.
     */
    public PassengerTrain(final int passengers, final double time) {
        this.numberOfPassengers = passengers;
        this.travelTime = time;
    }
    /**
     * @param type -
     * @param countCarriages -
     * @param brig -
     * @param route -
     * @param depart -
     * @param arriv -
     * @param passengers -
     * @param luag -
     * @param time -
     * @param stations -*/
    public PassengerTrain(final TrainType type,
                        final int countCarriages, final int brig,
                        final String route, final LocalDateTime depart,
                        final LocalDateTime arriv, final List carriages, final int passengers,
                        final double luag, final double time,
                        final List<String> stations) {
        super(type, countCarriages, brig, route, depart, arriv, carriages);
        this.numberOfPassengers = passengers;
        this.luaggage = luag;
        this.travelTime = time;
        this.stationList = stations;
    }
    /**@return number passengers.*/
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }
    /**@param passengers -*/
    public void setNumberOfPassengers(final int passengers) {
        this.numberOfPassengers = passengers;
    }
    /**@return */
    public double getLuaggage() {
        return luaggage;
    }
    /**@param luag -*/
    public void setLuaggage(final int luag) {
        this.luaggage = luag;
    }
    /**@return */
    public double getTravelTime() {
        return travelTime;
    }
    /**@param time -*/
    public void setTravelTime(final int time) {
        this.travelTime = time;
    }
    /**@return */
    public List<String> getStationList() {
        return stationList;
    }
    /**@param stations -*/
    public void setStationList(final List<String> stations) {
        this.stationList = stations;
    }

    /***/
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
        PassengerTrain that = (PassengerTrain) o;
        return numberOfPassengers == that.numberOfPassengers
                && Double.compare(that.luaggage, luaggage) == 0
                && Double.compare(that.travelTime, travelTime) == 0
                && stationList.equals(that.stationList);
    }
    /***/
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfPassengers, luaggage,
                        travelTime, stationList);
    }
    /***/
    @Override
    public String toString() {
        return "PassengerTrain{"
                + "numberOfPassengers=" + numberOfPassengers
                + ", luaggage=" + luaggage
                + ", travelTime=" + travelTime
                + ", stationList=" + stationList
                + '}';
    }
}
