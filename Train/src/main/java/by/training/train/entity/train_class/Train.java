package by.training.train.entity.train_class;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import by.training.train.entity.carriage.RailwayCarriage;
import by.training.train.entity.enums.TrainType;

/**
 * Super-class Train.
 * Describes the general structure for the classes of heirs Passenger,
 * Freight, Special Train.
 * */
public abstract class Train {
    /**Train identificatior.*/
    private int identificator;
    /**Type of a train.*/
    private TrainType trainType;
    /**Count of railway carriages in the train.*/
    private int railwayCarriages;
    /**Total number of train conductors and machinists.*/
    private int brigade;
    /**Route trail.*/
    private String route;
    /**Departure time.*/
    private LocalDateTime departTime;
    /**Arrival time.*/
    private LocalDateTime arrivalTime;
    private List<RailwayCarriage> carriages;

    /***/
    public Train() {
        identificator = 0;
        trainType = TrainType.PASSENGER;
        railwayCarriages = 0;
        brigade = 2;
        route = "Without route.";
        departTime = LocalDateTime.now();
        arrivalTime = departTime;
        carriages = new ArrayList<>(0);
    }

   /* /**@param id -train id.*//*
    public Train(final int id) {
        this.identificator = id;
    }*/

    /**@param type -type of train.
     * @param countCarriages -count of railway carriages in the train.
     * @param brig -number of train conductors and machinists.
     * @param route1 -train route.
     * @param depart -depart time of the train.
     * @param arriv -arrival time of the train.
     * @param carriageList -list of carriages in the train.
     */
    public Train(final TrainType type, final int countCarriages, final int brig,
            final String route1, final LocalDateTime depart,
            final LocalDateTime arriv, final List carriageList) {
        this.trainType = trainType;
        this.railwayCarriages = railwayCarriages;
        this.brigade = brig;
        this.route = route1;
        this.departTime = depart;
        this.arrivalTime = arriv;
        this.carriages = carriageList;
    }

    /**@return identificator of the train.*/
    public int getIdentificator() {
        return identificator;
    }
    /**@return type of the train.*/
    public TrainType getTrainType() {
        return trainType;
    }
    /**@return  count of railway carriages.*/
    public int getRailwayCarriages() {
        return railwayCarriages;
    }
    /**@param id -train id.*/
    public void setIdentificator(final int id) {
        this.identificator = id;
    }
    /**@param type -type of train.*/
    public void setTrainType(final TrainType type) {
        this.trainType = type;
    }
    /**@param countCarriages -count of railway carriages in the train.*/
    public void setRailwayCarriages(final int countCarriages) {
        this.railwayCarriages = countCarriages;
    }
    /**@return count of train brigade*/
    public int getBrigade() {
        return brigade;
    }
    /**@param brig -*/
    public void setBrigade(final int brig) {
        this.brigade = brig;
    }
    /**@return train route*/
    public String getRoute() {
        return route;
    }
    /**@param route1 -*/
    public void setRoute(final String route1) {
        this.route = route1;
    }
    /**@return depart time*/
    public LocalDateTime getDepartTime() {
        return departTime;
    }
    /**@param depart -*/
    public void setDepartTime(final LocalDateTime depart) {
        this.departTime = depart;
    }
    /**@return */
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
    /**@param arriv -*/
    public void setArrivalTime(final LocalDateTime arriv) {
        this.arrivalTime = arriv;
    }

    public List<RailwayCarriage> getCarriages() {
        return carriages;
    }

    public void setCarriages(
            List<RailwayCarriage> carriages) {
        this.carriages = carriages;
    }

    /***/
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Train train = (Train) o;
        return identificator == train.identificator
                && railwayCarriages == train.railwayCarriages
                && brigade == train.brigade
                && trainType == train.trainType
                && route.equals(train.route)
                && departTime.equals(train.departTime)
                && arrivalTime.equals(train.arrivalTime)
                && carriages.equals(train.carriages);
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(identificator, trainType, railwayCarriages, brigade,
                        route, departTime, arrivalTime, carriages);
    }
}
