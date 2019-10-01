package by.training.final_task.entity;

import java.util.Objects;

/**
 * Represent coupon category.
 * */
public class Category extends  Entity{
    private String name;

    public Category() { }

    public Category(final long newId) {
        id = newId;
    }

    public Category(final long newId, final String newName) {
        id = newId;
        name = newName;
    }

    public String getName() {
        return name;
    }

    public void setName(final String newName) {
        name = newName;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Category{" + super.toString() +
                "name='" + name + '\'' +
                '}';
    }
}
/*
public enum Category {
    ENTERTAINMENT("entertainment and showprogram"),
    EXCLUSIVE("exclusive"),
    QUESTS("quests"),
    LEISURE("leisure"),
    PETS("pets"),
    PHOTO_VIDEO("photo and video"),
    TRAVELS("travels"),
    ROMANTIC("romantic"),
    HEALTH_BEAUTY("health and beauty"),
    GOURMENTS("gourments"),
    MOTORISTS("motorists"),
    TRAINING("training and master classes"),
    GIFF_BASKETS("gift baskets"),
    FITNESS_SPORT("fitness and sport");

    private String value;

    Category(final String newValue) {
        value = newValue;
    }

    public String getValue() {
        return value;
    }

    public static Category fromValue(final String newValue)
            throws WrongEnumTupeException {
        for (Category category : Category.values()) {
            if (category.value.equals(newValue)) {
                return category;
            }
        }
        throw new WrongEnumTupeException(newValue);
    }

    public static Category valueOf(final int index) {
        return Category.values()[index];
    }

    public int getOrdinal() {
        return ordinal();
    }
}
*/
