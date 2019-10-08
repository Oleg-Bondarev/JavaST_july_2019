package by.training.final_task.filter;

import by.training.final_task.action.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
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

        }
    }

    @Override
    public void destroy() {

    }
}
