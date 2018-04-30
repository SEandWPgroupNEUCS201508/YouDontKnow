package com.neu.youdontknow.utils;

import org.junit.Test;

import javax.crypto.Cipher;
import java.security.Key;

public class DesUtils {
    /**
     *  static members
     *
     *  default key
     *
     */
    private static String defaultKey;

    static {
        defaultKey = "na_me_wen_ti_lai_le";
    }

    /**
     *  objects' members
     */

    // encryptor
    private Cipher encryptCipher = null;
    // decryptor
    private Cipher decryptCipher = null;


    /**
     * Util constructor
     * @param secretKey
     */
    public DesUtils(String secretKey) {
        Key key;
        try {
            key = getKey(secretKey.getBytes());
            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        } catch(Exception e) {
            GlobalUtils.alert("init the des utils failed");
            e.printStackTrace();
        }
    }

    // default constructor
    public DesUtils() throws Exception {
        this(defaultKey);
    }

    /**
     * transform the hex string to arr of byte
     *
     * @param strIn
     * @return arr of bytes
     */
    public static byte[] hexStr2ByteArr(String strIn) {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        byte[] arrOut = new byte[iLen/2];
        for(int i = 0; i < iLen; i += 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i/2] = (byte)Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }


    /**
     * transfrom the bytes arr to the hex string
     *
     * @param arrB
     * @return java.lang.String
     * @throws Exception
     */
    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * 加密 (逻辑: 1. 将要加密的字符串转换为字节数组(byte array)
     *            2. 将第一步的字节数组作为输入使用加密器(Cipher)的doFinal方法进行加密, 返回字节数组
     *            3. 把加密后的字节数组转换成十六进制的字符串)
     * @param strIn 要加密的字符串
     * @return 返回加密后的十六进制字符串
     * @throws Exception
     */
    public String encrypt(String strIn) throws Exception {
        return byteArr2HexStr(encrypt(strIn.getBytes()));
    }

    public byte[] encrypt(byte[] arrB) throws Exception {
        return encryptCipher.doFinal(arrB);
    }

    /**
     * 解密 (逻辑: 1. 把加密后的十六进制字符串转换成字节数组(byte array)
     *            2. 将第一步的字节数组作为输入使用加密器(Cipher)的doFinal方法进行解密, 返回字节数组(byte array)
     *            3. 把解密后的字节数组转换成字符串)
     * @param strIn
     * @return
     * @throws Exception
     */
    public String decrypt(String strIn) throws Exception {
        return new String(decrypt(hexStr2ByteArr(strIn)));
    }

    public byte[] decrypt(byte[] arrB) throws Exception {
        return decryptCipher.doFinal(arrB);
    }

    private Key getKey(byte[] arrBTmp) throws Exception {
        // init a byte arr
        byte[] arrB = new byte[8];
        // trans the origin Bytes to 8 bits
        for(int i = 0; i < arrBTmp.length && i < arrB.length; ++i) {
            arrB[i] = arrBTmp[i];
        }
        // generate the key
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
        return key;
    }

    public void testInterface() {
        return ;
    }
}
