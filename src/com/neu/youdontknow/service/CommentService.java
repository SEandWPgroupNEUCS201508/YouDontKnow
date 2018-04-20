package com.neu.youdontknow.service;

import com.neu.youdontknow.admin.CommentAdmin;
import com.neu.youdontknow.models.Comment;
import com.neu.youdontknow.utils.GlobalUtils;

import java.sql.SQLException;

public class CommentService {

    public int comment(Comment newComment) throws SQLException {
        if(null == newComment) return -1;
        return new CommentAdmin().addComment(newComment);
    }

    public int updateComment(int commentId, Comment tmp) throws SQLException {
        if(commentId <= 0 || null == tmp) {
            GlobalUtils.alert("No comment will be update");
            return -1;
        }
        return new CommentAdmin().updateById(commentId, tmp);
    }

}
