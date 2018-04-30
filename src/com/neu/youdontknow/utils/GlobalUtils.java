package com.neu.youdontknow.utils;

import java.security.Key;
import java.sql.SQLException;

public class GlobalUtils {

    private static DesUtils desUtils;
    private static String secretKey;

    static {
        secretKey = "simple_test";
        desUtils = new DesUtils(secretKey);
    }

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
     * The function is the password checker
     * dest is hex string which was encrypted in database
     *
     * @param src
     * @param dest
     * @return if the encrypted src password is same as the dest hex string
     */
    public static boolean checkPassword(String src, String dest) {
        try {
            boolean tag = desUtils.encrypt(src).equals(dest);
            return tag;
        } catch(Exception e) {
            alert("check password err!");
            e.printStackTrace();
        }
        return false;
    }

    public static String encryptPassword(String password) {
        // encrypt password
        try {
            String res = desUtils.encrypt(password);
            return res;
        } catch (Exception e) {
            alert("encrypt password err!");
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptPassword(String hexPassword) {
        try {
          String origin = desUtils.decrypt(hexPassword);
          return origin;
        } catch (Exception e) {
            alert("decrypt password err!");
            e.printStackTrace();
        }
        return null;
    }
}
