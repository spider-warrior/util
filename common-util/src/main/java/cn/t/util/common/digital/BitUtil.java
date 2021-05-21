package cn.t.util.common.digital;

/**
 *
 */
public final class BitUtil {

    private BitUtil() {
    }

    public static byte[] toUnsignedBitArray(byte b) {
        return toBitArray(b, false);
    }

    public static byte[] toSignedBitArray(byte b) {
        return toBitArray(b, true);
    }

    private static byte[] toBitArray(byte b, boolean signed) {
        int sign = signed ? -1 : 1;
        byte[] array = new byte[8];
        array[0] = (byte) (b >> 7 & sign);
        for (byte i = 1; i < 8; i++) {
            array[i] = (byte) (b >> (7 - i) & 1);
        }
        return array;
    }

    public static String toUnsignedBitStr(byte b) {
        return toBitStr(b, false);
    }

    public static String toSignedBitStr(byte b) {
        return toBitStr(b, true);
    }

    private static String toBitStr(byte b, boolean signed) {
        int sign = signed ? -1 : 1;
        StringBuilder sb = new StringBuilder();
        sb.append(b >> 7 & sign);
        for (byte i = 1; i < 8; i++) {
            sb.append((b >> (7 - i) & 1));
        }
        return sb.toString();
    }

    public static byte toByte(String bits) {
        return Byte.valueOf(bits, 2);
    }
}
