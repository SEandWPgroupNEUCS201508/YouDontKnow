package com.neu.youdontknow.utils;

import javax.servlet.Filter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // 获得在下面代码中要用的request,response,session对象

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();

        boolean hasLogin = session.getAttribute("user")==null?false:true;

        // judge whether the user has logged in
        if (hasLogin) {
            // go on this request
            chain.doFilter(request, response);
        }else {
            // redirect to login page
            servletResponse.sendRedirect("/login.html");
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

}