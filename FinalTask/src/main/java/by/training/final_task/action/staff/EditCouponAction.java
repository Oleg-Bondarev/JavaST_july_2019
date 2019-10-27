package by.training.final_task.action.staff;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.*;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CategoryService;
import by.training.final_task.service.interfaces.CompanyProviderService;
import by.training.final_task.service.interfaces.CouponService;
import by.training.final_task.service.parser.CouponFormParser;
import by.training.final_task.service.parser.InvalidFormDataException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class EditCouponAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();
    private static CouponFormParser couponParser = new CouponFormParser();
    private static final int CATEGORY_INDEX = 4;
    private static final int COMPANY_INDEX = 5;


    public EditCouponAction() {
        allowedRoles.add(Role.STAFF);
    }

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if ((user != null) && allowedRoles.contains(user.getRole())) {
                CouponService couponService = (CouponService)
                        factory.createService(DAOEnum.COUPON);
                List<String> couponParameters = new ArrayList<>();
                addCouponParameters(request, couponParameters);

                try {
                    Coupon changedCoupon = couponParser.parse(this,
                            couponParameters);
                    long couponId = Long.parseLong(request
                            .getParameter("couponID"));
                    Coupon oldCoupon = couponService.get(couponId);
                    changedCoupon.setId(couponId);
                    changedCoupon.setPathToPicture(oldCoupon
                            .getPathToPicture());
                    changedCoupon.setCouponAddDate(oldCoupon
                            .getCouponAddDate());
                    addNewCategory(changedCoupon, couponParameters);
                    addNewCompanyProvider(changedCoupon, couponParameters);
                    couponService.update(changedCoupon);

                    return new Forward("/coupons.html?page=1",
                            true);
                } catch (InvalidFormDataException newE) {
                    request.setAttribute("message", newE.getMessage());
                    return null;
                }
            } else {
                throw new ServiceException("forbiddenAccess");
            }
        }
        LOGGER.log(Level.INFO, "{} - attempted to access {} and failed",
                request.getRemoteAddr(), request.getRequestURI());
        throw new ServiceException("forbiddenAccess");
    }

    //fix list on map
    private void addCouponParameters(final HttpServletRequest request,
                                     final List<String> parameters) {
        parameters.add(request.getParameter("couponName"));
        parameters.add(request.getParameter("couponDescription"));
        parameters.add(request.getParameter("couponPrice"));
        parameters.add(request.getParameter("holdingAddress"));
        parameters.add(request.getParameter("category"));
        parameters.add(request.getParameter("companyProvider"));
    }

    private void addNewCategory(final Coupon newCoupon,
                                final List<String> params)
            throws ServiceException {
        CategoryService categoryService = (CategoryService)
                factory.createService(DAOEnum.CATEGORY);
        long categoryId = Long.parseLong(params.get(CATEGORY_INDEX));
        Category category = categoryService.get(categoryId);
        newCoupon.setCategory(category);
    }

    private void addNewCompanyProvider(final Coupon newCoupon,
                                       final List<String> params)
            throws ServiceException {
        CompanyProviderService companyService = (CompanyProviderService)
                factory.createService(DAOEnum.COMPANYPROVIDER);
        long companyId = Long.parseLong(params.get(COMPANY_INDEX));
        CompanyProvider company = companyService.get(companyId);
        newCoupon.setCompanyProvider(company);
    }
}
