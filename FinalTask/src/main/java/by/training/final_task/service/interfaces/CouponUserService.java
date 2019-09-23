package by.training.final_task.service.interfaces;

import by.training.final_task.entity.Coupon;
import by.training.final_task.entity.CouponUser;
import by.training.final_task.service.ServiceException;

import java.time.LocalDate;
import java.util.List;

public interface CouponUserService extends Service {
    List<Coupon> getAllCouponsCurrentUser(long userId, int offset, int limit)
            throws ServiceException;
    List<Coupon> getAllBetweenDatesCurrentUser(long userId, LocalDate startDate,
                                               LocalDate endDate, int offset,
                                               int limit)
            throws ServiceException;
    int getCountCouponNameCurrentUser(long userId)throws ServiceException;
    int getCountBetweenDatesCurrentUser(long userId, LocalDate startDate,
                                        LocalDate endDate)
            throws ServiceException;
    int getAllCount() throws ServiceException;
    int create(CouponUser newCouponUser) throws ServiceException;
    boolean update(CouponUser newCouponUser) throws ServiceException;
}
