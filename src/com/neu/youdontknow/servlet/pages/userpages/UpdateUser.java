package com.neu.youdontknow.servlet.pages.userpages;

import com.neu.youdontknow.admin.UserAdmin;
import com.neu.youdontknow.models.User;
import com.neu.youdontknow.service.UserService;
import com.neu.youdontknow.utils.GlobalUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateUser extends HttpServlet {
    @Override
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // accept the username and password from frontend
        response.setContentType("text/html;charset=utf-8");

        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String origin_password = request.getParameter("origin_password");
        String new_email = request.getParameter("new_email");
        String new_password = request.getParameter("new_password");

        User target = null;

        try {
            target = new UserAdmin().queryById(user_id).get(0);
        } catch(SQLException e) {
            GlobalUtils.alert("Failed to get update target user from database");
            e.printStackTrace();
        }
        if(user_id <= 0 || target == null)
            response.getWriter().write("Can't find out the user");
        else if(GlobalUtils.checkPassword(origin_password, target.getPassword()) != true)
            response.getWriter().write("Password err! Unable to update!");
        else {
            if(new_email == null || new_email.isEmpty())
                response.getWriter().write("Warning! new_email is null or empty!");
            if(new_password == null || new_password.isEmpty())
                response.getWriter().write("Warning! new_password is null or empty!");
            target.setPassword(GlobalUtils.encryptPassword(new_password));
            target.setEmail(new_email);

            int flag = 0;
            try {
                flag = new UserAdmin().updateById(user_id, target);
            } catch(SQLException e) {
                response.getWriter().write("Update user failed cause sql err!");
                GlobalUtils.alert("Update user failed cause sql err!");
                e.printStackTrace();
            }
            if(1 == flag) { // success
                response.getWriter().write("success to update the user");
            } else {
                GlobalUtils.alert("Update user failed cause unknown err");
                response.getWriter().write("Update user failed cause unknown err");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.sendRedirect("./404.htm");
    }
}
