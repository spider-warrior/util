package cn.t.util.common.digital;

/**
 * 16进制数工具类
 */
public final class HexUtil {

    private static final char[] hex = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    private HexUtil() {
    }

    public static int hexToInt(char left, char right) {
        int l = CharUtil.hexAsciiCharToInt(left);
        int r = CharUtil.hexAsciiCharToInt(right);
        return (l << 4) | r;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(hex[((b >> 4) & 0xF)]);
            sb.append(hex[(b & 0xF)]);
        }
        return sb.toString();
    }

    public static byte[] stringToBytes(String hex) {

        char[] data = hex.toCharArray();

        final int len = data.length;

        if ((len & 0x01) != 0) {
            throw new IllegalArgumentException("Odd number of characters.");
        }

        final byte[] out = new byte[len >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }

        return out;
    }

    public static String numberToHexString(byte num) throws IllegalAccessException {
        byte units = byteUnits(byte.class);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < units; i++) {
            byte current = (byte) (num >> (8 * (units - 1 - i)));
            sb.append(hex[((current >> 4) & 0xF)]);
            sb.append(hex[(current & 0xF)]);
        }
        return sb.toString();
    }

    public static String numberToHexString(short num) throws IllegalAccessException {
        byte units = byteUnits(short.class);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < units; i++) {
            byte current = (byte) (num >> (8 * (units - 1 - i)));
            sb.append(hex[((current >> 4) & 0xF)]);
            sb.append(hex[(current & 0xF)]);
        }
        return sb.toString();
    }

    public static String numberToHexString(char num) throws IllegalAccessException {
        byte units = byteUnits(char.class);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < units; i++) {
            byte current = (byte) (num >> (8 * (units - 1 - i)));
            sb.append(hex[((current >> 4) & 0xF)]);
            sb.append(hex[(current & 0xF)]);
        }
        return sb.toString();
    }

    public static String numberToHexString(int num) throws IllegalAccessException {
        byte units = byteUnits(int.class);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < units; i++) {
            byte current = (byte) (num >> (8 * (units - 1 - i)));
            sb.append(hex[((current >> 4) & 0xF)]);
            sb.append(hex[(current & 0xF)]);
        }
        return sb.toString();
    }

    public static String numberToHexString(long num) throws IllegalAccessException {
        byte units = byteUnits(long.class);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < units; i++) {
            byte current = (byte) (num >> (8 * (units - 1 - i)));
            sb.append(hex[((current >> 4) & 0xF)]);
            sb.append(hex[(current & 0xF)]);
        }
        return sb.toString();
    }

    private static byte byteUnits(Class<?> numberClass) throws IllegalAccessException {
        if (byte.class == numberClass || Byte.class == numberClass) {
            return 1;
        } else if (short.class == numberClass || Short.class == numberClass) {
            return 2;
        } else if (char.class == numberClass || Character.class == numberClass) {
            return 2;
        } else if (int.class == numberClass || Integer.class == numberClass) {
            return 4;
        } else if (long.class == numberClass || Long.class == numberClass) {
            return 8;
        } else {
            throw new IllegalAccessException("class must be primitive: [byte, short, char, int, long]");
        }
    }

    private static int toDigit(final char ch, final int index) {
        final int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new IllegalArgumentException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }
}
