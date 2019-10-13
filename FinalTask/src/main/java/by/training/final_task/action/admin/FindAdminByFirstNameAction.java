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

public class FindAdminByFirstNameAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String NAME_PARAM = "firstnameParameter";
    private static final int ROWS_IN_PAGE = 5;

    public FindAdminByFirstNameAction() {
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
                String nameForSearch = getNameForSearch(request);
                session.setAttribute(NAME_PARAM, nameForSearch);
                UserService userService = (UserService)
                        factory.createService(DAOEnum.USER);
                //"%"+NAME_PARAM+"%?
                PagePagination pagination = new PagePagination(userService
                        .getAmountOfAllUsersByFirstNameAndRole(nameForSearch, Role.ADMIN),
                        ROWS_IN_PAGE, request.getParameter("page"));
                Forward forward = new Forward("user/admin/findadmin.html&page=1");
                forward.getAttributes().put("amountOfPages", pagination.getPagesAmount());
                List<User> userList = userService.getAllUsersByRoleAndName(nameForSearch,
                        Role.ADMIN, pagination.getPageOffset(), ROWS_IN_PAGE);
                forward.getAttributes().put("resultUsers", userList);
                forward.getAttributes().put("paginationURL", "/user/admin/findadminbyfirsname.html");
                return forward;
            }
        }
        LOGGER.log(Level.INFO, "{} - attempted to access {} anf failed",
                request.getRemoteAddr(), request.getRequestURI());
        throw new ServiceException("forbiddenAccess");
    }

    private String getNameForSearch(final HttpServletRequest request) {
        String name = request.getParameter(NAME_PARAM);
        if (name == null) {
            HttpSession session = request.getSession(false);
            name = (String) session.getAttribute(NAME_PARAM);
        }
        return name;
    }
}
