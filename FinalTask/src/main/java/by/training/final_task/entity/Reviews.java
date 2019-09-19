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
    private long couponId;
    private long userId;

    public Reviews() { }

    public Reviews(final long newId, final String newReview,
                   final long newCouponId, final long newUserId) {
        id = newId;
        review = newReview;
        couponId = newCouponId;
        userId = newUserId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(final String newReview) {
        review = newReview;
    }

    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(final long newCouponId) {
        couponId = newCouponId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(final long newUserId) {
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
