package by.training.final_task.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SessionLocaleFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest newServletRequest,
                         final ServletResponse newServletResponse,
                         final FilterChain newFilterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) newServletRequest;
        if (request.getParameter("lang") != null) {
            request.getSession().setAttribute("sessionLang",
                    request.getParameter("lang"));
        }
        newFilterChain.doFilter(newServletRequest, newServletResponse);
    }

    @Override
    public void destroy() {    }
}
