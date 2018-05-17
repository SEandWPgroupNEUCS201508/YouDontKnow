package com.neu.youdontknow.servlet.pages.article;

import com.neu.youdontknow.models.Comment;
import com.neu.youdontknow.service.ArticleService;
import com.neu.youdontknow.service.CommentService;
import com.neu.youdontknow.utils.GlobalUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ArticleComment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("./404.htm");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException , ServletException {
        response.setContentType("text/html;charset=utf-8");
        int articleId = Integer.parseInt(request.getParameter("article_id"));
        List<Comment> resList = null;
        try {
            resList = new CommentService().getArticleComments(articleId);
        } catch (SQLException e) {
            request.setAttribute("comment_list", resList);
            request.setAttribute("article_id", null);
            request.getRequestDispatcher("./json_templates/article_comment.jsp").forward(request, response);
            e.printStackTrace();
        }
        request.setAttribute("comment_list", resList);
        request.setAttribute("article_id", articleId);
        request.getRequestDispatcher("./json_templates/article_comment.jsp").forward(request, response);
    }
}
