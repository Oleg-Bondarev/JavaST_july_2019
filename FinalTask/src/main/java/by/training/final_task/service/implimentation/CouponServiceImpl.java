package by.training.final_task.service.implimentation;

import by.training.final_task.dao.interfases.AbstractConnectionManager;
import by.training.final_task.dao.interfases.CouponDAO;
import by.training.final_task.dao.interfases.DaoFactory;
import by.training.final_task.dao.sql.ConnectionManager;
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
import java.util.List;

public class CouponServiceImpl extends AbstractService implements CouponService {

    public CouponServiceImpl() {
        super();
    }

    public CouponServiceImpl(final DaoFactory newFactory) {
        super(newFactory);
    }

    @Override
    public List<Coupon> getAllByCompanyProvider(final String name,
                                                final int offset,
                                                final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CouponDAO couponDAO = getDaoFactory().createCouponDAO(connectionManager);
                return couponDAO.getAllByCompanyProvider(name, offset, limit);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public List<Coupon> getAllByCategory(final Category category,
                                         final int offset,
                                         final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CouponDAO couponDAO = getDaoFactory().createCouponDAO(connectionManager);
                return couponDAO.getAllByCategory(category, offset, limit);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public List<Coupon> getAllByName(final String name, final int offset,
                                     final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CouponDAO couponDAO = getDaoFactory().createCouponDAO(connectionManager);
                return couponDAO.getAllByName(name, offset, limit);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public List<Coupon> getAllByPriceRange(final BigDecimal minBorder,
                                           final BigDecimal maxBorder,
                                           final int offset, final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CouponDAO couponDAO = getDaoFactory().createCouponDAO(connectionManager);
                return couponDAO.getAllByPriceRange(minBorder, maxBorder,
                        offset, limit);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public List<Coupon> getAllAvailableCoupons(final int offset,
                                               final int limit)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CouponDAO couponDAO = getDaoFactory().createCouponDAO(connectionManager);
                return couponDAO.getAllAvailableCoupons(offset, limit);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getAmountOfAllCoupons() throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CouponDAO couponDAO = getDaoFactory().createCouponDAO(connectionManager);
                return couponDAO.getAmountOfAllCoupons();
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getAmountByCategory(final Category category)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CouponDAO couponDAO = getDaoFactory().createCouponDAO(connectionManager);
                return couponDAO.getAmountByCategory(category);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getAmountByCompanyProvider(final String companyName)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CouponDAO couponDAO = getDaoFactory().createCouponDAO(connectionManager);
                return couponDAO.getAmountByCompanyProvider(companyName);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getAmountByName(final String name) throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CouponDAO couponDAO = getDaoFactory().createCouponDAO(connectionManager);
                return couponDAO.getAmountByName(name);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int getAmountByPriceRange(final BigDecimal minBorder,
                                     final BigDecimal maxBorder)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CouponDAO couponDAO = getDaoFactory().createCouponDAO(connectionManager);
                return couponDAO.getAmountByPriceRange(minBorder, maxBorder);
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public int create(final Coupon coupon) throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CouponDAO couponDAO = getDaoFactory().createCouponDAO(connectionManager);
                int couponId = couponDAO.create(coupon);
                coupon.setId(couponId);
                connectionManager.commitChange();
                return couponId;
            } catch (PersistentException newE) {
                connectionManager.rollbackChange();
                throw new ServiceException(newE.getMessage(), newE);
            }
        } catch (PersistentException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public boolean update(final Coupon coupon) throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CouponDAO couponDAO = getDaoFactory().createCouponDAO(connectionManager);
                boolean statement = couponDAO.update(coupon);
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
    public boolean updateAvailableStatus(final long id)
            throws ServiceException {
        try (AbstractConnectionManager connectionManager = new ConnectionManager()) {
            try {
                CouponDAO couponDAO = getDaoFactory().createCouponDAO(connectionManager);
                boolean statement = couponDAO.updateAvailableStatus(id);
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
