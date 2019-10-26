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
        //long couponID = Long.parseLong(request.getParameter("couponID"));
        long couponID = 0;
        try {
            couponID = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException newE) {
            LOGGER.log(Level.ERROR, newE);
        }

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
