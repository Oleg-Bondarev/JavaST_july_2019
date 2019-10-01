package by.training.final_task.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(final ServletRequest newServletRequest,
                         final ServletResponse newServletResponse,
                         final FilterChain newFilterChain)
            throws IOException, ServletException {
        newServletRequest.setCharacterEncoding("UTF-8");
        newFilterChain.doFilter(newServletRequest, newServletResponse);
    }

    @Override
    public void destroy() { }
}
