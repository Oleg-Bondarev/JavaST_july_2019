package by.training.final_task.action.admin;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.action.PagePagination;
import by.training.final_task.controller.ControllerException;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindStaffAction extends AuthorizedUserAction {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int ROWCOUNT = 5;

    public FindStaffAction() {
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
                UserService userService = (UserService) factory.createService(DAOEnum.USER);
                List<User> userList = (List<User>) request.getAttribute("resultUsers");
                if (userList == null) {
                    PagePagination pagination = new PagePagination(userService
                            .getAmountOfAllUsersByRole(Role.STAFF), ROWCOUNT,
                            request.getParameter("page"));
                    request.setAttribute("amountOfPages", pagination.getPagesAmount());
                    userList = userService.getAllUsersByRole(Role.STAFF,
                            pagination.getPageOffset(), ROWCOUNT);
                    request.setAttribute("resultUsers", userList);
                    request.setAttribute("paginationURL", "/user/admin/findstaff.html");
                }
                return null;
            } else {
                return new Forward("/login.html", true);
            }
        }
        throw  new ServiceException("forbiddenAccess");
    }
}
