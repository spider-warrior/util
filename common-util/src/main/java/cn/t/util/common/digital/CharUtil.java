package cn.t.util.common.digital;

public final class CharUtil {


    private CharUtil() {
    }

    /**
     * 16进制ascii字符转换为10进制int数字
     * @param c xxx
     * @return xxx
     */
    public static int hexAsciiCharToInt(char c) {
        return Character.digit(c, 16);
    }

    /**
     * 10进制ascii字符转换为10进制int数字
     * @param c xxx
     * @return xxx
     */
    public static int decAsciiCharToInt(char c) {
        return Character.digit(c, 10);
    }
}
