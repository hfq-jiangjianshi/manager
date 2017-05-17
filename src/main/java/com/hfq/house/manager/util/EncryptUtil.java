/*
 * Copyright 2016 uncle5.com All right reserved. This software is the
 * confidential and proprietary information of uncle5.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with uncle5.com .
 */
package com.hfq.house.manager.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author jjs
 *
 */
public class EncryptUtil {

    //md5加密方式
    private static final String ENCRYPT_WAY_MD5 = "MD5";

    private static final char[] hexDigits       = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
            'D', 'E', 'F' };

    public static String md5(String text) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance(EncryptUtil.ENCRYPT_WAY_MD5);
        } catch (NoSuchAlgorithmException e) {
        }

        byte[] bytes = md5.digest(text.getBytes(Charset.forName("utf-8")));
        String ciphertext = EncryptUtil.byteArrayToString(bytes);
        return ciphertext;
    }

    private static String byteArrayToString(byte[] bytes) {
        char[] cs = new char[bytes.length * 2];
        int i = 0;
        for (byte b : bytes) {
            cs[i++] = EncryptUtil.hexDigits[b >>> 4 & 0xf];
            cs[i++] = EncryptUtil.hexDigits[b & 0xf];
        }
        return new String(cs);
    }

//    public static void main(String[] args) {
//        System.out.println(EncryptUtil.md5("wenhua.qiu@liyunqiche.combb123456"));
//    }
}
