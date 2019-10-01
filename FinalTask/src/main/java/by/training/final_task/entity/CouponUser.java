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
    private Coupon coupon;
    private User user;
    /***/
    public CouponUser() { }

    public CouponUser(final long newId, final LocalDate newRegDate,
                      final Coupon newCoupon, final User newUser) {
        id = newId;
        registrationDateTime = newRegDate;
        coupon = newCoupon;
        user = newUser;
    }

    public LocalDate getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(final LocalDate newRegistrationDateTime) {
        registrationDateTime = newRegistrationDateTime;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(final Coupon newCoupon) {
        coupon = newCoupon;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User newUser) {
        user = newUser;
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
                && coupon.equals(that.coupon) && user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), registrationDateTime, coupon,
                user);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Coupon user{");
        builder.append(super.toString());
        builder.append(", registration date and time=");
        builder.append(registrationDateTime);
        builder.append("coupon=");
        builder.append(coupon);
        builder.append(", user=");
        builder.append(user);
        builder.append("}");
        return builder.toString();
    }
}
