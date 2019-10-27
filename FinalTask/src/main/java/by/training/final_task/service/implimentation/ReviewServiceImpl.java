package by.training.final_task.service.implimentation;

import by.training.final_task.dao.interfases.AbstractConnectionManager;
import by.training.final_task.dao.interfases.DaoFactory;
import by.training.final_task.dao.interfases.ReviewsDAO;
import by.training.final_task.dao.sql.ConnectionManager;
import by.training.final_task.entity.Reviews;
import by.training.final_task.dao.PersistentException;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.ReviewService;

import java.util.List;

public class ReviewServiceImpl extends AbstractService
        implements ReviewService {

    public ReviewServiceImpl(final DaoFactory newFactory) {
        super(newFactory);
    }

    public ReviewServiceImpl() {
        super();
    }

    @Override
    public List<Reviews> getAllReviewsCurrentUser(final long userId,
                                                  final int offset,
                                                  final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            try {
                ReviewsDAO reviewsDAO = getDaoFactory()
                        .createReviewsDAO(connectionManager);
                return reviewsDAO.getAllReviewsCurrentUser(userId, offset,
                        limit);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public List<Reviews> getAllReviewsCurrentCoupon(final long couponId,
                                                    final int offset,
                                                    final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            try {
                ReviewsDAO reviewsDAO = getDaoFactory()
                        .createReviewsDAO(connectionManager);
                return reviewsDAO.getAllReviewsCurrentCoupon(couponId, offset,
                        limit);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getAllCount() throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            try {
                ReviewsDAO reviewsDAO = getDaoFactory()
                        .createReviewsDAO(connectionManager);
                return reviewsDAO.getAllCount();
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getCountReviewsCurrentUser(final long userId)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            try {
                ReviewsDAO reviewsDAO = getDaoFactory()
                        .createReviewsDAO(connectionManager);
                return reviewsDAO.getCountReviewsCurrentUser(userId);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getCountReviewsCurrentCoupon(final long couponId)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            try {
                ReviewsDAO reviewsDAO = getDaoFactory()
                        .createReviewsDAO(connectionManager);
                return reviewsDAO.getCountReviewsCurrentCoupon(couponId);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int create(final Reviews review) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            try {
                ReviewsDAO reviewsDAO = getDaoFactory()
                        .createReviewsDAO(connectionManager);
                int userId = reviewsDAO.create(review);
                review.setId(userId);
                connectionManager.commitChange();
                return userId;
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public boolean update(final Reviews element) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            try {
                ReviewsDAO reviewsDAO = getDaoFactory()
                        .createReviewsDAO(connectionManager);
                boolean statement = reviewsDAO.update(element);
                connectionManager.commitChange();
                return statement;
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public boolean delete(final Reviews element) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            try {
                ReviewsDAO reviewsDAO = getDaoFactory()
                        .createReviewsDAO(connectionManager);
                boolean statment = reviewsDAO.delete(element);
                connectionManager.commitChange();
                return statment;
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public boolean delete(final long id) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            try {
                ReviewsDAO reviewsDAO = getDaoFactory()
                        .createReviewsDAO(connectionManager);
                boolean statement = reviewsDAO.delete(id);
                connectionManager.commitChange();
                return statement;
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }
}
