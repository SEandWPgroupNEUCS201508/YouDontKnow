package com.neu.youdontknow.servlet.pages.comment;

import com.neu.youdontknow.admin.CommentAdmin;
import com.neu.youdontknow.models.Comment;
import com.neu.youdontknow.utils.GlobalUtils;

import javax.servlet.ServletException;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int commentId = Integer.parseInt(request.getParameter("comment_id"));
        if(commentId <= 0) {
            response.getWriter().write("{}");
        } else {
            Comment comment = null;
            try {
                comment = new CommentAdmin().queryById(commentId).get(0);
            } catch(SQLException e) {
                response.getWriter().write("{}");
                e.printStackTrace();
            }
            if(comment == null) {
                response.getWriter().write("{}");
            } else {
                request.setAttribute("comment", comment);
                request.getRequestDispatcher("./json_tempates/comment.jsp").forward(request, response);
            }
        }
    }
}
