package com.demo.custom.metrics.filter;

import com.demo.custom.metrics.metrics.HttpServerRequestCounter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class CustomWebFilter implements Filter {

    @Autowired
    private HttpServerRequestCounter httpServerRequestCounter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter: {}", servletRequest);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        httpServerRequestCounter.addCount(request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
