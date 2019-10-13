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

public class AddAdminAction extends AuthorizedUserAction {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final UserFormParser userFormParser = new UserFormParser();

    public AddAdminAction() {
        allowedRoles.add(Role.ADMIN);
    }

    @Override
    public Forward executeRequest(final HttpServletRequest request,
                                  final HttpServletResponse response)
            throws ServiceException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if ((user != null) && allowedRoles.contains(user.getRole())) {
                List<String> userParameters = new ArrayList<>();
                addUserParameters(request, userParameters);
                try {
                    User newAdmin = userFormParser.parse(this,
                            userParameters);
                    newAdmin.setRole(Role.ADMIN);
                    UserService userService = (UserService) factory
                            .createService(DAOEnum.USER);
                    int userIdGenerated = userService.create(newAdmin);
                    newAdmin.setId(userIdGenerated);
                    Forward forward = new Forward("/user/admin/addadmin.html", true);
                    forward.getAttributes().put("successMessage", "adminAdded");
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
