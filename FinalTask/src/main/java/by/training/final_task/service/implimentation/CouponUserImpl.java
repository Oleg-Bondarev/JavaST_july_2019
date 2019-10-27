package by.training.final_task.service.implimentation;

import by.training.final_task.dao.interfases.AbstractConnectionManager;
import by.training.final_task.dao.interfases.CouponUserDAO;
import by.training.final_task.dao.interfases.DaoFactory;
import by.training.final_task.dao.sql.ConnectionManager;
import by.training.final_task.entity.Coupon;
import by.training.final_task.entity.CouponUser;
import by.training.final_task.dao.PersistentException;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CouponUserService;

import java.time.LocalDate;
import java.util.List;

public class CouponUserImpl extends AbstractService
        implements CouponUserService {

    public CouponUserImpl() {
        super();
    }

    public CouponUserImpl(final DaoFactory newFactory) {
        super(newFactory);
    }

    @Override
    public List<Coupon> getAllCouponsCurrentUser(final long userId,
                                                 final int offset,
                                                 final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            try {
                CouponUserDAO couponUserDAO = getDaoFactory()
                        .createCouponUserDAO(connectionManager);
                return couponUserDAO.getAllCouponsCurrentUser(userId, offset,
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
    public List<Coupon> getAllBetweenDatesCurrentUser(final long userId,
                                                      final LocalDate startDate,
                                                      final LocalDate endDate,
                                                      final int offset,
                                                      final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            try {
                CouponUserDAO couponUserDAO = getDaoFactory()
                        .createCouponUserDAO(connectionManager);
                return couponUserDAO.getAllBetweenDatesCurrentUser(userId,
                        startDate, endDate, offset, limit);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getCountCouponNameCurrentUser(final long userId)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            try {
                CouponUserDAO couponUserDAO = getDaoFactory()
                        .createCouponUserDAO(connectionManager);
                return couponUserDAO.getCountCouponNameCurrentUser(userId);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getCountBetweenDatesCurrentUser(final long userId,
                                               final LocalDate startDate,
                                               final LocalDate endDate)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            try {
                CouponUserDAO couponUserDAO = getDaoFactory()
                        .createCouponUserDAO(connectionManager);
                return couponUserDAO.getCountBetweenDatesCurrentUser(userId,
                        startDate, endDate);
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
                CouponUserDAO couponUserDAO = getDaoFactory()
                        .createCouponUserDAO(connectionManager);
                return couponUserDAO.getAllCount();
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int create(final CouponUser newCouponUser) throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            try {
                CouponUserDAO couponUserDAO = getDaoFactory()
                        .createCouponUserDAO(connectionManager);
                int couponUserId = couponUserDAO.create(newCouponUser);
                newCouponUser.setId(couponUserId);
                connectionManager.commitChange();
                return couponUserId;
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public boolean update(final CouponUser newCouponUser)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager =
                     new ConnectionManager()) {
            try {
                CouponUserDAO couponUserDAO = getDaoFactory()
                        .createCouponUserDAO(connectionManager);
                boolean statement = couponUserDAO.update(newCouponUser);
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
