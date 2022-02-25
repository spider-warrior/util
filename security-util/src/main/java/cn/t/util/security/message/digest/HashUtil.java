package cn.t.util.security.message.digest;


import cn.t.util.common.digital.HexUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    private static final String MD5 = "MD5";
    private static final String SHA1 = "SHA1";

    public static String md5(byte[] bytes) throws NoSuchAlgorithmException {
        return digest(MD5, bytes);
    }

    public static String md5(String src) throws NoSuchAlgorithmException {
        return digest(MD5, src);
    }

    public static String sha1(String src) throws NoSuchAlgorithmException {
        return digest(SHA1, src);
    }

    private static String digest(String d, String src) throws NoSuchAlgorithmException {
        return digest(d, src.getBytes());
    }

    private static String digest(String d, byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(d);
        byte[] encodeBytes = md.digest(bytes);
        return HexUtil.bytesToHex(encodeBytes);
    }

}
