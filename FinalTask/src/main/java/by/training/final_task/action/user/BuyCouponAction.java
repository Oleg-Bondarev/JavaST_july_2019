package by.training.final_task.action.user;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

            } else {
                return new Forward("/loginpage.html");
            }
        }
        return new Forward("/loginpage.html");
    }
}
