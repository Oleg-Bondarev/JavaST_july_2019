package by.training.final_task.dao.interfases;

import by.training.final_task.entity.Coupon;
import by.training.final_task.entity.CouponUser;
import by.training.final_task.dao.PersistentException;

import java.time.LocalDate;
import java.util.List;

public interface CouponUserDAO extends DAO<CouponUser> {
    List<Coupon> getAllCouponsCurrentUser(long userId, int offset, int limit)
        throws PersistentException;
    List<Coupon> getAllBetweenDatesCurrentUser(long userId, LocalDate startDate,
        LocalDate endDate, int offset, int limit) throws PersistentException;
    int getCountCouponNameCurrentUser(long userId)throws PersistentException;
    int getCountBetweenDatesCurrentUser(long userId, LocalDate startDate,
        LocalDate endDate) throws PersistentException;
    int getAllCount() throws PersistentException;
}
