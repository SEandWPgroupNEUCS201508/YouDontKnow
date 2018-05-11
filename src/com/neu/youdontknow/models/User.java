package com.neu.youdontknow.models;

public class User implements Model{
    private int id;
    private String username;
    private String password;
    private String email;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
