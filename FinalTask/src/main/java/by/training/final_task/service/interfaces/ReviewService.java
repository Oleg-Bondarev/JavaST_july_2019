package by.training.final_task.service.interfaces;

import by.training.final_task.entity.Reviews;
import by.training.final_task.service.ServiceException;

import java.util.List;

public interface ReviewService extends Service {
    List<Reviews> getAllReviewsCurrentUser(long userId, int offset, int limit)
            throws ServiceException;
    List<Reviews> getAllReviewsCurrentCoupon(long couponId, int offset,
                                             int limit) throws ServiceException;
    int getAllCount() throws ServiceException;
    int getCountReviewsCurrentUser(long userId) throws ServiceException;
    int getCountReviewsCurrentCoupon(long couponId) throws ServiceException;
    int create(Reviews review) throws ServiceException;
    boolean update(Reviews element) throws ServiceException;
    boolean delete(Reviews element) throws ServiceException;
    boolean delete(long id) throws ServiceException;
}
