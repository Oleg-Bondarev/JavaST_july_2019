package by.training.final_task.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Represent coupon category.
 * */
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
