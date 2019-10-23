package by.training.final_task.action.staff;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Category;
import by.training.final_task.entity.CompanyProvider;
import by.training.final_task.entity.Coupon;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CategoryService;
import by.training.final_task.service.interfaces.CompanyProviderService;
import by.training.final_task.service.interfaces.CouponService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class EditCouponPageAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if (user != null) {
                CouponService couponService = (CouponService)
                        factory.createService(DAOEnum.COUPON);
                CategoryService categoryService = (CategoryService) factory
                        .createService(DAOEnum.CATEGORY);
                CompanyProviderService companyProviderService = (CompanyProviderService)
                        factory.createService(DAOEnum.COMPANYPROVIDER);
                long couponID = Long.parseLong(request.getParameter("couponID"));
                List<Category> categoryList = categoryService.getAll();
                List<CompanyProvider> companyList =
                        companyProviderService.getAvailableCompanyList();
                Coupon coupon = couponService.get(couponID);
                prepareOldCouponsParameters(coupon, categoryService,
                        companyProviderService);

                request.setAttribute("oldCoupon", coupon);
                request.setAttribute("categoryList", categoryList);
                request.setAttribute("companyList", companyList);
                return null;
            } else {
                throw new ServiceException("forbiddenAccess");
            }
        }
        LOGGER.log(Level.INFO, "{} - attempted to access {} and failed",
                request.getRemoteAddr(), request.getRequestURI());
        throw new ServiceException("forbiddenAccess");
    }

    private void prepareOldCouponsParameters(final Coupon newCoupon,
                         final CategoryService newCategoryService,
                         final CompanyProviderService newCompanyProviderService)
            throws ServiceException {
        Category category = newCategoryService.get(newCoupon.getCategory()
                .getId());
        CompanyProvider company = newCompanyProviderService.get(newCoupon
                .getCompanyProvider().getId());
        newCoupon.setCategory(category);
        newCoupon.setCompanyProvider(company);
    }
}
