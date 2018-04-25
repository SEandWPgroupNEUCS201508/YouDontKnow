package com.neu.youdontknow.utils;

import java.sql.SQLException;

public class GlobalUtils {

    public static void raiseSQLError(String msg) throws SQLException {
        System.out.println(msg);
        throw new SQLException(msg);
    }

    public static void alert(String msg) {
        System.out.println(msg);
        // TODO
        // SEND THE MSG TO THE FRONTEND
    }

    /**
     * TODO:
     *
     * The function is the password checker
     *
     * @param src
     * @param dest
     * @return
     */
    public static boolean checkPassword(String src, String dest) {
        return src.equals(dest);
    }

    public static String encryptPassword(String password) {
        // TODO
        // encrypt password
        return password;
    }

}
