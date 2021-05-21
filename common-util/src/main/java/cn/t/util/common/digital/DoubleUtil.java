package cn.t.util.common.digital;

public class DoubleUtil {

    public static byte[] doubleToBytes(double d) {
        long value = Double.doubleToRawLongBits(d);
        byte[] byteRet = new byte[8];
        for (int i = 0; i < 8; i++) {
            byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
        }
        return byteRet;
    }

    public static double bytesToDouble(byte[] arr) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value |= ((long) (arr[i] & 0xff)) << (8 * i);
        }
        return Double.longBitsToDouble(value);
    }

    public static String  doubleToHexString(double d) {
        return Long.toHexString(Double.doubleToLongBits(d));
    }

    public static double hexStringToDouble(String hexString) {
        return Double.longBitsToDouble(Long.parseLong(hexString));
    }
}
