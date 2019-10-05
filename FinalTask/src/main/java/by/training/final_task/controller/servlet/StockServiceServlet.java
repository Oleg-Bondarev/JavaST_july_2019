package by.training.final_task.controller.servlet;

import by.training.final_task.controller.ControllerException;
import by.training.final_task.dao.PersistentException;
import by.training.final_task.dao.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class StockServiceServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();
    private ServletConfiguration servletConfig = new ServletConfiguration();

    public StockServiceServlet() throws ControllerException {
    }

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
            throws ServletException {
        requestProcess(request, response);
    }

    @Override
    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws ServletException {
        requestProcess(request, response);
    }

    private void requestProcess(final HttpServletRequest request,
                                final HttpServletResponse response)
            throws ServletException {
        //TODO
    }
}
