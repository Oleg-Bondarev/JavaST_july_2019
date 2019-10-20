package by.training.final_task.action.admin;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Role;
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
import java.util.ResourceBundle;

public class AddStaffAction extends AuthorizedUserAction {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final UserFormParser userFormParser = new UserFormParser();

    public AddStaffAction() {
        allowedRoles.add(Role.ADMIN);
    }

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user1 = (User) session.getAttribute("authorizedUser");
            if ((user1 != null) && allowedRoles.contains(user1.getRole())) {
                List<String> userParameters = new ArrayList<>();
                addUserParameters(request, userParameters);
                try {
                    User user = userFormParser.parse(this,
                            userParameters);
                    user.setRole(Role.STAFF);
                    user.setPathToAvatar("img/staff/user5.jpg");
                    UserService userService = (UserService) factory
                            .createService(DAOEnum.USER);
                    int userIdGenerated = userService.create(user);
                    user.setId(userIdGenerated);
                    Forward forward = new Forward("/user/admin/addstaffpage.html", true);
                    ResourceBundle rb = ResourceBundle.getBundle("local");
                    forward.getAttributes().put("successMessage", rb);
                    return forward;
                } catch (ServiceException newE) {
                    request.setAttribute("message", newE.getMessage());
                    return null;
                }
            }
            return new Forward("/login.html", true);
        }
        LOGGER.log(Level.INFO, "{} - attempted to access {} and failed",
                request.getRemoteAddr(), request.getRequestURI());
        return new Forward("/login.html", true);
    }

    private void addUserParameters(final HttpServletRequest request,
                                   final List<String> userParameters) {
        userParameters.add(request.getParameter("login"));
        userParameters.add(request.getParameter("password"));
        userParameters.add(request.getParameter("email"));
        userParameters.add(request.getParameter("firstName"));
        userParameters.add(request.getParameter("secondName"));
        userParameters.add(request.getParameter("mobilePhone"));
    }
}
