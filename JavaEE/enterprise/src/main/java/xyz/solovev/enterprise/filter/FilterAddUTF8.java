package xyz.solovev.enterprise.filter;

import xyz.solovev.enterprise.utils.LogSystem;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = FilterAddUTF8.NAME, urlPatterns = {"/*"})
public class FilterAddUTF8 implements Filter {

    public static final String NAME = "FilterAddUTF8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LogSystem.getLogger().info("Enter FilterAddUTF8");
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html; charset=UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
