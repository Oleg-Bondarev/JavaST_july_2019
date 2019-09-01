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

    public ArtificialFlower() {
        scientistsNames = new ArrayList<>();
    }

    public List<String> getScientistsNames() {
        return new ArrayList<>(scientistsNames);
    }

    public void setScientistName(final String name) {
        scientistsNames.add(name);
    }

    public void setScientistsNames(final List<String> scientists) {
        if (scientists != null) {
            this.scientistsNames = scientistsNames;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ArtificialFlower that = (ArtificialFlower) o;
        return scientistsNames.equals(that.scientistsNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), scientistsNames);
    }

    @Override
    public String toString() {
        return "ArtificialFlower{" +
                "scientistsNames=" + scientistsNames +
                '}';
    }
}
