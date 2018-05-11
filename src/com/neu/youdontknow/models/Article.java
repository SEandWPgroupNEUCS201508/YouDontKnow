package com.neu.youdontknow.models;

import java.sql.Date;
import java.sql.Time;

public class Article implements Model {
    private int id;
    private String title;
    private String content;
    private int user_id;
    private String forum;
    private Date published_date;
    private Time published_time;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getForum() {
        return forum;
    }

    public Date getPublished_date() {
        return published_date;
    }

    public Time getPublished_time() {
        return published_time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setForum(String forum) {
        this.forum = forum;
    }

    public void setPublished_date(Date published_date) {
        this.published_date = published_date;
    }

    public void setPublished_time(Time published_time) {
        this.published_time = published_time;
    }
}
