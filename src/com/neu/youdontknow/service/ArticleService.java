package com.neu.youdontknow.service;

import com.neu.youdontknow.admin.ArticleAdmin;
import com.neu.youdontknow.models.Article;
import com.neu.youdontknow.utils.GlobalUtils;

import java.sql.SQLException;

public class ArticleService {

    public int publishArticle(Article article) throws SQLException {
        if(null == article) {
            GlobalUtils.alert("the article doesn't exist");
            return -1;
        }
        return new ArticleAdmin().addArticle(article);
    }

    public int updateArticle(int id, Article tmp) throws SQLException {
        if(id <= 0 || null == tmp) {
            GlobalUtils.alert("No article will be update");
            return -1;
        }
        return new ArticleAdmin().updateById(id, tmp);
    }
}
