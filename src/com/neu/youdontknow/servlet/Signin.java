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

public class Signin extends HttpServlet {

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

//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//
//        user.setUsername(username);
//        user.setPassword(GlobalUtils.encryptPassword(password));
//        user.setEmail(email);
//
//        try {
//            new UserAdmin().addUser(user);
//        } catch (SQLException e) {
//            GlobalUtils.alert("add user failed");
//            e.printStackTrace();
//        }

        response.sendRedirect("index.jsp");
    }
}
