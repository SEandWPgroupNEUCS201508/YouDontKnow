package com.neu.youdontknow.admin;

import com.neu.youdontknow.models.User;
import com.neu.youdontknow.utils.DataBaseUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserAdmin {

    public int addUser(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "insert into user(username, password, email) values(?, ?, ?)";
        return queryRunner.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }

    public int deleteById(int userId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "delete from user where id=?";
        return queryRunner.update(sql, userId);
    }

    public List<User> queryById(int userId) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "select * from user where id=?";
        return queryRunner.query(sql, new BeanListHandler<>(User.class), userId);
    }

    public List<User> queryByUsername(String userName) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "select * from user where username=?";
        return queryRunner.query(sql, new BeanListHandler<>(User.class), userName);
    }

    public int updateById(int userId, User tmp) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataBaseUtils.getDataSource());
        String sql = "update user set username=?, password=?, email=? where id=?";
        return queryRunner.update(sql,
                tmp.getUsername(), tmp.getPassword(), tmp.getEmail(), userId);
    }
}
