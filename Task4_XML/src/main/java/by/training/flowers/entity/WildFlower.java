package by.training.flowers.entity;

import java.util.Objects;

/**
 * Wild flower class.
 * */
public class WildFlower extends AbstractFlower {
    /**
     * Is protected.
     * */
    private boolean isProtected;
    /**
     * @return boolean.
     * */
    public boolean isProtected() {
        return isProtected;
    }
    /**
     * @param protect -boolean value.
     * */
    public void setProtected(final boolean protect) {
        isProtected = protect;
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
        WildFlower that = (WildFlower) o;
        return isProtected == that.isProtected;
    }
    /**
     * @return hashcode.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isProtected);
    }
    /**
     * @return string.
     * */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append(" WildFlower{ isProtected=" + isProtected + "}\n");
        return builder.toString();
    }
}
