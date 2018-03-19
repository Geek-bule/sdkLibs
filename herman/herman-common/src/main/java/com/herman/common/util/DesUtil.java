package com.herman.common.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * DES机密
 * Created by herman on 2018/01/25.
 */
public class DesUtil {

    /**
     * 加密密钥，长度必须为8位，任意字符
     */
    public static String KEY = "herman12";

    /**
     * 解密字符串
     * @param message
     * @param urlDecodeFlag
     * @return
     */
    public static String decrypt(String message, boolean urlDecodeFlag) {
        String rtnStr = "";
        try {
            if (null != message && !"".equals(message)) {
                if (urlDecodeFlag) {
                    rtnStr = URLDecoder.decode(message, "utf-8");
                }
                byte[] bytesrc = convertHexString(rtnStr);
                Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
                DESKeySpec desKeySpec = new DESKeySpec(KEY.getBytes("UTF-8"));
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
                IvParameterSpec iv = new IvParameterSpec(KEY.getBytes("UTF-8"));
                cipher.init(2, secretKey, iv);
                byte[] retByte = cipher.doFinal(bytesrc);
                rtnStr = new String(retByte,"utf-8");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rtnStr;
    }

    /**
     * 先对给定的字符串进行url解码，再解密字符串
     * @param message
     * @return
     */
    public static String decrypt(String message) {
        return decrypt(message, true);
    }

    /**
     * 对给定的字符串进行加密
     * @param message
     * @param urlEncodeFlag
     * @return
     */
    public static String encrypt(String message, boolean urlEncodeFlag) {
        String rtnStr = null;
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(KEY.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(KEY.getBytes("UTF-8"));
            cipher.init(1, secretKey, iv);
            rtnStr = toHexString(cipher.doFinal(message.getBytes("UTF-8")));
            if (urlEncodeFlag) {
                rtnStr = URLEncoder.encode(rtnStr, "utf-8");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rtnStr;
    }

    /**
     * 对给定的字符串进行加密,并对加密后的字符串进行URLEncode
     * @param message
     * @return
     */
    public static String encrypt(String message) {
        return encrypt(message, true);
    }

    /**
     * 转十六进制
     * @param s
     * @return
     */
    private static byte[] convertHexString(String s) {
        byte[] digest = new byte[s.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = s.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = ((byte) byteValue);
        }
        return digest;
    }

    /**
     * 转十六进制
     * @param b
     * @return
     */
    private static String toHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xFF & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }
        return hexString.toString();
    }
}
