package by.training.final_task.service.implimentation;

import by.training.final_task.dao.interfases.CouponDAO;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Category;
import by.training.final_task.entity.Coupon;
import by.training.final_task.dao.PersistentException;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CouponService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CouponServiceImpl extends ServiceImpl implements CouponService {

    private static final Logger LOGGER = LogManager.getLogger();
    private CouponDAO couponDAO;

    public CouponServiceImpl(Connection newConnection) {
        super(newConnection);
    }

    @Override
    public List<Coupon> getAllByCompanyProvider(final String name,
                                                final int offset,
                                                final int limit)
            throws ServiceException {
        try {
            prepareCouponDao(DAOEnum.COUPON);
            List<Coupon> couponList = couponDAO.getAllByCompanyProvider(name,
                                                                offset, limit);
            commitAndChangeAutoCommit();
            return couponList;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<Coupon> getAllByCategory(final Category category,
                                         final int offset,
                                         final int limit)
            throws ServiceException {
        try {
            prepareCouponDao(DAOEnum.COUPON);
            List<Coupon> couponList = couponDAO.getAllByCategory(category,
                                                                offset, limit);
            commitAndChangeAutoCommit();
            return couponList;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<Coupon> getAllByName(final String name, final int offset,
                                     final int limit)
            throws ServiceException {
        try {
            prepareCouponDao(DAOEnum.COUPON);
            List<Coupon> couponList = couponDAO.getAllByName(name, offset,
                                                            limit);
            commitAndChangeAutoCommit();
            return couponList;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<Coupon> getAllByPriceRange(final BigDecimal minBorder,
                                           final BigDecimal maxBorder,
                                           final int offset, final int limit)
            throws ServiceException {
        try {
            prepareCouponDao(DAOEnum.COUPON);
            List<Coupon> couponList = couponDAO.getAllByPriceRange(minBorder,
                                            maxBorder, offset, limit);
            commitAndChangeAutoCommit();
            return couponList;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public List<Coupon> getAllAvailableCoupons(final int offset,
                                               final int limit)
            throws ServiceException {
        try {
            prepareCouponDao(DAOEnum.COUPON);
            List<Coupon> couponList = couponDAO.getAllAvailableCoupons(offset,
                                                                        limit);
            commitAndChangeAutoCommit();
            return couponList;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAmountOfAllCoupons() throws ServiceException {
        try {
            prepareCouponDao(DAOEnum.COUPON);
            int amount = couponDAO.getAmountOfAllCoupons();
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAmountByCategory(final Category category)
            throws ServiceException {
        try {
            prepareCouponDao(DAOEnum.COUPON);
            int amount = couponDAO.getAmountByCategory(category);
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAmountByCompanyProvider(final String companyName)
            throws ServiceException {
        try {
            prepareCouponDao(DAOEnum.COUPON);
            int amount = couponDAO.getAmountByCompanyProvider(companyName);
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAmountByName(final String name) throws ServiceException {
        try {
            prepareCouponDao(DAOEnum.COUPON);
            int amount = couponDAO.getAmountByName(name);
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int getAmountByPriceRange(final BigDecimal minBorder,
                                     final BigDecimal maxBorder)
            throws ServiceException {
        try {
            prepareCouponDao(DAOEnum.COUPON);
            int amount = couponDAO.getAmountByPriceRange(minBorder, maxBorder);
            commitAndChangeAutoCommit();
            return amount;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public int create(final Coupon coupon) throws ServiceException {
        try {
            prepareCouponDao(DAOEnum.COUPON);
            int index = couponDAO.create(coupon);
            commitAndChangeAutoCommit();
            return index;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public boolean update(final Coupon coupon) throws ServiceException {
        try {
            prepareCouponDao(DAOEnum.COUPON);
            boolean state = couponDAO.update(coupon);
            commitAndChangeAutoCommit();
            return state;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    @Override
    public boolean updateAvailableStatus(final long id)
            throws ServiceException {
        try {
            prepareCouponDao(DAOEnum.COUPON);
            boolean state = couponDAO.updateAvailableStatus(id);
            commitAndChangeAutoCommit();
            return state;
        } catch (PersistentException newE) {
            rollbackTransaction();
            throw new ServiceException(newE.getMessage(), newE);
        }
    }

    private void prepareCouponDao(final DAOEnum classType)
            throws ServiceException {
        try {
            connection.setAutoCommit(false);
            couponDAO = (CouponDAO) createDAO(classType);
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
