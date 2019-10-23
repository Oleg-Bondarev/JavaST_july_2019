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
import javax.servlet.http.HttpSession;
import java.util.List;

public class CouponAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();
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
            request.setAttribute("paginationURL",
                    "/coupons.html");
        }
        return null;
    }
}
