package by.training.final_task.action;

import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if (user != null) {
                LOGGER.log(Level.INFO, "{} has logged out", user.getLogin());
                session.invalidate();
                request.setAttribute("message", "loggedOutSuccessfully");
                return new Forward("/login.html");
            } else {
                request.setAttribute("message", "errorInLogout");
                return new Forward("/login.html");
            }
        }
        return new Forward("/login.html");
    }
}
