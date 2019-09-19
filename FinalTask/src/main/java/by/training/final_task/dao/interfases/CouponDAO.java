package by.training.final_task.dao.interfases;

import by.training.final_task.entity.Category;
import by.training.final_task.entity.Coupon;
import by.training.final_task.exception.PersistentException;

import java.math.BigDecimal;
import java.util.List;

public interface CouponDAO extends DAO<Coupon> {
    //нужно ли имя компании or id?
    List<Coupon> getAllByCompanyProvider(String name, int offset, int limit)
            throws PersistentException;
    List<Coupon> getAllByCategory(Category category, int offset, int limit)
            throws PersistentException;
    List<Coupon> getAllByName(String name, int offset, int limit)
            throws PersistentException;
    List<Coupon> getAllByPriceRange(BigDecimal minBorder, BigDecimal maxBorder,
                            int offset, int limit) throws PersistentException;
    List<Coupon> getAllAvailableCoupons(int offset, int limit)
            throws PersistentException;
    int getAmountOfAllCoupons() throws PersistentException;
    //int categoryId??
    int getAmountByCategory(Category category) throws PersistentException;
    //String compAddr?
    int getAmountByCompanyProvider(String companyName)
            throws PersistentException;
    int getAmountByName(String name) throws PersistentException;
    int getAmountByPriceRange(BigDecimal minBorder, BigDecimal maxBorder)
            throws PersistentException;
}
