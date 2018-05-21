package com.neu.youdontknow.servlet.pages.user;

import com.neu.youdontknow.admin.UserAdmin;
import com.neu.youdontknow.models.User;
import com.neu.youdontknow.utils.GlobalUtils;

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
            target = new UserAdmin().queryById(user_id);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        if(user_id <= 0 || target == null)
            response.getWriter().write("{\"success\" : false}");
        else if(GlobalUtils.checkPassword(origin_password, target.getPassword()) != true)
            response.getWriter().write("{\"success\" : false}");
        else {
            if(new_email == null || new_email.isEmpty())
                response.getWriter().write("{\"success\" : false}");
            if(new_password == null || new_password.isEmpty())
                response.getWriter().write("{\"success\" : false}");
            target.setPassword(GlobalUtils.encryptPassword(new_password));
            target.setEmail(new_email);

            int flag = 0;
            try {
                flag = new UserAdmin().updateById(user_id, target);
            } catch(SQLException e) {
                response.getWriter().write("{\"success\" : false}");
                e.printStackTrace();
            }
            if(1 == flag) { // success
                response.getWriter().write("{\"success\" : true}");
            } else {
                response.getWriter().write("{\"success\" : false}");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.sendRedirect("./404.htm");
    }
}
