package cn.t.util.common.digital;

import java.nio.ByteOrder;
import java.util.Arrays;

public final class ByteUtil {

    private ByteUtil() {
    }

    /**
     * 字节转换成字符串
     *
     * @param bytes     要转换的字节数组
     * @param separator 分隔符
     * @return e.g.: <code>192.168.1.100</code>
     * @author yangjian
     */
    public static String bytesToPositiveNumber(byte[] bytes, String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            //append element and separator
            stringBuilder.append(b).append(separator);
        }
        //remove the last separator
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    /**
     * 字符串转化数组
     *
     * @param source    要转换的字符串
     * @param separator 分隔符
     * @return e.g.: <code>[192,168,1,100]</code>
     * @author yangjian
     */
    public static byte[] stringsToBytes(String source, String separator) {
        String[] elements = source.split(separator);
        byte[] bytes = new byte[elements.length];
        for (int i = 0; i < elements.length; i++) {
            bytes[i] = Integer.valueOf(elements[i].trim()).byteValue();
        }
        return bytes;
    }

    public static byte[] hexStringToBytes(String source, ByteOrder order) {
        char[] chars = source.trim().toCharArray();
        if (chars.length % 2 != 0) {
            if (ByteOrder.BIG_ENDIAN == order) {
                char[] newChars = new char[chars.length + 1];
                newChars[0] = '0';
                System.arraycopy(chars, 0, newChars, 1, chars.length);
                chars = newChars;
            } else if (ByteOrder.LITTLE_ENDIAN == order) {
                chars = Arrays.copyOf(chars, chars.length + 1);
            } else {
                throw new IllegalArgumentException("ByteOrder cannot be null");
            }
        }
        byte[] result = new byte[chars.length / 2];
        for (int i = 0; i < chars.length; i += 2) {
            int v = HexUtil.hexToInt(chars[i], chars[i + 1]);
            result[i / 2] = (byte) v;
        }
        return result;
    }

    public static void printBytesInline(byte[] bytes, String separator, boolean needReverse) {
        if (needReverse) {
            for (int i = 0; i < bytes.length / 2; i++) {
                byte l = bytes[i];
                bytes[i] = bytes[bytes.length - 1 - i];
                bytes[bytes.length - 1 - i] = l;
            }
        }
        for (byte b : bytes) {
            System.out.print(b & 0xFF);
            System.out.print(separator);
        }
        System.out.println();
    }

    public static void printIntsInline(int[] ints, String separator, boolean needReverse) {
        if (needReverse) {
            for (int i = 0; i < ints.length / 2; i++) {
                int l = ints[i];
                ints[i] = ints[ints.length - 1 - i];
                ints[ints.length - 1 - i] = l;
            }
        }
        for (int i : ints) {
            System.out.print(i & 0xFF);
            System.out.print(separator);
        }
        System.out.println();
    }

    public static void printBytesSimple(byte[] bytes) {
        System.out.println(Arrays.toString(bytes));
    }

    public static int hashCode(byte[] value) {
        int h = 0;
        if (value.length > 0) {
            for (byte b : value) {
                h = 31 * h + b;
            }
        }
        return h;
    }
}
