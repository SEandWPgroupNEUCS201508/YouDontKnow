package com.neu.youdontknow.servlet.pages.articlepages;

import com.neu.youdontknow.models.Article;
import com.neu.youdontknow.service.ArticleService;
import com.neu.youdontknow.service.UserService;
import com.neu.youdontknow.utils.GlobalUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ArticlePublication extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/404.htm");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        Article article = new Article();
        try {
            BeanUtils.populate(article, request.getParameterMap());
            int state = new ArticleService().publish(article);
            if(1 == state) { // success
                response.getWriter().print("success");
            } else {
                response.getWriter().print("failed to register");
                GlobalUtils.alert("failed to register");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
