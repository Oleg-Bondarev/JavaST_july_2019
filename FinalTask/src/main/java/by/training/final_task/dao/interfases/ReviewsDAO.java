package by.training.final_task.dao.interfases;

import by.training.final_task.entity.Reviews;
import by.training.final_task.exception.PersistentException;

import java.util.List;

public interface ReviewsDAO extends DAO<Reviews> {
    List<Reviews> getAllReviewsCurrentUser(long userId, int offset, int limit)
        throws PersistentException;
    List<Reviews> getAllReviewsCurrentCoupon(long couponId, int offset,
        int limit) throws PersistentException;
    int getAllCount() throws PersistentException;
    int getCountReviewsCurrentUser(long userId) throws PersistentException;
    int getCountReviewsCurrentCoupon(long couponId) throws PersistentException;
}
