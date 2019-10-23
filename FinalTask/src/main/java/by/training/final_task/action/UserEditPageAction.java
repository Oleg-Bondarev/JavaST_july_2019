package by.training.final_task.action;

import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserEditPageAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if (user != null) {
                request.setAttribute("oldUserInformation", user);
                return null;
            } else {
                throw new ServiceException("forbiddenAccess");
            }
        }
        LOGGER.log(Level.INFO, "{} - attempted to access {} and failed",
                request.getRemoteAddr(), request.getRequestURI());
        throw new ServiceException("forbiddenAccess");
    }
}
