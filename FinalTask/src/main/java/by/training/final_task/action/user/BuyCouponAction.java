package by.training.final_task.action.user;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Coupon;
import by.training.final_task.entity.CouponUser;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CouponService;
import by.training.final_task.service.interfaces.CouponUserService;
import by.training.final_task.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class BuyCouponAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String MESSAGE = "message";

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if (user != null) {
                long couponId = Long.parseLong(request
                        .getParameter("couponID"));
                makePurchase(couponId, user, request);
                return new Forward("/coupons.html?page=1");
            } else {
                return new Forward("/loginpage.html");
            }
        }
        return new Forward("/loginpage.html");
    }

    private void makePurchase(final long newCouponId, final User newUser,
                              final HttpServletRequest
                              newRequest)
            throws ServiceException {
        CouponService couponService = (CouponService) factory
                .createService(DAOEnum.COUPON);
        CouponUserService couponUserService = (CouponUserService) factory
                .createService(DAOEnum.COUPONUSER);
        UserService userService = (UserService) factory
                .createService(DAOEnum.USER);
        Coupon coupon = couponService.getIfAvailable(newCouponId);
        if (coupon == null) {
            newRequest.setAttribute("successMessage",
                            "couponIsAlreadyBought");
        } else {
            CouponUser couponUser = new CouponUser(0, LocalDate.now(),
                                                        coupon, newUser);
            long id = couponUserService.create(couponUser);
            couponUser.setId(id);
            couponService.updateAvailableStatus(coupon.getId());
        }
    }
}
