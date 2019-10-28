package by.training.final_task.filter;

import by.training.final_task.action.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Url action filter.
 * */
public class ActionFormUriFilter implements Filter {
    /**
     * Class logger.
     * */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Initialize.
     *
     * @param filterConfig filter config.
     */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }
    /**
     * Do filter.
     *
     * @param newServletRequest  request.
     * @param newServletResponse response.
     * @param newFilterChain     chain.
     * @throws IOException       i/o exception.
     * @throws ServletException  servlet exception.
     */
    @Override
    public void doFilter(final ServletRequest newServletRequest,
                         final ServletResponse newServletResponse,
                         final FilterChain newFilterChain)
            throws IOException, ServletException {
        if (newServletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest)
                    newServletRequest;
            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();
            LOGGER.log(Level.INFO, "Starting of processing of request for"
                    + " URI {}", uri);
            int beginAction = contextPath.length();
            int endAction = uri.lastIndexOf('.');
            String actionName;

            if (endAction >= 0) {
                actionName = uri.substring(beginAction, endAction);
            } else {
                actionName = uri.substring(beginAction);
            }

            ActionCommandClass actionCommandClass = new ActionCommandClass();
            try {
                Action action = actionCommandClass.getAction(actionName);
                action.setName(actionName);
                httpRequest.setAttribute("action", action);
                clearSessionMessages(httpRequest);
                newFilterChain.doFilter(newServletRequest, newServletResponse);
            } catch (NullPointerException newE) {
                LOGGER.log(Level.INFO, "It is impossible to create action"
                        + " handler object " + newE.getMessage());
                httpRequest.setAttribute("error", String.format(
                        "Requested address %s cannot be processed", uri));
                httpRequest.getServletContext().getRequestDispatcher(
                        "/WEB-INF/jsp/error404.jsp").forward(newServletRequest,
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
     */
    private void clearSessionMessages(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("message", null);
            session.setAttribute("successMessage", null);
        }
    }
    /**
     * Destroy.
     */
    @Override
    public void destroy() {
    }
}
