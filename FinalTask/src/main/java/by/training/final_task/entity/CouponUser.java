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
     * Coupon object.
     * */
    private Coupon coupon;
    /**
     * User object.
     * */
    private User user;

    /**
     * Constructor.
     * @param newId id to be set to {@link #id}
     * @param newRegDate registration date to be set
     *                  to {@link #registrationDateTime}
     * @param newCoupon coupon to be set to {@link #coupon}
     * @param newUser user to be set to {@link #user}
     * */
    public CouponUser(final long newId, final LocalDate newRegDate,
                      final Coupon newCoupon, final User newUser) {
        id = newId;
        registrationDateTime = newRegDate;
        coupon = newCoupon;
        user = newUser;
    }
    /**
     * @return registration date.
     * */
    public LocalDate getRegistrationDateTime() {
        return registrationDateTime;
    }
    /**
     * Sets registration date.
     * @param newRegistrationDateTime date to be set
     *                               to {@link #registrationDateTime}
     * */
    public void setRegistrationDateTime(final LocalDate
                                                newRegistrationDateTime) {
        registrationDateTime = newRegistrationDateTime;
    }
    /**
     * @return coupon object.
     * */
    public Coupon getCoupon() {
        return coupon;
    }
    /**
     * Sets coupon object.
     * @param newCoupon coupon to be set to {@link #coupon}
     * */
    public void setCoupon(final Coupon newCoupon) {
        coupon = newCoupon;
    }
    /**
     * @return user object.
     * */
    public User getUser() {
        return user;
    }
    /**
     * Sets user object.
     * @param newUser user to be set to {@link #user}
     * */
    public void setUser(final User newUser) {
        user = newUser;
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
        CouponUser that = (CouponUser) newO;
        return registrationDateTime.equals(that.registrationDateTime)
                && coupon.equals(that.coupon) && user.equals(that.user);
    }
    /**
     * Hashcode method.
     * @return object hashcode.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), registrationDateTime, coupon,
                user);
    }
    /**
     * @return object string representation.
     * */
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
