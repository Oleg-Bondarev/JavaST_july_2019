package by.training.final_task.action;

import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Category;
import by.training.final_task.entity.Coupon;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CategoryService;
import by.training.final_task.service.interfaces.CouponService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Represent search coupons by category action.
 * */
public class FindCouponByCategoryAction extends AuthorizedUserAction {
    /**
     * Class logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Count objects in the page for pagination.
     * */
    private static final int ROWCOUNT = 10;

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        long categoryId = Long.parseLong(request.getParameter(
                "category"));
        CategoryService categoryService = (CategoryService) factory
                .createService(DAOEnum.CATEGORY);
        CouponService couponService = (CouponService) factory
                .createService(DAOEnum.COUPON);
        Category category = categoryService.get(categoryId);
        PagePagination pagination = new PagePagination(
                couponService.getAmountByCategory(category), ROWCOUNT,
                request.getParameter("page"));
        List<Coupon> resultList = couponService.getAllByCategory(category,
                pagination.getPageOffset(), ROWCOUNT);
        Forward forward = new Forward("/coupons.html");
        forward.getAttributes().put("resultCoupons", resultList);
        forward.getAttributes().put("amountOfPages", pagination
                .getPagesAmount());
        forward.getAttributes().put("paginationURL", "/coupons.html");
        return forward;
    }
}
