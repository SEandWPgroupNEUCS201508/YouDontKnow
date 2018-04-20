package com.neu.youdontknow.admin;

import com.neu.youdontknow.models.Comment;
import com.neu.youdontknow.utils.DataBaseUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CommentAdmin {

    public int addComment(Comment comment) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "insert into comment(comment, user_id, article_id, comment_id) values(?, ?, ?, ?)";
        return queryRunner.update(sql,
                comment.getComment(), comment.getUser_id(), comment.getArticle_id(), comment.getComment_id());
    }

    public int deleteById(int commentId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "delete from comment where id=?";
        return queryRunner.update(sql, commentId);
    }

    public List<Comment> queryById(int commentId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "select * from comment where id=?";
        return queryRunner.query(sql, new BeanListHandler<>(Comment.class), commentId);
    }

    public int updateById(int commentId, Comment tmp) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "update comment set comment=?, user_id=?, article_id=?, comment_id=? where id=?";
        return queryRunner.update(sql,
                tmp.getComment(), tmp.getUser_id(), tmp.getArticle_id(), tmp.getComment_id(), commentId);
    }
}
