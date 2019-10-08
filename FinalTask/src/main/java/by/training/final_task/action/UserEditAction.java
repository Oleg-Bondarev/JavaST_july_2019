package by.training.final_task.action;

import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.UserService;
import by.training.final_task.service.parser.UserFormParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class UserEditAction extends AuthorizedUserAction {

    private static final Logger LOGGER = LogManager.getLogger();
    private static UserFormParser userParser = new UserFormParser();
    private static final int PASSWORD_INDEX = 1;

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if (user != null) {
                UserService userService = (UserService) factory.createService(
                        DAOEnum.USER);
                request.setAttribute("oldUserInformation", user);
                List<String> userParameters = new ArrayList<>();
                addUserParameters(request, userParameters);
                if ((userParameters.get(PASSWORD_INDEX) == null)
                        || (userParameters.get(PASSWORD_INDEX).isEmpty())) {
                    request.setAttribute("message", "inputPasswordToSubmit");
                    return null;
                }

                try {
                    User changeUser = userParser.parse(this, userParameters);
                    changeUser.setId(user.getId());
                    changeUser.setRole(user.getRole());
                    userService.update(changeUser);
                    session.setAttribute("authorizedUser", changeUser);
                    return new Forward("/user/profile.html", true);
                } catch (ServiceException newE) {
                    request.setAttribute("message", newE.getMessage());
                    return null;
                }
            } else {
                throw new ServiceException("forbiddenAccess");
            }
        }
        LOGGER.log(Level.INFO, "{} - attempted to access {} and failed",
                request.getRemoteAddr(), request.getRequestURI());
        throw new ServiceException("forbiddenAccess");
    }

    //TODO what about path for user avatar?
    private void addUserParameters(final HttpServletRequest request,
                                   final List<String> parameters) {
        parameters.add(request.getParameter("login"));
        parameters.add(request.getParameter("password"));
        parameters.add(request.getParameter("email"));
        parameters.add(request.getParameter("firstName"));
        parameters.add(request.getParameter("secondName"));
        parameters.add(request.getParameter("mobilePhone"));
    }
}
