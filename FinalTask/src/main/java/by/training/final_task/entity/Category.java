package by.training.final_task.entity;

import java.util.Objects;

/**
 * Represent coupon category.
 * */
public class Category extends Entity {
    /**
     * Default class constructor.
     * */
    public Category() { }
    /**
     * Category name.
     * */
    private String name;
    /**
     * Constructor.
     * @param newId id to be set to {@link #id}
     * @param newName category name to be set to {@link #name}
     * */
    public Category(final long newId, final String newName) {
        id = newId;
        name = newName;
    }
    /**
     * Returns category name.
     * @return category name.
     * */
    public String getName() {
        return name;
    }
    /**
     * Sets category name.
     * @param newName category name to be set to {@link #name}
     * */
    public void setName(final String newName) {
        name = newName;
    }
    /**
     * Override equals method.
     * @param newO object to compare.
     * @return boolean expression true if object equals, else - return false.
     * */
    @Override
    public boolean equals(final Object newO) {
        if (this == newO) {
            return true;
        }
        if (newO == null || getClass() != newO.getClass()) {
            return false;
        }
        if (!super.equals(newO)) {
            return false;
        }
        Category category = (Category) newO;
        return name.equals(category.name);
    }
    /**
     * Hashcode method.
     * @return object hashcode.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
    /**
     * @return object string representation.
     * */
    @Override
    public String toString() {
        return "Category{" + super.toString() + "name='" + name + '\'' + '}';
    }
}
