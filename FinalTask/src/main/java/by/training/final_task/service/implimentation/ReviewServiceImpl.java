package by.training.final_task.service.implimentation;

import by.training.final_task.dao.interfases.ReviewsDAO;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Reviews;
import by.training.final_task.exception.PersistentException;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.ReviewService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReviewServiceImpl extends ServiceImpl implements ReviewService {

    private static final Logger LOGGER = LogManager.getLogger();
    private ReviewsDAO reviewsDAO;

    public ReviewServiceImpl(final Connection newConnection) {
        super(newConnection);
    }

    @Override
    public List<Reviews> getAllReviewsCurrentUser(final long userId,
                                            final int offset, final int limit)
            throws ServiceException {
        try {
            prepareReviewDao(DAOEnum.REVIEWS);
            List<Reviews> reviewsList = reviewsDAO.getAllReviewsCurrentUser(
                                                        userId, offset, limit);
            commitAndChangeAutoCommit();
            return reviewsList;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<Reviews> getAllReviewsCurrentCoupon(final long couponId,
                                        final int offset, final int limit)
            throws ServiceException {
        try {
            prepareReviewDao(DAOEnum.REVIEWS);
            List<Reviews> reviewsList = reviewsDAO.getAllReviewsCurrentCoupon(
                    couponId, offset, limit);
            commitAndChangeAutoCommit();
            return reviewsList;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAllCount() throws ServiceException {
        try {
            prepareReviewDao(DAOEnum.REVIEWS);
            int amount = reviewsDAO.getAllCount();
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getCountReviewsCurrentUser(final long userId)
            throws ServiceException {
        try {
            prepareReviewDao(DAOEnum.REVIEWS);
            int amount = reviewsDAO.getCountReviewsCurrentUser(userId);
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getCountReviewsCurrentCoupon(final long couponId)
            throws ServiceException {
        try {
            prepareReviewDao(DAOEnum.REVIEWS);
            int amount = reviewsDAO.getCountReviewsCurrentCoupon(couponId);
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int create(final Reviews review) throws ServiceException {
        try {
            prepareReviewDao(DAOEnum.REVIEWS);
            int index = reviewsDAO.create(review);
            commitAndChangeAutoCommit();
            return index;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public boolean update(final Reviews element) throws ServiceException {
        try {
            prepareReviewDao(DAOEnum.REVIEWS);
            boolean statement = reviewsDAO.update(element);
            commitAndChangeAutoCommit();
            return statement;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public boolean delete(final Reviews element) throws ServiceException {
        try {
            prepareReviewDao(DAOEnum.REVIEWS);
            boolean statement = reviewsDAO.delete(element);
            commitAndChangeAutoCommit();
            return statement;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public boolean delete(final long id) throws ServiceException {
        try {
            prepareReviewDao(DAOEnum.REVIEWS);
            boolean statement = reviewsDAO.delete(id);
            commitAndChangeAutoCommit();
            return statement;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    private void prepareReviewDao(final DAOEnum classType)
            throws ServiceException {
        try {
            connection.setAutoCommit(false);
            reviewsDAO = (ReviewsDAO) createDAO(classType);
        } catch (SQLException newE) {
            rollbackTransaction();
            LOGGER.log(Level.ERROR, "Have some problems in setting" +
                    " autocommit transaction property.", newE);
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    private void commitAndChangeAutoCommit() throws ServiceException {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException newE) {
            rollbackTransaction();
            LOGGER.log(Level.ERROR, "Have some problems in setting" +
                    " autocommit transaction property.", newE);
            throw new ServiceException(newE.getMessage(), newE);
        }
    }
}
