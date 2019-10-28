package by.training.final_task.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Encoding filter. Use UTF-8.
 */
public class EncodingFilter implements Filter {
    /**
     * Character encoding to which request encoding will be set.
     */
    private static final String REQUEST_ENCODING = "UTF-8";

    /**
     * Initializer.
     *
     * @param filterConfig filter config.
     */
    @Override
    public void init(final FilterConfig filterConfig) {

    }

    /**
     * Using in chain.
     *
     * @param newServletRequest  request from servlet.
     * @param newServletResponse response to servlet.
     * @throws IOException      if have problems in setCharacterEncoding.
     * @throws ServletException servlet exception.
     */
    @Override
    public void doFilter(final ServletRequest newServletRequest,
                         final ServletResponse newServletResponse,
                         final FilterChain newFilterChain)
            throws IOException, ServletException {
        newServletRequest.setCharacterEncoding(REQUEST_ENCODING);
        newServletResponse.setCharacterEncoding(REQUEST_ENCODING);
        newFilterChain.doFilter(newServletRequest, newServletResponse);
    }

    /**
     * Destroy method.
     */
    @Override
    public void destroy() {
    }
}
