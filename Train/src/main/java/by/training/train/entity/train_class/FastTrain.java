package by.training.train.entity.train_class;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import by.training.train.entity.enums.TrainType;

/***/
public class FastTrain extends PassengerTrain {
    /**Maximum speed of the train.*/
    private double maxSpeed;
    /***/
    private final static double defaultMaxSpeed = 100.0;
    /***/
    public FastTrain() {
        super();
        maxSpeed = defaultMaxSpeed;
    }
    /**@param passengers -
     * @param time -
     * @param maxSp - maximum speed of the passenger train.*/
    public FastTrain(final int passengers, final double time,
            final double maxSp) {
        super(passengers, time);
        this.maxSpeed = maxSp;
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
     * @param maxSp -*/
    public FastTrain(final TrainType type, final int countCarriages,
            final int brig, final String route, final LocalDateTime depart,
            final LocalDateTime arriv, final List carriages,
            final int passengers, final double luag, final double time,
            final List<String> stations, final double maxSp) {
        super(type, countCarriages, brig, route, depart, arriv, carriages,
                passengers, luag, time, stations);
        this.maxSpeed = maxSp;
    }

    /**@return max speed of train.*/
    public double getMaxSpeed() {
        return maxSpeed;
    }
    /**@param maxSp -*/
    public void setMaxSpeed(final double maxSp) {
        this.maxSpeed = maxSp;
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
        FastTrain fastTrain = (FastTrain) o;
        return Double.compare(fastTrain.maxSpeed, maxSpeed) == 0;
    }
    /***/
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxSpeed);
    }
    /***/
    @Override
    public String toString() {
        return "FastTrain{"
                + "maxSpeed=" + maxSpeed
                + '}';
    }
}
