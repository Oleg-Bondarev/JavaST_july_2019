package by.training.final_task.dao.interfases;

import by.training.final_task.entity.CouponUser;
import by.training.final_task.exception.PersistentException;

import java.util.Date;
import java.util.List;

public interface CouponUserDAO extends DAO<CouponUser> {
    CouponUser getById(long id) throws PersistentException;
    //???
    List<CouponUser> getAll(int offset, int limit) throws PersistentException;
    List<CouponUser> getAllCouponsCurrentUser(long userId, int offset, int limit)
        throws PersistentException;
    List<CouponUser> getAllBetweenDatesCurrentUser(long userId, Date startDate,
                                          Date endDate, int offset, int limit)
            throws PersistentException;
    int getCountCouponNameCurrentUser(long userId, int offset, int limit)
            throws PersistentException;
    int getCountBetweenDatesCurrentUser(long userId, Date startDate,
                                    Date endDate) throws PersistentException;
    int getAllCount() throws PersistentException;
    boolean delete(long id) throws PersistentException;
}
