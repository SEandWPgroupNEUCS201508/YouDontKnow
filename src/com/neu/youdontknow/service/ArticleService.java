package com.neu.youdontknow.service;

import com.neu.youdontknow.admin.ArticleAdmin;
import com.neu.youdontknow.admin.CommentAdmin;
import com.neu.youdontknow.models.Article;
import com.neu.youdontknow.models.Model;
import com.neu.youdontknow.utils.GlobalUtils;

import java.sql.SQLException;
import java.util.List;

public class ArticleService implements Service {

    // public interface Service

    public int publish(Model tmp) throws SQLException {
        if(tmp == null || !(tmp instanceof Article)) {
            GlobalUtils.alert("Can't publish the article by common Interface");
            return -1;
        } else {
            return this.publishArticle((Article)tmp);
        }
    }

    public int delete(Model target, boolean cascade) throws SQLException {
        if(target == null || !(target instanceof Article)) {
            GlobalUtils.alert("Can't delete the article by common Interface");
            return -1;
        } else {
            return this.deleteArticle((Article)target, cascade);
        }
    }

    public int update(int id, Model tmp) throws SQLException{
        if(null == tmp || !(tmp instanceof Article)) {
            GlobalUtils.alert("Can't update the article by comment Interface");
            return -1;
        } else {
            return this.updateArticle(id, (Article)tmp);
        }
    }

    // public func

    public List<Article> getForumPage(String forum, int articleNum, int lastId) throws SQLException {
        if(forum.isEmpty() || null == forum || 0 == articleNum) {
            GlobalUtils.alert("No article will be back for the forum: " + forum);
            return null;
        } else
            return new ArticleAdmin().queryByForum(forum, articleNum, lastId);
    }

    // private func

    private int publishArticle(Article article) throws SQLException {
        if(null == article) {
            GlobalUtils.alert("the article doesn't exist");
            return -1;
        }
        return new ArticleAdmin().addArticle(article);
    }

    private int updateArticle(int id, Article tmp) throws SQLException {
        if(id <= 0 || null == tmp) {
            GlobalUtils.alert("No article will be update");
            return -1;
        }
        return new ArticleAdmin().updateById(id, tmp);
    }

    private int deleteArticle(Article target, boolean cascade) throws SQLException {
        if(null == target) {
            GlobalUtils.alert("No article will be delete");
            return -1;
        }

        ArticleAdmin articleAdmin = new ArticleAdmin();
        int articleId = target.getId();

        int articleDeleteFlag = articleAdmin.deleteById(articleId);
        if(true == cascade) {
            int commentCascadeFlag = new CommentAdmin().deleteByArticleId(articleId);
            if(1 != commentCascadeFlag)
                articleDeleteFlag = -1;
        }
        return articleDeleteFlag;
    }
}
