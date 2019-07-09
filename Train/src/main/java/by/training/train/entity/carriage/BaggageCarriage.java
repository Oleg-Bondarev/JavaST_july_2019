package by.training.train.entity.carriage;

import java.util.Objects;

public class BaggageCarriage extends SpecialCarriage {
    /**Load of baggage in the carriage(in ton).*/
    private double baggageLoad;
    /**MAX load of the baggage(in ton).*/
    private static final double MAX_LOAD = 10;

    /**def constructor.*/
    public BaggageCarriage() {
        super();
        this.baggageLoad = MAX_LOAD;
    }
    /**@param brigade -
     * @param newLoad - load of carriage.*/
    public BaggageCarriage(final int brigade, final double newLoad) {
        super(brigade);
        this.baggageLoad = newLoad;
    }
    /**@return load of baggage in the carriage.*/
    public double getBaggageLoad() {
        return baggageLoad;
    }
    /**@param newLoad -*/
    public void setBaggageLoad(final double newLoad) {
        this.baggageLoad = newLoad;
    }
    /**@param o - object.*/
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
        BaggageCarriage that = (BaggageCarriage) o;
        return Double.compare(that.baggageLoad, baggageLoad) == 0;
    }
    /**@return hashCode*/
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), baggageLoad);
    }
    /**@return string*/
    @Override
    public String toString() {
        return "BaggageCarriage{"
                + "baggageLoad=" + baggageLoad
                + '}';
    }
}
