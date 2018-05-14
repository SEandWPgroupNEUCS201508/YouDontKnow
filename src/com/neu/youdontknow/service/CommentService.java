package com.neu.youdontknow.service;

import com.neu.youdontknow.admin.CommentAdmin;
import com.neu.youdontknow.models.Comment;
import com.neu.youdontknow.models.Model;
import com.neu.youdontknow.utils.GlobalUtils;

import java.sql.SQLException;
import java.util.List;

public class CommentService implements Service {

    // public interface Service

    public int publish(Model tmp) throws SQLException {
        if(tmp == null || !(tmp instanceof Comment)) {
            GlobalUtils.alert("Can't publish the comment by common Interface");
            return -1;
        } else {
            return this.publishComment((Comment)tmp);
        }
    }

    public int delete(Model target, boolean cascade) throws SQLException {
        if(target == null || !(target instanceof Comment)) {
            GlobalUtils.alert("Can't delete the comment by common Interface");
            return -1;
        } else {
            return this.deleteComment((Comment)target, cascade);
        }
    }

    public int update(int id, Model tmp) throws SQLException {
        if(null == tmp || !(tmp instanceof Comment)) {
            GlobalUtils.alert("Can't update the comment by comment Interface");
            return -1;
        } else {
            return this.updateComment(id, (Comment)tmp);
        }
    }

    // public func
    public List<Comment> getArticleComments(int articleId) throws SQLException {
        if(articleId <= 0){
            GlobalUtils.alert("No comment will be back for the article!");
            return null;
        } else return new CommentAdmin().queryByArticleId(articleId);
    }

    // private func

    private int publishComment(Comment newComment) throws SQLException {
        if(null == newComment) return -1;
        return new CommentAdmin().addComment(newComment);
    }

    private int updateComment(int commentId, Comment tmp) throws SQLException {
        if(commentId <= 0 || null == tmp) {
            GlobalUtils.alert("No comment will be update");
            return -1;
        }
        return new CommentAdmin().updateById(commentId, tmp);
    }

    private int deleteComment(Comment target, boolean cascade) throws SQLException {
        if(null == target) {
            GlobalUtils.alert("No comment will be updated");
            return -1;
        }

        return new CommentAdmin().deleteById(target.getId());
    }

}
