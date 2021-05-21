package cn.t.util.common.digital;

import java.nio.ByteOrder;

/**
 * xxx
 */
public final class IntUtil {


    private static final int FF = 0xFF;


    private IntUtil() {
    }

    public static byte[] intToBytes(int value, ByteOrder order) {
        byte[] bytes = new byte[4];
        //大端
        if (ByteOrder.BIG_ENDIAN == order) {
            for (byte i = 0; i < 4; i++) {
                bytes[i] = (byte) (value >> (24 - (i * 8)));
            }
        } else {
            //小端
            for (byte i = 0; i < 4; i++) {
                bytes[i] = (byte) (value >> (i * 8));
            }
        }
        return bytes;
    }

    public static int[] intToPositiveBytes(int value, ByteOrder order) {
        int[] ints = new int[4];
        //大端
        if (ByteOrder.BIG_ENDIAN == order) {
            for (byte i = 0; i < 4; i++) {
                ints[i] = value >> (24 - (i * 8)) & FF;
            }
        } else {
            //小端
            for (byte i = 0; i < 4; i++) {
                ints[i] = value >> (i * 8) & FF;
            }
        }
        return ints;
    }

    public static int bytesToInt(byte[] source) {
        return bytesToInt(source, 0, ByteOrder.BIG_ENDIAN);
    }

    public static int bytesToInt(byte[] source, int offset, ByteOrder order) {
        int value;
        //大端
        if (ByteOrder.BIG_ENDIAN == order) {
            value = ((source[offset] & 0xFF) << 24)
                | ((source[offset + 1] & 0xFF) << 16)
                | ((source[offset + 2] & 0xFF) << 8)
                | (source[offset + 3] & 0xFF);
        } else {
            //小端
            value = (source[offset] & 0xFF)
                | ((source[offset + 1] & 0xFF) << 8)
                | ((source[offset + 2] & 0xFF) << 16)
                | ((source[offset + 3] & 0xFF) << 24);
        }
        return value;
    }

    /**
     * 字符串转化数组
     *
     * @param source    要转换的字符串
     * @param separator 分隔符
     * @return e.g.: <code>[192,168,1,100]</code>
     * @author yangjian
     */
    public static int[] stringsToInts(String source, String separator) {
        String[] elements = source.split(separator);
        int[] ints = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            ints[i] = Integer.parseInt(elements[i].trim());
        }
        return ints;
    }
}
