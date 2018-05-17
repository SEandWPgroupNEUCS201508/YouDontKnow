package com.neu.youdontknow.admin;

import com.neu.youdontknow.models.User;
import com.neu.youdontknow.utils.DataBaseUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.SQLException;

public class UserAdmin {

    public int addUser(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "insert into user(username, password, email) values(?, ?, ?)";
        return queryRunner.update(sql,
                user.getUsername(), user.getPassword(), user.getEmail()
                );
    }

    public int deleteById(int userId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "delete from user where id=?";
        return queryRunner.update(sql, userId);
    }

    public User queryById(int userId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "select * from user where id=?";
        return queryRunner.query(sql, new BeanHandler<>(User.class), userId);
    }

    public User queryByUsername(String userName) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "select * from user where username=?";
        return queryRunner.query(sql, new BeanHandler<>(User.class), userName);
    }

    public int updateById(int userId, User tmp) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "update user set username=?, password=?, email=? where id=?";
        return queryRunner.update(sql,
                tmp.getUsername(), tmp.getPassword(), tmp.getEmail(), userId);
    }
}
