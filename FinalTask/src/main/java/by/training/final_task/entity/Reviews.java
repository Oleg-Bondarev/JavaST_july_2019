package by.training.final_task.entity;

import java.util.Objects;

/**
 * Represent reviews.
 * */
public class Reviews extends Entity {
    /**
     * Review.
     * */
    private String review;
    /**
     * Coupon on wich there is a review.
     * */
    private Coupon coupon;
    /**
     * User which left a review on coupon.
     * */
    private User user;

    /***/
    public Reviews() { }
    /**
     * Constructor.
     * @param newId id to be set to {@link #id}
     * @param newReview review to be set to {@link #review}
     * @param newUser user to be set to {@link #user}
     * @param newCoupon coupon to be set to {@link #coupon}
     * */
    public Reviews(final long newId, final String newReview,
                   final Coupon newCoupon, final User newUser) {
        id = newId;
        review = newReview;
        coupon = newCoupon;
        user = newUser;
    }
    /**
     * @return {@link #review}
     * */
    public String getReview() {
        return review;
    }
    /**
     * Sets review.
     * @param newReview review to be set to {@link #review}
     * */
    public void setReview(final String newReview) {
        review = newReview;
    }
    /**
     * @return {@link #coupon}
     * */
    public Coupon getCoupon() {
        return coupon;
    }
    /**
     * Sets coupon.
     * @param newCoupon coupon to be set to {@link #coupon}
     * */
    public void setCoupon(final Coupon newCoupon) {
        coupon = newCoupon;
    }
    /**
     * @return {@link #user}
     * */
    public User getUser() {
        return user;
    }
    /**
     * Sets user.
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
        Reviews rewiews = (Reviews) newO;
        return review.equals(rewiews.review) && coupon.equals(rewiews.coupon)
                && user.equals(rewiews.user);
    }
    /**
     * Hashcode method.
     * @return object hashcode.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), review, coupon, user);
    }
    /**
     * @return object string representation.
     * */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Review{");
        builder.append(super.toString());
        builder.append(", review=");
        builder.append(review);
        builder.append(", coupon=");
        builder.append(coupon);
        builder.append(", user=");
        builder.append(user);
        builder.append("}");
        return builder.toString();
    }
}
