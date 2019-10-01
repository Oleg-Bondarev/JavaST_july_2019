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
    private Coupon coupon;
    private User user;

    public Reviews() { }

    public Reviews(final long newId, final String newReview,
                   final Coupon newCoupon, final User newUser) {
        id = newId;
        review = newReview;
        coupon = newCoupon;
        user = newUser;
    }

    public String getReview() {
        return review;
    }

    public void setReview(final String newReview) {
        review = newReview;
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
        Reviews rewiews = (Reviews) newO;
        return review.equals(rewiews.review) && coupon.equals(rewiews.coupon)
                && user.equals(rewiews.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), review, coupon, user);
    }

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
