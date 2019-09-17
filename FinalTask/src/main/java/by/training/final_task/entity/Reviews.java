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
    private int couponId;
    private int userId;

    public Reviews() { }

    public String getReview() {
        return review;
    }

    public void setReview(final String newReview) {
        review = newReview;
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
        Reviews rewiews = (Reviews) newO;
        return review.equals(rewiews.review) && couponId == rewiews.couponId
                && userId == rewiews.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), review, couponId, userId);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Review{");
        builder.append(super.toString());
        builder.append(", review=");
        builder.append(review);
        builder.append(", coupon id=");
        builder.append(couponId);
        builder.append(", user id=");
        builder.append(userId);
        builder.append("}");
        return builder.toString();
    }
}
