package by.training.train.entity.carriage;

import java.util.Objects;

public class PostCarriage extends SpecialCarriage {
    /**Mail load(in ton).*/
    private double mailLoad;
    /**MAX load of the carriage(in ton).*/
    private static final double MAX_LOAD = 20;
    /**def constructor.*/
    public PostCarriage() {
        super();
        this.mailLoad = MAX_LOAD;
    }
    /**@param brigade -
     * @param newLoad - load of carriage.*/
    public PostCarriage(final int brigade, final double newLoad) {
        super(brigade);
        this.mailLoad = newLoad;
    }
    /**@return load of mail in the carriage.*/
    public double getMailLoad() {
        return mailLoad;
    }
    /**@param newLoad -*/
    public void setMailLoad(final double newLoad) {
        this.mailLoad = newLoad;
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
        PostCarriage that = (PostCarriage) o;
        return Double.compare(that.mailLoad, mailLoad) == 0;
    }
    /**@return hashCode*/
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mailLoad);
    }
    /**@return string*/
    @Override
    public String toString() {
        return "PostCarriage{"
                + "mailLoad=" + mailLoad
                + '}';
    }
}
