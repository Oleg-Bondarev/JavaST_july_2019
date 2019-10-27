package by.training.final_task.controller.servlet;

import by.training.final_task.action.Action;
import by.training.final_task.action.ActionManager;
import by.training.final_task.action.ActionManagerFactory;
import by.training.final_task.controller.ControllerException;
import by.training.final_task.dao.PersistentException;
import by.training.final_task.dao.pool.ConnectionPool;
import by.training.final_task.service.ServiceException;
import by.training.final_task.service.implimentation.ServiceFactoryImpl;
import by.training.final_task.service.interfaces.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public final class StockServiceServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final ServletConfiguration servletConfig =
            new ServletConfiguration();

    @Override
    public void init() {
        try {
            ConnectionPool.getInstance().initialize(
                    servletConfig.getDbDriverClass(),
                    servletConfig.getDbURL(),
                    servletConfig.getDbUserLogin(),
                    servletConfig.getDbUserPassword(),
                    servletConfig.getDbPoolStartSize(),
                    servletConfig.getDbPoolMaxSize(),
                    servletConfig.getDbPoolCheckConnectionTimeout());
            LOGGER.log(Level.DEBUG, "Successful init servlet.");
        } catch (PersistentException newE) {
            LOGGER.log(Level.ERROR, "It is impossible to initialize" +
                    " application. " + newE.getMessage(), newE);
            destroy();
        }
    }

    @Override
    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response)
            throws ServletException, IOException {
        requestProcess(request, response);
    }

    @Override
    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServletException, IOException {
        requestProcess(request, response);
    }

    public ServiceFactory getFactory() throws ServiceException {
        return new ServiceFactoryImpl();
    }

    public ServletConfiguration getConfiguration() {
        return servletConfig;
    }

    //todo where is it use?
    private void requestProcess(final HttpServletRequest request,
                                final HttpServletResponse response)
            throws ServletException, IOException {
        Action action = (Action) request.getAttribute("action");
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Map<String, Object> attributes = (Map<String, Object>)
                        session.getAttribute("redirectedData");
                if (attributes != null) {
                    for (String key : attributes.keySet()) {
                        request.setAttribute(key, attributes.get(key));
                    }
                    session.removeAttribute("redirectedData");
                }
            }
            ActionManager actionManager = ActionManagerFactory
                    .getManager(getFactory());
            Action.Forward forward = actionManager
                    .execute(action, request, response);
            actionManager.close();

            if ((session != null) && (forward != null)
                    && (!forward.getAttributes().isEmpty())) {
                session.setAttribute("redirectedData",
                        forward.getAttributes());
            }
            String requestedUri = request.getRequestURI();

            if ((forward != null) && forward.isRedirect()) {
                String redirectedUri = request.getContextPath()
                        + forward.getForward();
                LOGGER.log(Level.DEBUG, "Request for URI {} id redirected"
                        + " to URI {}", requestedUri, redirectedUri);
                response.sendRedirect(redirectedUri);
            } else {
                String jspPage;
                if (forward != null) {
                    jspPage = forward.getForward();
                } else {
                    jspPage = action.getName() + ".jsp";
                }
                jspPage = "/WEB-INF/jsp" + jspPage;
                LOGGER.log(Level.DEBUG, "Request for URI {} is forwarded"
                        + " to JSP {}", requestedUri, jspPage);
                getServletContext().getRequestDispatcher(jspPage)
                        .forward(request, response);
            }
        } catch (ServiceException | ControllerException newE) {
            LOGGER.log(Level.ERROR, "It is impossible to process request",
                    newE);
            request.setAttribute("message", "cannotProcessData");
            request.setAttribute("exception", newE.getMessage()
                    /*"Error processing data"*/);
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/error404.jsp")
                    .forward(request, response);
        }
    }
}
