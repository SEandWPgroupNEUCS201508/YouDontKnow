package com.neu.youdontknow.service;

import com.neu.youdontknow.models.Model;

import java.sql.SQLException;

public interface Service {
    public int publish(Model tmp) throws SQLException;
    public int delete(Model target, boolean cascade) throws SQLException;
//    public int query();
    public int update(int id, Model tmp) throws SQLException;
}
