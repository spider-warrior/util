package cn.t.util.common.digital;

public class FloatUtil {

    public static byte[] floatToBytes(float f) {
        long value = Float.floatToRawIntBits(f);
        byte[] byteRet = new byte[8];
        for (int i = 0; i < 8; i++) {
            byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
        }
        return byteRet;
    }

    public static float bytesToFloat(byte[] arr) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            value |= ((long) (arr[i] & 0xff)) << (8 * i);
        }
        return Float.intBitsToFloat(value);
    }

}
