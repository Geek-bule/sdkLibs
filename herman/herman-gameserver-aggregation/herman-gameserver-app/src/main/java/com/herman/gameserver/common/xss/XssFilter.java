package com.herman.gameserver.common.xss;

import javax.servlet.*;
import java.io.IOException;

/**
 * xss过滤器,防止XSS攻击
 * Created by herman on 2018/2/12.
 */
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
