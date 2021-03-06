package by.training.final_task.action.admin;

import by.training.final_task.action.AuthorizedUserAction;
import by.training.final_task.action.PagePagination;
import by.training.final_task.dao.sql.DAOEnum;
import by.training.final_task.entity.Role;
import by.training.final_task.entity.User;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.interfaces.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

//by first and/or second name
public class FindUserByNameAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String NAME_PARAM = "firstNameParameter";
    private static final String SURNAME_PARAM = "secondNameParameter";
    private static final int ROWS_IN_PAGE = 5;

    public FindUserByNameAction() {
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
                String[] params = getNameAndSurnameForSearch(request);
                if (!params[0].isEmpty()) {
                    session.setAttribute(NAME_PARAM, params[0]);
                    session.setAttribute(SURNAME_PARAM, params[1]);
                    UserService userService = (UserService)
                            factory.createService(DAOEnum.USER);
                    PagePagination pagination;
                    if (!params[1].isEmpty()) {
                        pagination = new PagePagination(userService
                                .getAmountOfAllUsersByFirstAndSecondName(
                                        params[0], params[1], Role.USER),
                                ROWS_IN_PAGE,
                                request.getParameter("page"));
                    } else {
                        pagination = new PagePagination(userService
                                .getAmountOfAllUsersByFirstNameAndRole(
                                        params[0], Role.USER), ROWS_IN_PAGE,
                                request.getParameter("page"));
                    }
                    Forward forward = new Forward(
                            "/user/admin/findusers.html");
                    forward.getAttributes().put("amountOfPages",
                            pagination.getPagesAmount());
                    List<User> userList;
                    if (!params[1].isEmpty()) {
                        userList = userService.getAllUsersByFirstAndSecondName(
                                params[0], params[1], Role.USER);
                    } else {
                        userList = userService.getAllUsersByRoleAndName(
                                params[0], Role.USER, pagination.getPageOffset(),
                                ROWS_IN_PAGE);
                    }
                    forward.getAttributes().put("resultUsers", userList);
                    return forward;
                }
                return new Forward("/user/admin/findusers.html");
            }
        }
        LOGGER.log(Level.INFO, "{} - attempted to access {} anf failed",
                request.getRemoteAddr(), request.getRequestURI());
        throw new ServiceException("forbiddenAccess");
    }

    private String[] getNameAndSurnameForSearch(final HttpServletRequest request) {
        String name = request.getParameter(NAME_PARAM);
        String surname = request.getParameter(SURNAME_PARAM);
        if (surname == null) {
            HttpSession session = request.getSession(false);
            surname = (String) session.getAttribute(SURNAME_PARAM);
        }
        String[] array = {name, surname};
        return array;
    }
}
