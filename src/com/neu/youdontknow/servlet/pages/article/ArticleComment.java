package com.neu.youdontknow.servlet.pages.article;

import com.neu.youdontknow.models.Comment;
import com.neu.youdontknow.service.ArticleService;
import com.neu.youdontknow.service.CommentService;
import com.neu.youdontknow.utils.GlobalUtils;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        int articleId = Integer.parseInt(request.getParameter("article_id"));

        if(articleId <= 0) {
            response.getWriter().write("Please post correct data! Article_id <= 0!");
            GlobalUtils.alert("Post data was not fix the format!");
        } else {
            List<Comment> resList = null;
            try {
                resList = new CommentService().getArticleComments(articleId);
            } catch (SQLException e) {
                response.getWriter().write("Failed to get comment list json cause the sql err!");
                GlobalUtils.alert("SQL err happend when get article-comment page!");
                e.printStackTrace();
            }
            if(null == resList || resList.isEmpty()) {
                response.getWriter().write("Error! Result set is empty!");
                GlobalUtils.alert("Article-comment page is empty");
            } else {
                request.getSession().setAttribute("comment_list", resList);
                request.getSession().setAttribute("article_id", articleId);
                response.sendRedirect("./json_templates/article_comment.jsp");
            }
        }
    }
}
