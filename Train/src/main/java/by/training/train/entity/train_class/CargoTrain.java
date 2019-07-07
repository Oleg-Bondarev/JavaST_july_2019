package by.training.train.entity.train_class;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import by.training.train.entity.enums.TrainType;

public class CargoTrain extends Train {
    /**Useful weight (in tons) carried by train.*/
    private double maximumCargoWeight;
    private double defaultMaxWeighth = 27.5;

    /***/
    public CargoTrain() {
        super();
        maximumCargoWeight = defaultMaxWeighth;
    }

    public CargoTrain(final TrainType type, final int countCarriages,
            final int brig, final String route, final LocalDateTime depart,
            final LocalDateTime arriv, final List carriages,
            final double maxCargoWeight) {
        super(type, countCarriages, brig, route, depart, arriv, carriages);
        this.maximumCargoWeight = maxCargoWeight;
    }

    public double getMaximumCargoWeight() {
        return maximumCargoWeight;
    }

    public void setMaximumCargoWeight(double maximumCargoWeight) {
        this.maximumCargoWeight = maximumCargoWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        CargoTrain that = (CargoTrain) o;
        return Double.compare(that.maximumCargoWeight, maximumCargoWeight) == 0
                && Double.compare(that.defaultMaxWeighth, defaultMaxWeighth) == 0;
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(super.hashCode(), maximumCargoWeight, defaultMaxWeighth);
    }

    @Override
    public String toString() {
        return "CargoTrain{"
                + "maximumCargoWeight=" + maximumCargoWeight
                + ", defaultMaxWeighth=" + defaultMaxWeighth
                + '}';
    }
}
