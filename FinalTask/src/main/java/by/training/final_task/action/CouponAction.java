package by.training.final_task.action;

import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Category;
import by.training.final_task.entity.Coupon;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CategoryService;
import by.training.final_task.service.interfaces.CouponService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Represent the transition to the coupons page and the preparation
 * of parameters for  output to tha page.
 * */
public class CouponAction extends AuthorizedUserAction {
    /**
     * Count objects in the page for pagination.
     * */
    private static final int ROWCOUNT = 10;

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        CouponService couponService = (CouponService) factory.createService(
                DAOEnum.COUPON);
        CategoryService categoryService = (CategoryService) factory
                .createService(DAOEnum.CATEGORY);
        List<Category> categoryList = categoryService.getAll();
        request.setAttribute("categoryList", categoryList);
        List<Coupon> couponsList = (List<Coupon>)
                request.getAttribute("resultCoupons");
        if (couponsList == null) {
            PagePagination pagination = new PagePagination(
                    couponService.getAmountOfAllCoupons(), ROWCOUNT,
                    request.getParameter("page"));
            request.setAttribute("amountOfPages", pagination
                    .getPagesAmount());
            couponsList = couponService.getAllAvailableCoupons(
                    pagination.getPageOffset(), ROWCOUNT);
            request.setAttribute("resultCoupons", couponsList);
            request.setAttribute("paginationURL", "/coupons.html");
        }
        return null;
    }
}
