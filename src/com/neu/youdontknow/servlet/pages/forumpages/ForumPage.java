package com.neu.youdontknow.servlet.pages.forumpages;

import com.neu.youdontknow.admin.ArticleAdmin;
import com.neu.youdontknow.models.Article;
import com.neu.youdontknow.service.ArticleService;
import com.neu.youdontknow.utils.GlobalUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ForumPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String forum = request.getQueryString();

        if(null == forum || forum.isEmpty()) {
            GlobalUtils.alert("Can't get the forum(null) from frontend.");
            response.sendRedirect("/404.htm");
        } else {
            List<Article> resList = null;
            try {
                resList = new ArticleService().getForumPage(forum, 10);
            } catch(SQLException e) {
                GlobalUtils.alert("failed to get forum page's article from backend: " + forum);
                e.printStackTrace();
            }

            if(resList.isEmpty() || null == resList)
                GlobalUtils.alert("resList get for forum: " + forum + " is empty");
            else {
                request.getSession().setAttribute("articleList", resList);
                response.sendRedirect("/testForum.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/404.htm");
    }
}
