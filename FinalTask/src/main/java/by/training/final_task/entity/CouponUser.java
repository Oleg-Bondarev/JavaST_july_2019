package by.training.final_task.entity;

import java.util.Date;
import java.util.Objects;

/**
 * Represents class.
 * */
public class CouponUser extends  Entity {
    /**
     * Registration date time.
     * */
    private Date registrationDateTime;
    /**
     * Identify.
     * */
    private int registrationId;
    private int couponId;
    private int userId;
    /***/
    public CouponUser() { }

    public Date getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(final Date newRegistrationDateTime) {
        registrationDateTime = newRegistrationDateTime;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(final int newRegistrationId) {
        registrationId = newRegistrationId;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(final int newCouponId) {
        couponId = newCouponId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int newUserId) {
        userId = newUserId;
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
                && registrationDateTime.equals(that.registrationDateTime)
                && couponId == that.couponId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationDateTime, registrationId, couponId,
                userId);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Coupon user{");
        builder.append("registration id=");
        builder.append(registrationId);
        builder.append(", registration date and time=");
        builder.append(registrationDateTime);
        builder.append("coupon id=");
        builder.append(couponId);
        builder.append(", user id=");
        builder.append(userId);
        builder.append("}");
        return builder.toString();
    }
}
