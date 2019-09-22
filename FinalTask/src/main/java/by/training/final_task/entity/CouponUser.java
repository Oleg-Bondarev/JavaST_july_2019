package by.training.final_task.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents class.
 * */
public class CouponUser extends  Entity {
    /**
     * Registration date time.
     * */
    private LocalDate registrationDateTime;
    /**
     * Identify.
     * */
    private long couponId;
    private long userId;
    /***/
    public CouponUser() { }

    public CouponUser(final long newId, final LocalDate newRegDate,
                      final long newCouponId, final long newUserId) {
        id = newId;
        registrationDateTime = newRegDate;
        couponId = newCouponId;
        userId = newUserId;
    }

    public LocalDate getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(final LocalDate newRegistrationDateTime) {
        registrationDateTime = newRegistrationDateTime;
    }

    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(final int newCouponId) {
        couponId = newCouponId;
    }

    public long getUserId() {
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
        return registrationDateTime.equals(that.registrationDateTime)
                && couponId == that.couponId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), registrationDateTime, couponId,
                userId);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Coupon user{");
        builder.append(super.toString());
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
