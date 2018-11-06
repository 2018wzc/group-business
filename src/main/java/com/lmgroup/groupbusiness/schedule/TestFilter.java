package com.lmgroup.groupbusiness.schedule;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器调用测试
 *
 * @author wangzichun 时间:2018/11/06
 */
@WebFilter(urlPatterns = "/hello/*", filterName = "testFilter")
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("过滤器调用成功!");
    }

    @Override
    public void destroy() {

    }
}
