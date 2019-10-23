package by.training.final_task.service.interfaces;

import by.training.final_task.entity.Category;
import by.training.final_task.entity.Coupon;
import by.training.final_task.service.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface CouponService extends Service {
    Coupon get(long id) throws ServiceException;
    List<Coupon> getAllByCompanyProvider(String name, int offset, int limit)
            throws ServiceException;
    List<Coupon> getAllByCategory(Category category, int offset, int limit)
            throws ServiceException;
    List<Coupon> getAllByName(String name, int offset, int limit)
            throws ServiceException;
    List<Coupon> getAllByPriceRange(BigDecimal minBorder, BigDecimal maxBorder,
                                    int offset, int limit)
            throws ServiceException;
    List<Coupon> getAllAvailableCoupons(int offset, int limit)
            throws ServiceException;
    int getAmountOfAllCoupons() throws ServiceException;
    int getAmountByCategory(Category category) throws ServiceException;
    int getAmountByCompanyProvider(String companyName)
            throws ServiceException;
    int getAmountByName(String name) throws ServiceException;
    int getAmountByPriceRange(BigDecimal minBorder, BigDecimal maxBorder)
            throws ServiceException;
    int create(Coupon coupon) throws ServiceException;
    boolean update(Coupon coupon) throws ServiceException;
    boolean updateAvailableStatus(long id) throws ServiceException;
}
