package com.neu.youdontknow.servlet;

import com.neu.youdontknow.models.Article;
import com.neu.youdontknow.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String word = req.getParameter("word");
        List<Article> resList = null;
        try {
            resList = new ArticleService().searchByWord(word);
        } catch(Exception e) {
            if(null == resList) {
                resList = new ArrayList<Article>();
            }
            resList.clear();
        }
        resp.setContentType("text/jsp;charset=utf-8");
        req.setAttribute("resList", resList);
        req.setAttribute("word", word);
        req.getRequestDispatcher("./json_templates/search_article.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
