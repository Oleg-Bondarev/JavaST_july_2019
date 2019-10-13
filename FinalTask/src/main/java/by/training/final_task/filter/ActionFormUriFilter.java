package by.training.final_task.filter;

import by.training.final_task.action.*;
import by.training.final_task.action.admin.AddAdminAction;
import by.training.final_task.action.admin.FindAdminAction;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionFormUriFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger();
    private static Map<String, Class<? extends Action>> actionMap =
            new ConcurrentHashMap<>();

    static {
        actionMap.put("/", MainAction.class);
        actionMap.put("/homepage", MainAction.class);
        actionMap.put("/login", LoginAction.class);
        actionMap.put("/logout", LogoutAction.class);
        actionMap.put("/register", RegisterAction.class);
        actionMap.put("/coupons", CouponAction.class);
        actionMap.put("/user/profile",  ProfileAction.class);
        actionMap.put("/user/admin/addadmin", AddAdminAction.class);
        actionMap.put("/user/admin/findadmin", FindAdminAction.class);
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest newServletRequest,
                         final ServletResponse newServletResponse,
                         final FilterChain newFilterChain)
            throws IOException, ServletException {
        if (newServletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) newServletRequest;
            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();
            LOGGER.log(Level.INFO, "Starting of processing of request for" +
                    " URI {}", uri);
            int beginAction = contextPath.length();
            int endAction = uri.lastIndexOf('.');
            String actionName;

            if (endAction >= 0) {
                actionName = uri.substring(beginAction, endAction);
            } else {
                actionName =uri.substring(beginAction);
            }

            Class<? extends Action> actionClass = actionMap.get(actionName);
            try {
                Action action = actionClass.newInstance();
                action.setName(actionName);
                httpRequest.setAttribute("action", action);
                clearSessionMessages(httpRequest);
                newFilterChain.doFilter(newServletRequest, newServletResponse);
            } catch (IllegalAccessException | InstantiationException
                    | NullPointerException newE) {
                LOGGER.log(Level.INFO, "It is impossible to create action"
                        + " handler object " + newE.getMessage());
                httpRequest.setAttribute("error", String.format(
                        "Requested address %s cannot be processed", uri));
                httpRequest.getServletContext().getRequestDispatcher(
                "/WEB-INF/jsp/error.jsp").forward(newServletRequest,
                        newServletResponse);
            }
        } else {
            LOGGER.log(Level.INFO, "It is impossible to use HTTP filter");
        }
    }

    /**
     * Clear all messages when new action is created.
     *
     * @param request -Servlet request.
     * */
    private void clearSessionMessages(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("message", null);
            session.setAttribute("successMessage", null);
        }
    }

    @Override
    public void destroy() {
    }
}
