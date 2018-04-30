package com.neu.youdontknow.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DesUtilsTest {
    @Test
    void testInterface() {
        try {
            String test = "hallwood";
            //注意这里，自定义的加密的KEY要和解密的KEY一致，这就是钥匙，如果你上锁了，却忘了钥匙，那么是解密不了的
            DesUtils des = new DesUtils("asdfqo7rg8qe&&*7q9hrg;aiybrlaHJK>lh@abervuo%^#dfa%&*(fawrgaeybfvk)_U:L?ybuk!@WDetde"); //自定义密钥
            System.out.println("加密前的字符：" + test);
            System.out.println("加密后的字符：" + des.encrypt(test));
            System.out.println("解密后的字符：" + des.decrypt(des.encrypt(test)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}