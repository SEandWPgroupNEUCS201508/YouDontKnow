package com.neu.youdontknow.servlet.pages.comment;

import com.neu.youdontknow.admin.CommentAdmin;
import com.neu.youdontknow.models.Comment;
import com.neu.youdontknow.utils.GlobalUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CommentPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("./404.htm");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int commentId = Integer.parseInt(request.getParameter("comment_id"));
        if(commentId <= 0) {
            response.getWriter().write("Please check the format of the data you post!");
            GlobalUtils.alert("Format ERR!");
        } else {
            Comment comment = null;
            try {
                comment = new CommentAdmin().queryById(commentId).get(0);
            } catch(SQLException e) {
                response.getWriter().write("SQL err happen when query comment by their id");
                e.printStackTrace();
            }
            if(comment == null) {
                GlobalUtils.alert("Can't find out the target!");
                response.getWriter().write("Can't find out the target!");
            } else {
                request.getSession().setAttribute("comment", comment);
                response.sendRedirect("./json_tempates/comment.jsp");
            }
        }
    }
}
