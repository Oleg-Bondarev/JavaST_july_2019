package by.training.flowers.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Artificial flower class.
 * */
public class ArtificialFlower extends AbstractFlower {
    /**
     * Names of scientists.
     * */
    private List<String> scientistsNames;
    /**
     * Constructor.
     * */
    public ArtificialFlower() {
        scientistsNames = new ArrayList<>();
    }
    /**
     * @return list.
     * */
    public List<String> getScientistsNames() {
        return new ArrayList<>(scientistsNames);
    }
    /**
     * @param name -name.
     * */
    public void setScientistName(final String name) {
        scientistsNames.add(name);
    }
    /**
     * @param scientists -list.
     * */
    public void setScientistsNames(final List<String> scientists) {
        if (scientists != null) {
            this.scientistsNames = scientists;
        }
    }
    /**
     * @param o -obj.
     * @return bool.
     * */
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
        ArtificialFlower that = (ArtificialFlower) o;
        return scientistsNames.equals(that.scientistsNames);
    }
    /**
     * @return hashcode.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), scientistsNames);
    }
    /**
     * @return string.
     * */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append(" ArtificialFlower{ scientistsNames=" + scientistsNames
                + "}\n");
        return builder.toString();
    }
}
