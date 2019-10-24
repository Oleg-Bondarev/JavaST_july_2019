package by.training.final_task.action.staff;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Coupon;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.CouponService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CouponBlockingAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();

    public CouponBlockingAction() {
        allowedRoles.add(Role.STAFF);
    }

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if ((user != null) && this.getAllowedRoles()
                    .contains(user.getRole())) {
                long couponId = Long.parseLong(request
                        .getParameter("couponID"));
                CouponService couponService = (CouponService) factory
                        .createService(DAOEnum.COUPON);
                couponService.updateAvailableStatus(couponId);
                session.setAttribute("message", "couponBlocked");
                return new Forward("/coupons.html?page=1");
            } else {
                throw new ServiceException("forbiddenAccess");
            }
        }
        LOGGER.log(Level.INFO, "{} attempted to access {} and failed",
                request.getRemoteAddr(), request.getRequestURI());
        throw new ServiceException("forbiddenAccess");
    }
}
