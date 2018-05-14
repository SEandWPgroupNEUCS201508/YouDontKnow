package com.neu.youdontknow.servlet;

import com.neu.youdontknow.models.User;
import com.neu.youdontknow.service.UserService;
import com.neu.youdontknow.utils.GlobalUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Login extends HttpServlet {

    @Override
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // accept the username and password from frontend
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(null == username || null == password || password.isEmpty() || username.isEmpty()) {
            GlobalUtils.alert("Username or password is empty");
        } else {
            User user = null;

            try {
                user = new UserService().login(username, password);
            } catch(SQLException e) {
                e.printStackTrace();
            }

            if(null != user) {
                request.getSession().setAttribute("user", user);
                Cookie userId = new Cookie("userid", new Integer(user.getId()).toString());
                response.addCookie(userId);
                response.sendRedirect("/index");
            } else {
                GlobalUtils.alert("User is not found when login");
                response.sendRedirect("/login");
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.sendRedirect("./login.html");
    }
}
