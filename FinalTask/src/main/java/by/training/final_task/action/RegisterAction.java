package by.training.final_task.action;

import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.UserService;
import by.training.final_task.service.parser.InvalidFormDataException;
import by.training.final_task.service.parser.UserFormParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class RegisterAction extends Action {

    private static final UserFormParser userParser = new UserFormParser();

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        if ((session == null)
                || (session.getAttribute("authorizedUser") == null)) {
            List<String> userRegistrationParameter = new ArrayList<>();
            addUserParametersToList(request, userRegistrationParameter);
            User user;
            try {
                user = userParser.parse(this, userRegistrationParameter);
                user.setRole(Role.USER);
                //default avatar for new users
                user.setPathToAvatar("img/user/user_profile.jpg");
                UserService userService = (UserService)
                        factory.createService(DAOEnum.USER);
                try {
                    int generatedUserId = userService.create(user);
                    user.setId(generatedUserId);
                } catch (ServiceException newE) {
                    return executeForward("/registration.html",
                            "message", newE.getMessage());
                }
            } catch (InvalidFormDataException newE) {
                return executeForward("/registration.html",
                        "message", newE.getMessage());
            }
            return new Forward("/loginpage.html");
        } else {
            return executeForward("/loginpage.html",
                    "message", "alreadyLoggedIn");
        }
    }

    public static void addUserParametersToList(HttpServletRequest request, List<String> parametersList) {
        parametersList.add(request.getParameter("login"));
        parametersList.add(request.getParameter("password"));
        parametersList.add(request.getParameter("email"));
        parametersList.add(request.getParameter("firstName"));
        parametersList.add(request.getParameter("secondName"));
        parametersList.add(request.getParameter("mobilePhone"));
    }
}
