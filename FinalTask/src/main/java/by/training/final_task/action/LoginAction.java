package by.training.final_task.action;

import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.ServiceFactory;
import by.training.final_task.service.interfaces.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String MESSAGE = "message";

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        if ((login != null) && (password != null)) {
            if (session.getAttribute("authorizedUser") == null) {
                UserService service = (UserService) factory
                        .createService(DAOEnum.USER);
                User user = service.get(login, password);
                if (user != null) {
                    session.setAttribute("authorizedUser", user);
                    session.setAttribute("userName", user.getLogin());
                    LOGGER.log(Level.INFO, "User {} is logged in from {}"
                            + " ({}:{})", login, request.getRemoteAddr(),
                            request.getRemoteHost(), request.getRemotePort());
                    request.setAttribute(MESSAGE, "loggedInSuccessfully");

                    return new Forward("/user/profile.html");
                } else {
                    request.setAttribute(MESSAGE,
                            "couldNotFindLoginPassword");
                    LOGGER.log(Level.INFO, "User {} unsuccessfully tried" +
                            " to login from {} ({},{})", login,
                            request.getRemoteAddr(), request.getRemoteHost(),
                            request.getRemotePort());
                }
            } else {
                request.setAttribute(MESSAGE, "alreadyLoggedInUser");
            }
        }
        request.setAttribute(MESSAGE,"fillAllFields");
        return null;
    }
}
