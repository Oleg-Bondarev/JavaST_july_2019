package by.training.final_task.action;

import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Category;
import by.training.final_task.entity.CompanyProvider;
import by.training.final_task.entity.Coupon;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CategoryService;
import by.training.final_task.service.interfaces.CompanyProviderService;
import by.training.final_task.service.interfaces.CouponService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MoreInfoAction extends AuthorizedUserAction {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        String tempId = request.getParameter("id");
        if (!validator.validateIdParameter(tempId)) {
            LOGGER.log(Level.DEBUG, "Incorrect id parameter {}", tempId);
            return executeForward("/coupons.html?page=1",
                    "message", "incorrectIdParam");
        }
        long couponID = Long.parseLong(tempId);

        CouponService couponService = (CouponService)
                factory.createService(DAOEnum.COUPON);
        Coupon coupon = couponService.get(couponID);
        prepareCoupon(coupon);
        request.setAttribute("coupon", coupon);
        return null;
    }

    private void prepareCoupon(final Coupon newCoupon) throws ServiceException {
        CategoryService categoryService = (CategoryService) factory
                .createService(DAOEnum.CATEGORY);
        CompanyProviderService companyProviderService = (CompanyProviderService)
                factory.createService(DAOEnum.COMPANYPROVIDER);
        Category category = categoryService.get(newCoupon.getCategory()
                .getId());
        CompanyProvider company = companyProviderService.get(newCoupon
                .getCompanyProvider().getId());
        newCoupon.setCategory(category);
        newCoupon.setCompanyProvider(company);
    }
}
