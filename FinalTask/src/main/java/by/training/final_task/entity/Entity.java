package by.training.final_task.entity;

import java.util.Objects;

public class Entity {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long newId) {
        id = newId;
    }


    @Override
    public boolean equals(Object newO) {
        if (this == newO) return true;
        if (newO == null || getClass() != newO.getClass()) return false;
        Entity entity = (Entity) newO;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
