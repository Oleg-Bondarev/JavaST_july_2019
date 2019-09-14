package by.training.final_task.entity;

import java.util.Objects;

/**
 * Represent reviews.
 * */
public class Rewiews extends Entity {
    /**
     * Review.
     * */
    private String review;

    public Rewiews() { }

    public String getReview() {
        return review;
    }

    public void setReview(final String newReview) {
        review = newReview;
    }

    @Override
    public boolean equals(final Object newO) {
        if (this == newO) {
            return true;
        }
        if (newO == null || getClass() != newO.getClass()) {
            return false;
        }
        Rewiews rewiews = (Rewiews) newO;
        return review.equals(rewiews.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), review);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Review{");
        builder.append(super.toString());
        builder.append(", review=");
        builder.append(review);
        builder.append("}");
        return builder.toString();
    }
}
