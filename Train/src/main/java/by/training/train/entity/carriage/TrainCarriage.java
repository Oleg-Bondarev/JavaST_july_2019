package by.training.train.entity.carriage;

import java.util.Objects;

public abstract class TrainCarriage {
    /**Unique carrriage identifier.*/
    private static int identificator = 1;
    /**Carriage staff.*/
    private int brigade;

    /**Default constructor.*/
    public TrainCarriage() {
        brigade = 2;
    }

    /**Constructor  with parameters.
     * @param newBrigade -count of carriage staff.*/
    public TrainCarriage(final int newBrigade) {
        this.brigade = newBrigade;
    }

    /**Getter for carriage ID.
     * @return ID of the carriage.*/
    public static int getIdentificator() {
        return identificator;
    }

    /**public static void setIdentificator(int identificator) {
        TrainCarriage.identificator = identificator;
    }*/

    /**Getter for count of carriage staff.
     * @return count of carriage staff.*/
    public int getBrigade() {
        return brigade;
    }

    /**Setter.
     * @param newBrigade - count of carriage staff.*/
    public void setBrigade(final int newBrigade) {
        this.brigade = newBrigade;
    }

    /**Increment carriage ID when we create new carriage.*/
    public static void incrementID() {
        identificator++;
    }

    /**Overriding the equality method.
     * @param o -object.*/
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrainCarriage that = (TrainCarriage) o;
        return brigade == that.brigade;
    }

    /**Overriding the hashCode method.
     * @return hashcode.*/
    @Override
    public int hashCode() {
        return Objects.hash(brigade);
    }

    /**Overriding the toString method.
     * @return string.*/
    @Override
    public String toString() {
        return "TrainCarriage{"
                + "id=" + identificator
                + "brigade=" + brigade
                + '}';
    }
}
