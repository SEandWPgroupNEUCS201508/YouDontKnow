package com.neu.youdontknow.servlet.pages.article;

import com.neu.youdontknow.models.Article;
import com.neu.youdontknow.service.ArticleService;
import com.neu.youdontknow.utils.GlobalUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

public class ArticlePublication extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/publish_article.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        Article article = new Article();
        response.setCharacterEncoding("utf-8");
        try {
            BeanUtils.populate(article, request.getParameterMap());
            if(null == article) {
                response.getWriter().print("{\"success\" : false}");
            } else {
                article.setPublished_date(new Date(System.currentTimeMillis()));
                article.setPublished_time(new Time(System.currentTimeMillis()));
            }
            if(new ArticleService().publish(article) == 1) { // success
                response.getWriter().print("{\"success\" : true}");
            } else {
                response.getWriter().print("{\"success\" : false}");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
