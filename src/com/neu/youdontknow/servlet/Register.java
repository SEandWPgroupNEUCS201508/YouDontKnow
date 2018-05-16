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
        int state = 0;
        try {
            BeanUtils.populate(user, request.getParameterMap());
            state = new UserService().register(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(1 == state) { // success
            response.getWriter().print("{\"success\":true}");
        } else {
            response.getWriter().print("{\"success\":false}");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
