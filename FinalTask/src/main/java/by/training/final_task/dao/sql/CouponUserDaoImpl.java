package by.training.final_task.dao.sql;

import by.training.final_task.dao.interfases.CouponUserDAO;
import by.training.final_task.entity.CouponUser;
import by.training.final_task.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class CouponUserDaoImpl extends BaseDaoImpl implements CouponUserDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    public CouponUserDaoImpl(final Connection newConnection) {
        super(newConnection);
    }

    @Override
    public List<CouponUser> getAllCouponsCurrentUser(final long userId,
                                                     final int offset,
                                                     final int limit)
            throws PersistentException {

    }

    @Override
    public List<CouponUser> getAllBetweenDatesCurrentUser(long userId, Date startDate, Date endDate, int offset, int limit) throws PersistentException {
        return null;
    }

    @Override
    public int getCountCouponNameCurrentUser(long userId, int offset, int limit) throws PersistentException {
        return 0;
    }

    @Override
    public int getCountBetweenDatesCurrentUser(long userId, Date startDate, Date endDate) throws PersistentException {
        return 0;
    }

    @Override
    public int getAllCount() throws PersistentException {
        return 0;
    }

    @Override
    public int create(CouponUser element) throws PersistentException {
        return 0;
    }

    @Override
    public CouponUser get() throws PersistentException {
        return null;
    }

    @Override
    public CouponUser get(long id) throws PersistentException {
        return null;
    }

    @Override
    public List<CouponUser> getAll(int offset, int limit) throws PersistentException {
        return null;
    }

    @Override
    public boolean update(CouponUser element) throws PersistentException {
        return false;
    }

    @Override
    public boolean delete(CouponUser element) throws PersistentException {
        return false;
    }

    @Override
    public boolean delete(long id) throws PersistentException {
        return false;
    }
}
