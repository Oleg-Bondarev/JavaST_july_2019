package by.training.flowers.entity;

import java.util.Objects;

/**
 * Wild flower class.
 * */
public class WildFlower extends AbstractFlower {
    /**
     * Is protected.
     * */
    private boolean protect;
    /**
     * @return boolean.
     * */
    public boolean isProtect() {
        return protect;
    }
    /**
     * @param newProtect -boolean value.
     * */
    public void setProtect(final boolean newProtect) {
        protect = newProtect;
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
        return protect == that.protect;
    }
    /**
     * @return hashcode.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), protect);
    }
    /**
     * @return string.
     * */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append(" WildFlower{ isProtected=" + protect + "}\n");
        return builder.toString();
    }
}
