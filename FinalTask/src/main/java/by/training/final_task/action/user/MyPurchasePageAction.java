package by.training.final_task.action.user;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.action.PagePagination;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Coupon;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CouponUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MyPurchasePageAction extends AuthorizedUserAction {

    private static final int ROWCOUNT = 3;

    public MyPurchasePageAction() {
        allowedRoles.add(Role.USER);
    }

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if ((user != null) && allowedRoles.contains(user.getRole())) {
                CouponUserService couponUserService = (CouponUserService)
                        factory.createService(DAOEnum.COUPONUSER);
                List<Coupon> userPurchaseList = (List<Coupon>)
                        request.getAttribute("resultUserPurchases");
                if (userPurchaseList == null) {
                    PagePagination pagination = new PagePagination(
                            couponUserService.getCountCouponNameCurrentUser(
                                    user.getId()), ROWCOUNT,
                            request.getParameter("page"));
                    request.setAttribute("amountOfPages", pagination
                            .getPagesAmount());
                    userPurchaseList = couponUserService
                            .getAllCouponsCurrentUser(user.getId(), pagination
                                    .getPageOffset(), ROWCOUNT);
                    request.setAttribute("resultCoupons",
                            userPurchaseList);
                    request.setAttribute("paginationURL",
                            "/coupon/user/mypurchases.html");
                }
                return null;
            } else {
                return new Forward("/loginpage.html", true);
            }
        }
        throw new ServiceException("forbiddenAccess");
    }
}
