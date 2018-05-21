package com.neu.youdontknow.servlet.pages.comment;

import com.neu.youdontknow.models.Comment;
import com.neu.youdontknow.service.CommentService;
import com.neu.youdontknow.utils.GlobalUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class CommentPublication extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("404.htm");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        user_id, article_id, comment_id, comment
        request.setCharacterEncoding("UTF-8");
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int articleId = Integer.parseInt(request.getParameter("article_id"));
        int commentId = Integer.parseInt(request.getParameter("comment_id"));
        String content = request.getParameter("comment");

        Comment comment = new Comment();
        comment.setComment(content);
        comment.setArticle_id(articleId);
        comment.setComment_id(commentId);
        comment.setUser_id(userId);
        comment.setPublished_date(new Date(System.currentTimeMillis()));
        comment.setPublished_time(new Time(System.currentTimeMillis()));

        int tag = -1;
        try {
            tag = new CommentService().publish(comment);
        } catch (SQLException e) {
//            response.getWriter().write("{\"success\" : false}");
            e.printStackTrace();
        }
        if(tag == 1) { // success
            response.getWriter().write("{\"success\" : true}");
        } else {
            response.getWriter().write("{\"success\" : false}");
        }
    }
}
