package by.training.final_task.entity;

import java.util.Objects;

/**
 * Represents class with common field id.
 * */
public class Entity {
    /**
     * Identificator.
     * */
    protected long id;

    /**
     * @return id.
     * */
    public long getId() {
        return id;
    }
    /**
     * Sets id.
     * @param newId id to be set to {@link #id}
     * */
    public void setId(long newId) {
        id = newId;
    }
    /**
     * Override equals method.
     * @param newO object to compare.
     * @return boolean expression true if object equals, else - return false.
     * */
    @Override
    public boolean equals(Object newO) {
        if (this == newO) {
            return true;
        }
        if (newO == null || getClass() != newO.getClass()) {
            return false;
        }
        Entity entity = (Entity) newO;
        return id == entity.id;
    }
    /**
     * Hashcode method.
     * @return object hashcode.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    /**
     * @return object string representation.
     * */
    @Override
    public String toString() {
        return "id=" + id;
    }
}
