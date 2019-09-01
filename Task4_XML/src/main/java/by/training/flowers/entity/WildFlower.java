package by.training.flowers.entity;

import java.util.List;
import java.util.Objects;

/**
 * Wild flower class.
 * */
public class WildFlower extends AbstractFlower {
    /**
     * Is protected.
     * */
    private boolean isProtected;

    private List<String> regions;

    public boolean isProtected() {
        return isProtected;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegion(final String region) {
        regions.add(region);
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }

    public void setProtected(boolean aProtected) {
        isProtected = aProtected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WildFlower that = (WildFlower) o;
        return isProtected == that.isProtected &&
                regions.equals(that.regions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isProtected, regions);
    }

    @Override
    public String toString() {
        return "WildFlower{" +
                "isProtected=" + isProtected +
                ", regions=" + regions +
                '}';
    }
}
