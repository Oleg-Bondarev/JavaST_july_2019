package by.training.final_task.service.implimentation;

import by.training.final_task.dao.interfases.CouponUserDAO;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Coupon;
import by.training.final_task.entity.CouponUser;
import by.training.final_task.dao.PersistentException;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CouponUserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CouponUserImpl extends ServiceImpl implements CouponUserService {

    private static final Logger LOGGER = LogManager.getLogger();
    private CouponUserDAO couponUserDAO;

    public CouponUserImpl(Connection newConnection) {
        super(newConnection);
    }

    @Override
    public List<Coupon> getAllCouponsCurrentUser(final long userId,
                                                 final int offset,
                                                 final int limit)
            throws ServiceException {
        try {
            prepareCouponUserDao(DAOEnum.COUPONUSER);
            List<Coupon> couponList = couponUserDAO.getAllCouponsCurrentUser(
                    userId, offset, limit);
            commitAndChangeAutoCommit();
            return couponList;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<Coupon> getAllBetweenDatesCurrentUser(final long userId,
                          final LocalDate startDate, final LocalDate endDate,
                          final int offset,final int limit)
            throws ServiceException {
        try {
            prepareCouponUserDao(DAOEnum.COUPONUSER);
            List<Coupon> couponList = couponUserDAO
                    .getAllBetweenDatesCurrentUser(userId, startDate, endDate,
                            offset, limit);
            commitAndChangeAutoCommit();
            return couponList;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getCountCouponNameCurrentUser(final long userId)
            throws ServiceException {
        try {
            prepareCouponUserDao(DAOEnum.COUPONUSER);
            int amount = couponUserDAO.getCountCouponNameCurrentUser(userId);
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getCountBetweenDatesCurrentUser(final long userId,
                             final LocalDate startDate, final LocalDate endDate)
            throws ServiceException {
        try {
            prepareCouponUserDao(DAOEnum.COUPONUSER);
            int amount = couponUserDAO.getCountBetweenDatesCurrentUser(userId,
                    startDate, endDate);
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAllCount() throws ServiceException {
        try {
            prepareCouponUserDao(DAOEnum.COUPONUSER);
            int amount = couponUserDAO.getAllCount();
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int create(final CouponUser newCouponUser) throws ServiceException {
        try {
            prepareCouponUserDao(DAOEnum.COUPONUSER);
            int index = couponUserDAO.create(newCouponUser);
            commitAndChangeAutoCommit();
            return index;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public boolean update(final CouponUser newCouponUser)
            throws ServiceException {
        try {
            prepareCouponUserDao(DAOEnum.COUPONUSER);
            boolean statement = couponUserDAO.update(newCouponUser);
            commitAndChangeAutoCommit();
            return statement;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    private void prepareCouponUserDao(final DAOEnum classType)
            throws ServiceException {
        try {
            connection.setAutoCommit(false);
            couponUserDAO = (CouponUserDAO) createDAO(classType);
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
