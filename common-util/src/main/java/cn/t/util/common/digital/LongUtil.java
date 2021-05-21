package cn.t.util.common.digital;

/**
 * 大端地位占高字节
 */
public class LongUtil {

    public static byte[] longToBytes(long number) {
        byte[] bytes = new byte[8];
        for (byte i = 0; i < 8; i++) {
            bytes[7 - i] = (byte) (number >> (8 * i));
        }
        return bytes;
    }

    public static long bytesToLong(byte[] bytes) {
        long num = 0;
        for (byte i = 0; i < 8 && i < bytes.length; i++) {
            num |= (((long) bytes[i]) << (8 * (7 - i)));
        }
        return num;
    }

}
