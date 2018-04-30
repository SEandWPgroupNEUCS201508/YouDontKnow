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
        } else if(false == GlobalUtils.checkPassword(password, user.get(0).getPassword())) {
            GlobalUtils.alert("Password error");
            return null;
        } else user.get(0);

        return null; // satisfy the Compiler
    }

    public int register(User newUser) throws SQLException {
        if(null == newUser) {
            GlobalUtils.alert("None register!");
            return -1;
        }
        // encrypt the password of the user obj that will be storaged in the database
        String srcPassword = newUser.getPassword();
        newUser.setPassword(GlobalUtils.encryptPassword(srcPassword));

        return new UserAdmin().addUser(newUser);
    }

    public int updateUser(User tmp) throws SQLException {
        if(null == tmp) {
            GlobalUtils.alert("No user would be update");
            return -1;
        }
        // defaultly, we think that the tmp user obj's password is not encrypted
        // so you know...
        String srcPassword = tmp.getPassword();
        tmp.setPassword(GlobalUtils.encryptPassword(srcPassword));
        
        return new UserAdmin().updateById(tmp.getId(), tmp);
    }
}
