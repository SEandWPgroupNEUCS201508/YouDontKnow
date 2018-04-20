package com.neu.youdontknow.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBaseUtils {
    private static ComboPooledDataSource dataSource;

    static {
        try {
            dataSource = new ComboPooledDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  get connection from database
     *  @return conn
     */
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch(SQLException e) {
            System.out.println("Error! Unable to get connection");
            e.printStackTrace();
        }
    }

    /**
     *  getDataSource
     *  @return dataSource
     */
    public static ComboPooledDataSource getDataSource() {
        return dataSource;
    }

    /**
     *  release the resource
     *
     *  @param conn
     *              The connection to the database
     *  @param st
     *              The statement await executions
     *  @param rs
     *              The resultset of sql
     *
     */
    public static void releaseResource(Connection conn, Statement st, ResultSet rs) {
        try {
            closeStatement(st);
            closeResultSet(rs);
            closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection(Connection conn) throws SQLException {
        if(conn != null) {
            conn.close();
            conn = null;
        }
    }

    private static void closeStatement(Statement st) throws SQLException {
        if(st != null) {
            st.close();
            st = null;
        }
    }

    private static void closeResultSet(ResultSet rs) throws SQLException {
        if(rs != null) {
            rs.close();
            rs = null;
        }

    }
}
