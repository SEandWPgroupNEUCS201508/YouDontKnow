package com.neu.youdontknow.service;

import com.neu.youdontknow.admin.ArticleAdmin;
import com.neu.youdontknow.admin.CommentAdmin;
import com.neu.youdontknow.admin.UserAdmin;
import com.neu.youdontknow.models.Article;
import com.neu.youdontknow.models.Model;
import com.neu.youdontknow.models.User;
import com.neu.youdontknow.utils.GlobalUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserService implements Service {

    // public interface Service

    public int publish(Model tmp) throws SQLException {
        if(tmp == null || !(tmp instanceof User)) {
            GlobalUtils.alert("Can't publish the user by common Interface");
            return -1;
        } else {
            return this.register((User)tmp);
        }
    }

    public int delete(Model target, boolean cascade) throws SQLException {
        if(target == null || !(target instanceof User)) {
            GlobalUtils.alert("Can't delete the user by common Interface");
            return -1;
        } else {
            return this.deleteUser((User)target, cascade);
        }
    }

    public int update(int id, Model tmp) throws SQLException{
        if(null == tmp || !(tmp instanceof Article)) {
            GlobalUtils.alert("Can't update the user by comment Interface");
            return -1;
        } else {
            return this.updateUser((User)tmp);
        }
    }

    // public service method

    public User login(String username, String password) throws SQLException, IOException {
        User user = new UserAdmin().queryByUsername(username);
        if(user == null)
            return null;
        if(false == GlobalUtils.checkPassword(password, user.getPassword())) {
            return null;
        } else return user;
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

    private int updateUser(User tmp) throws SQLException {
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

    /**
     *
     * @param target
     * the user that you wanna delete
     *
     * @param cascade
     * if you want to delete all the articles and comments emited by the target user
     *
     * @return
     * @throws SQLException
     */
    private int deleteUser(User target, boolean cascade) throws SQLException {
        if(null == target) {
            GlobalUtils.alert("No user will be delete");
            return -1;
        }

        UserAdmin userAdmin = new UserAdmin();
        int userId = target.getId();

        int userDeleteflag = userAdmin.deleteById(userId);
        if(true == cascade) {
            int articleCascadeFlag = new ArticleAdmin().deleteByUserId(userId);
            int commentCascadeFlag = new CommentAdmin().deleteByUserId(userId);
            if(1 != articleCascadeFlag || 1 != commentCascadeFlag)
                userDeleteflag = -1;
        }
        return userDeleteflag;
    }
}
