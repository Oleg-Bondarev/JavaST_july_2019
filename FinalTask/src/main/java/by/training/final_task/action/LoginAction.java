package by.training.final_task.action;

import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Represent login action.
 * */
public class LoginAction extends AuthorizedUserAction {
    /**
     * Class logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Name of the attribute.
     * */
    private static final String MESSAGE = "message";
    /**
     * Url for redirect.
     * */
    private static final String LOGIN_URL = "/loginpage.html";

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        if ((!login.isEmpty()) && (!password.isEmpty())) {
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
                    LOGGER.log(Level.INFO, "User {} unsuccessfully tried"
                                    + " to login from {} ({},{})", login,
                            request.getRemoteAddr(), request.getRemoteHost(),
                            request.getRemotePort());
                    return executeForward(LOGIN_URL, MESSAGE,
                            "couldNotFindLoginPassword");
                }
            } else {
                return executeForward(LOGIN_URL, MESSAGE,
                        "alreadyLoggedInUser");
            }
        }
        return executeForward(LOGIN_URL, MESSAGE, "fillAllFields");
    }
}
