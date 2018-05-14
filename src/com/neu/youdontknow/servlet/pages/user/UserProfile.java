package com.neu.youdontknow.servlet.pages.user;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/jsp;charset=utf-8");
        response.sendRedirect("./json_templates/user_profile.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/jsp;charset=utf-8");
        response.sendRedirect("./json_templates/user_profile.jsp");
    }
}
