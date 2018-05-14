package com.neu.youdontknow.servlet.pages.article;

import com.neu.youdontknow.admin.ArticleAdmin;
import com.neu.youdontknow.models.Article;
import com.neu.youdontknow.utils.GlobalUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateArticle extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/404.htm");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // accept the username and password from frontend
        response.setContentType("text/html;charset=utf-8");

        String newTitle = request.getParameter("title");
        String newContent = request.getParameter("content");
        int article_id = Integer.parseInt(request.getParameter("article_id"));
        String forum = request.getParameter("forum");

        if(newTitle == null || newTitle.isEmpty() || newContent == null || newContent.isEmpty() ||
                article_id <= 0 || forum.isEmpty() || forum == null) {
            response.getWriter().write("Please check the data you post!");
            GlobalUtils.alert("Post data for updating article err!");
        }

        Article target = null;

        try {
            target = new ArticleAdmin().queryById(article_id).get(0);
        } catch(SQLException e) {
            GlobalUtils.alert("Failed to get update target article from database");
            response.getWriter().write("Failed to get update target article from database");
            e.printStackTrace();
        }
        if(article_id <= 0 || target == null)
            response.getWriter().write("Can't find out the article");
        else {
            if(newTitle == null || newTitle.isEmpty())
                response.getWriter().write("Warning! New title is empty or null!");
            else if(newContent == null || newContent.isEmpty())
                response.getWriter().write("Warning! New content is empty or null!");
            else if(forum == null || forum.isEmpty())
                response.getWriter().write("Warning! New forum you set may be empty!");

            target.setForum(forum);
            target.setContent(newContent);
            target.setTitle(newTitle);

            int flag = 0;
            try {
                flag = new ArticleAdmin().updateById(article_id, target);
            } catch(SQLException e) {
                response.getWriter().write("Update article failed cause sql err!");
                GlobalUtils.alert("Update article failed cause sql err!");
                e.printStackTrace();
            }
            if(1 == flag) { // success
                response.getWriter().write("success to update the article");
            } else {
                GlobalUtils.alert("Update article failed cause unknown err");
                response.getWriter().write("Update article failed cause unknown err");
            }
        }
    }
}
