package by.training.final_task.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents class.
 * */
public class CouponUser {
    /**
     * Registration date time.
     * */
    private LocalDateTime registrationDateTime;
    /**
     * Identify.
     * */
    private int registrationId;
    /***/
    public CouponUser() { }

    public LocalDateTime getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(final LocalDateTime
                                                newRegistrationDateTime) {
        registrationDateTime = newRegistrationDateTime;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(final int newRegistrationId) {
        registrationId = newRegistrationId;
    }

    @Override
    public boolean equals(final Object newO) {
        if (this == newO) {
            return true;
        }
        if (newO == null || getClass() != newO.getClass()) {
            return false;
        }
        CouponUser that = (CouponUser) newO;
        return registrationId == that.registrationId
                && registrationDateTime.equals(that.registrationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationDateTime, registrationId);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Coupon user{");
        builder.append("registration id=");
        builder.append(registrationId);
        builder.append(", registration date and time=");
        builder.append(registrationDateTime);
        builder.append("}");
        return builder.toString();
    }
}
