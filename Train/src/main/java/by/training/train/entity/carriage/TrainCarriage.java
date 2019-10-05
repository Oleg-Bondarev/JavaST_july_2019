package by.training.train.entity.carriage;

import java.util.Objects;

public abstract class TrainCarriage {
    /**Unique carriage identifier.*/
    private final long identificator;
    /***/
    private static long id = 0;
    /**Carriage staff.*/
    private int brigade;

    /**Default constructor.*/
    public TrainCarriage() {
        identificator = ++id;
        brigade = 2;
    }

    /**Constructor  with parameters.
     * @param newBrigade -count of carriage staff.
     * */
    public TrainCarriage(final int newBrigade) {
        identificator = ++id;
        this.brigade = newBrigade;
    }

    /**Constructor with parameters.
     * @param trainCarriageNew -*/
    public TrainCarriage(final TrainCarriage trainCarriageNew) {
        identificator = ++id;
        brigade = trainCarriageNew.getBrigade();
    }

    /**Getter for carriage ID.
     * @return ID of the carriage.*/
    public long getIdentificator() {
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
        return  " id=" + identificator
                + ", brigade=" + brigade
                + ", ";
    }
}
