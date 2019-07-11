package by.training.train.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import by.training.train.entity.carriage.TrainCarriage;

public class Train {
    /**List of carriages.*/
    private List<TrainCarriage> train;
    /**Default constructor.*/
    public Train() {
        train = new ArrayList<>(0);
    }
    /**@param newListTrain - new train.*/
    public Train(final List<TrainCarriage> newListTrain) {
        this.train = newListTrain;
    }
    /**@return list of carriages - train.*/
    public List<TrainCarriage> getTrain() {
        return train;
    }
    /**@param newTrain -new train.*/
    public void setTrain(final List<TrainCarriage> newTrain) {
        this.train = newTrain;
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
        Train train1 = (Train) o;
        return train.equals(train1.train);
    }
    /**@return hash-code.*/
    @Override
    public int hashCode() {
        return Objects.hash(train);
    }
    /**@return string.*/
    @Override
    public String toString() {
        return "Train{"
                + "train=" + train
                + '}';
    }
}
