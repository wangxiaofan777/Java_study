package com.wxf.eshaop.cache.ha.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


/**
 * Hystrix请求过滤器
 */
@WebFilter(filterName = "hystrixRequestContextFilter", urlPatterns = "/*")
public class HystrixRequestContextFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {

    }
}
