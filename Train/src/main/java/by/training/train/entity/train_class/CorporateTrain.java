package by.training.train.entity.train_class;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import by.training.train.entity.enums.TrainType;

/***/
public class CorporateTrain extends PassengerTrain {
    /**Name of the corporate train.*/
    private String trainName;

    /***/
    public CorporateTrain() {
        super();
        trainName = "Simple train name.";
    }
    /**@param passengers -
     * @param time -
     * @param name -*/
    public CorporateTrain(final int passengers, final double time,
                        final String name) {
        super(passengers, time);
        this.trainName = name;
    }
    /**@param type -
     * @param countCarriages -
     * @param brig -
     * @param route -
     * @param depart -
     * @param arriv -
     * @param passengers -
     * @param luag -
     * @param time -
     * @param stations -
     * @param name -*/
    public CorporateTrain(final TrainType type, final int countCarriages,
            final int brig, final String route, final LocalDateTime depart,
            final LocalDateTime arriv, final List carriages,
            final int passengers, final double luag, final double time,
            final List<String> stations, final String name) {
        super(type, countCarriages, brig, route, depart, arriv, carriages,
                passengers, luag, time, stations);
        this.trainName = name;
    }
    /**@return */
    public String getTrainName() {
        return trainName;
    }
    /**@param name -*/
    public void setTrainName(final String name) {
        this.trainName = name;
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
        CorporateTrain that = (CorporateTrain) o;
        return trainName.equals(that.trainName);
    }
    /***/
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), trainName);
    }

    /***/
    @Override
    public String toString() {
        return "CorporateTrain{"
                + "trainName='" + trainName + '\''
                + '}';
    }
}
