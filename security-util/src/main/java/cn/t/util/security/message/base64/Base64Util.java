package cn.t.util.security.message.base64;

import java.util.Base64;

public class Base64Util {

    public static byte[] encode(byte[] bs) {
        return Base64.getEncoder().encode(bs);
    }

    public static byte[] decode(byte[] bs) {
        return Base64.getDecoder().decode(bs);
    }
}
