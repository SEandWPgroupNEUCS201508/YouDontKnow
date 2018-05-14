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
        request.setCharacterEncoding("utf-8");
        Article article = new Article();
        try {
            BeanUtils.populate(article, request.getParameterMap());
            // test
            if(null == article) {
                GlobalUtils.alert("Can't get article from request!");
            } else {
                article.setPublished_date(new Date(System.currentTimeMillis()));
                article.setPublished_time(new Time(System.currentTimeMillis()));
            }
            int state = new ArticleService().publish(article);
            if(1 == state) { // success
                response.getWriter().print("success to publish the article");
            } else {
                response.getWriter().print("failed to publish article");
                GlobalUtils.alert("failed to publish article");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
