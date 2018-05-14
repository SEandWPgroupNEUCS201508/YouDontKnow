package com.neu.youdontknow.servlet.pages.forum;

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
        List<Article> resList = null;
        try {
            resList = new ArticleService().getForumPage("test", 10, Integer.MAX_VALUE);
        } catch (SQLException e) {
            response.getWriter().write("Failed to get article list json cause the sql err!");
            GlobalUtils.alert("SQL err happend when get forum page!");
            e.printStackTrace();
        }
        if(null == resList || resList.isEmpty()) {
            response.getWriter().write("Error! Result set is empty!");
            GlobalUtils.alert("Forum page is empty");
        } else {
            request.getSession().setAttribute("article_list", resList);
            request.getSession().setAttribute("forum", "test");
            request.getSession().setAttribute("last_id", Integer.MAX_VALUE);
            request.getSession().setAttribute("limit_num", 10);
            response.sendRedirect("./json_templates/forum.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/jsp;charset=utf-8");

        String forum = request.getParameter("forum");
        int last_id = Integer.parseInt(request.getParameter("last_id"));
        int limit_num = Integer.parseInt(request.getParameter("limit_num"));

        if(forum == null || last_id < 0 || limit_num <= 0 || forum.isEmpty()) {
            response.getWriter().write("Please review the data you post!");
            GlobalUtils.alert("Post data were not fix the format!");
        } else {
            List<Article> resList = null;
            try {
                resList = new ArticleService().getForumPage(forum, limit_num, last_id);
            } catch (SQLException e) {
                response.getWriter().write("Failed to get article list json cause the sql err!");
                GlobalUtils.alert("SQL err happend when get forum page!");
                e.printStackTrace();
            }
            if(null == resList || resList.isEmpty()) {
                response.getWriter().write("Error! Result set is empty!");
                GlobalUtils.alert("Forum page is empty");
            } else {
                request.getSession().setAttribute("article_list", resList);
                request.getSession().setAttribute("forum", forum);
                request.getSession().setAttribute("last_id", last_id);
                request.getSession().setAttribute("limit_num", limit_num);
                response.sendRedirect("./json_templates/forum.jsp");
            }
        }
    }
}
