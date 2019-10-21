package by.training.final_task.action.staff;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Category;
import by.training.final_task.entity.Coupon;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CategoryService;
import by.training.final_task.service.interfaces.CouponService;
import by.training.final_task.service.parser.CouponFormParser;
import by.training.final_task.service.parser.InvalidFormDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddCouponAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final CouponFormParser parser = new CouponFormParser();
    private static final String MESSAGE_ATTRIBUTE = "message";

    public AddCouponAction() {
        this.allowedRoles.add(Role.STAFF);
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
                updateCategoryList(request);
                addCouponParametersToList(request, couponAddParameters);
                Coupon coupon;
                try {
                    coupon = parser.parse(this, couponAddParameters);
                } catch (InvalidFormDataException newE) {

                }
            }
        }
        //TODO !!!!!!
        return null;
    }

    private void addCouponParametersToList(final HttpServletRequest newServletRequest,
                                          final List<String> couponParameters) {
        couponParameters.add(newServletRequest.getParameter("couponName"));
        couponParameters.add(newServletRequest.getParameter("description"));
        couponParameters.add(newServletRequest.getParameter("price"));
        couponParameters.add(newServletRequest.getParameter("holdingAddress"));
        couponParameters.add(newServletRequest.getParameter("category"));
        couponParameters.add(newServletRequest.getParameter("companyProvider"));
    }

    private void updateCategoryList(final HttpServletRequest newServletRequest)
            throws ServiceException {
        CategoryService categoryService = (CategoryService) factory
                .createService(DAOEnum.CATEGORY);
        List<Category> categoryList = categoryService.getAll();
        newServletRequest.setAttribute("categoryList", categoryList);
    }
}
