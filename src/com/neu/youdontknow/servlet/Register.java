package com.neu.youdontknow.servlet;

import com.neu.youdontknow.admin.UserAdmin;
import com.neu.youdontknow.models.User;
import com.neu.youdontknow.service.UserService;
import com.neu.youdontknow.utils.GlobalUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
            int state = new UserService().register(user);
            if(1 == state) { // success
                response.getWriter().print("success");
            } else {
                response.getWriter().print("failed to register");
                GlobalUtils.alert("failed to register");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/index");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.sendRedirect("register.html");
    }
}
