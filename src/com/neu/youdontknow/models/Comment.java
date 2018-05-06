package com.neu.youdontknow.models;

public class Comment implements Model {
    private int id;
    private String comment;
    private int user_id;
    private int article_id;
    private int comment_id;

    public int getId() {
        return id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public String getComment() {
        return comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }
}
