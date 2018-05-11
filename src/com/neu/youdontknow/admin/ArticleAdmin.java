package com.neu.youdontknow.admin;

import com.neu.youdontknow.models.Article;
import com.neu.youdontknow.utils.DataBaseUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class ArticleAdmin {

    /**
     * add a new article to article table
     * by new object
     *
     * @param article
     * @return
     * @throws SQLException
     */
    public int addArticle(Article article) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "insert into article(title, content, user_id, forum, published_date, published_time) " +
                "values(?, ?, ?, ?, ?, ?)";
        return queryRunner.update(
                sql, article.getTitle(), article.getContent(), article.getUser_id(),
                article.getForum(), article.getPublished_date(), article.getPublished_time()
                );
    }

    /**
     * delete an existed article from table
     * by id
     *
     * @param articleId
     * @return
     * @throws SQLException
     */
    public int deleteById(int articleId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "delete from article where id=?";
        return queryRunner.update(sql, articleId);
    }

    // cascade delete from user
    public int deleteByUserId(int userId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "delete from article where user_id=?";
        return queryRunner.update(sql, userId);
    }


    /**
     * query the article that id = ?
     *
     * @param articleId
     * @return
     * @throws SQLException
     */
    public List<Article> queryById(int articleId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "select * from article where id=?";
        return queryRunner.query(sql, new BeanListHandler<>(Article.class), articleId);
    }

    public List<Article> queryByForum(String forum, int num) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "select * from article where forum=? limit ?";
        return queryRunner.query(sql, new BeanListHandler<>(Article.class), forum, num);
    }

    /**
     * update the aritcle to the new one by article objects
     *
     * @param articleId
     * @param tmp
     * @return
     * @throws SQLException
     */
    public int updateById(int articleId, Article tmp) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "update article set title=?, content=?, user_id=? where id = ?";
        return queryRunner.update(sql, tmp.getTitle(), tmp.getContent(), tmp.getUser_id(), articleId);
    }

}
