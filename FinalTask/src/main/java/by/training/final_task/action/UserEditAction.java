package by.training.final_task.action;

import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.UserService;
import by.training.final_task.service.parser.InvalidFormDataException;
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
                List<String> userParameters = new ArrayList<>();
                addUserParameters(request, userParameters);
                if ((userParameters.get(PASSWORD_INDEX) == null)
                        || (userParameters.get(PASSWORD_INDEX).isEmpty())) {
                    return executeForward("/user/usereditpage.html",
                            "message", "inputPasswordToSubmit");
                }

                try {
                    String oldLogin = request.getParameter("oldLogin");
                    User oldUser = userService.getByLogin(oldLogin);

                    User changeUser = userParser.parse(this,
                            userParameters);
                    changeUser.setId(user.getId());
                    changeUser.setRole(user.getRole());
                    changeUser.setPathToAvatar(user.getPathToAvatar());
                    userService.update(changeUser, oldUser);
                    session.setAttribute("authorizedUser", changeUser);
                    return new Forward("/user/profile.html", true);
                } catch (InvalidFormDataException | ServiceException newE) {
                    return executeForward("/user/usereditpage.html",
                            "message", newE.getMessage());
                }
            } else {
                throw new ServiceException("forbiddenAccess");
            }
        }
        LOGGER.log(Level.INFO, "{} - attempted to access {} and failed",
                request.getRemoteAddr(), request.getRequestURI());
        throw new ServiceException("forbiddenAccess");
    }

    private void addUserParameters(final HttpServletRequest request,
                                   final List<String> parameters) {
        parameters.add(request.getParameter("login"));
        parameters.add(request.getParameter("password"));
        parameters.add(request.getParameter("email"));
        parameters.add(request.getParameter("firstName"));
        parameters.add(request.getParameter("secondName"));
        //check phone
        String mobilePhone = readCorrectPhone(request
                .getParameter("mobilePhone"));
        parameters.add(mobilePhone);
    }

    private String readCorrectPhone(final String phone) {
        String code = "+375";
        if (phone.contains(code)) {
            int index = phone.indexOf(code) + code.length();
            code = phone.substring(index);
        }
        return code;
    }
}
