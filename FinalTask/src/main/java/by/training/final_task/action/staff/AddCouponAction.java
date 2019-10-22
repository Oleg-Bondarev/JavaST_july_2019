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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddCouponAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final CouponFormParser parser = new CouponFormParser();

    public AddCouponAction() {
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
                List<String> couponAddParameters = new ArrayList<>();
                addCouponParametersToList(request, couponAddParameters);
                Coupon coupon;
                try {
                    coupon = parser.parse(this, couponAddParameters);
                    CouponService couponService = (CouponService) factory
                            .createService(DAOEnum.COUPON);
                    fillCategoryCompany(coupon, couponAddParameters);

                    coupon.setPathToPicture("img/coupon/default.jpg");
                    couponService.create(coupon);
                    return new Forward("/user/staff/addcouponpage.html",
                            true);
                } catch (InvalidFormDataException newE) {
                    request.setAttribute("message", newE.getMessage());
                    return null;
                }
            }
            LOGGER.log(Level.INFO, "{} - attempted to access {} and" +
                    " stopped due to not enough privileges",
                    request.getRemoteAddr(), request.getRequestURI());
        }
        throw new ServiceException("forbiddenAccess");
    }

    private void addCouponParametersToList(final HttpServletRequest newServletRequest,
                                          final List<String> couponParameters) {
        couponParameters.add(newServletRequest.getParameter("couponName"));
        couponParameters.add(newServletRequest.getParameter("couponDescription"));
        couponParameters.add(newServletRequest.getParameter("couponPrice"));
        couponParameters.add(newServletRequest.getParameter("holdingAddress"));
        couponParameters.add(newServletRequest.getParameter("category"));
        couponParameters.add(newServletRequest.getParameter("companyProvider"));
    }

    private void fillCategoryCompany(final Coupon newCoupon,
                                     final List<String> newParameters)
            throws ServiceException {
        final int categoryIndex = 4;
        final int companyIndex = 5;
        CategoryService categoryService = (CategoryService) factory
                .createService(DAOEnum.CATEGORY);
        CompanyProviderService companyService =
                (CompanyProviderService) factory.createService(
                        DAOEnum.COMPANYPROVIDER);
        int categoryID = Integer.parseInt(newParameters.get(categoryIndex));
        int companyID = Integer.parseInt(newParameters.get(companyIndex));
        Category category = categoryService.get(categoryID);
        CompanyProvider companyProvider = companyService.get(companyID);
        newCoupon.setCategory(category);
        newCoupon.setCompanyProvider(companyProvider);
    }
}
