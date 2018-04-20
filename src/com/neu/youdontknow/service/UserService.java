package com.neu.youdontknow.service;

import com.neu.youdontknow.admin.UserAdmin;
import com.neu.youdontknow.models.User;
import com.neu.youdontknow.utils.GlobalUtils;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    public User login(String username, String password) throws SQLException {
        UserAdmin userAdmin = new UserAdmin();
        List<User> user = userAdmin.queryByUsername(username);
        // if more than one users have the same username
        if(user.size() != 1) {
            GlobalUtils.raiseSQLError("More than one users have the same username, WTF?");
        } else if(false == GlobalUtils.checkPassword(user.get(0).getPassword(), password)) {
            GlobalUtils.alert("Password error");
            return null;
        } else return user.get(0);

        return null; // satisfy the Compiler
    }

    public int register(User newUser) throws SQLException {
        if(null == newUser) {
            GlobalUtils.alert("None register!");
            return -1;
        }
        return new UserAdmin().addUser(newUser);
    }
}
