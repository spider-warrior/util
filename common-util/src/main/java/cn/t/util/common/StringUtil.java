package cn.t.util.common;


/**
 * 字符集问题
 * 1.不存在使用一个字符集转成成另一个字符集的问题，因为String底层使用char[]存储,只在需要操作byte[]时，转换字节有关
 */
public class StringUtil {


    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }


    public static boolean isAllNumeric(String str) {
        char[] chars = str.toCharArray();
        if (chars.length == 0) {
            return false;
        } else {
            for (char c : chars) {
                if (c < 48 || c > 57) {
                    return false;
                }
            }
        }
        return true;
    }


    public static String removeEmoji(String src) {
        if (isEmpty(src)) {
            return src;
        }
        byte[] bytes = src.getBytes();
        StringBuilder sb = new StringBuilder();
        int count;
        for (int i = 0; i < bytes.length; i += count) {
            count = countUTF8CharBytes(bytes[i]);
            if (count < 2) {
                sb.append((char) bytes[i]);
                count = 1;
            } else if (count < 4) {
                sb.append(new String(bytes, i, count));
            } else {
                sb.append("*");
            }
        }
        return sb.toString();
    }


    /**
     * 将emoji字符转成Unicode
     *
     * @param src xxx
     * @return xxx
     */
    public static String emojiToUnicode(String src) {
        StringBuilder unicode = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            int codepoint = src.codePointAt(i);
            if (isEmojiCharacter(codepoint)) {
                unicode.append("\\u").append(Integer.toHexString(c));
            } else {
                unicode.append(c);
            }
        }
        return unicode.toString();
    }

    /**
     * 将Unicode字符转成emoji
     *
     * @param src xxx
     * @return xxx
     */
    public static String unicodeToEmoji(String src) {
        if (isEmpty(src)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        int length = src.length();
        for (int i = 0; i < length; i++) {
            if (src.charAt(i) == '\\') {
                if ((i < length - 5) && ((src.charAt(i + 1) == 'u') || (src.charAt(i + 1) == 'U'))) {
                    try {
                        builder.append((char) Integer.parseInt(src.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        builder.append(src.charAt(i));
                    }
                } else {
                    builder.append(src.charAt(i));
                }
            } else {
                builder.append(src.charAt(i));
            }
        }
        return builder.toString();
    }

    /**
     * 判断是否包含Emoji符号
     *
     * @param codePoint xxx
     * @return xxx
     */
    public static boolean isEmojiCharacter(int codePoint) {
        return (codePoint >= 0x2600 && codePoint <= 0x27BF) // 杂项符号与符号字体
            || codePoint == 0x303D
            || codePoint == 0x2049
            || codePoint == 0x203C
            || (codePoint >= 0x2000 && codePoint <= 0x200F)//
            || (codePoint >= 0x2028 && codePoint <= 0x202F)//
            || codePoint == 0x205F //
            || (codePoint >= 0x2065 && codePoint <= 0x206F)//
            /* 标点符号占用区域 */
            || (codePoint >= 0x2100 && codePoint <= 0x214F)// 字母符号
            || (codePoint >= 0x2300 && codePoint <= 0x23FF)// 各种技术符号
            || (codePoint >= 0x2B00 && codePoint <= 0x2BFF)// 箭头A
            || (codePoint >= 0x2900 && codePoint <= 0x297F)// 箭头B
            || (codePoint >= 0x3200 && codePoint <= 0x32FF)// 中文符号
            || (codePoint >= 0xD800 && codePoint <= 0xDFFF)// 高低位替代符保留区域
            || (codePoint >= 0xE000 && codePoint <= 0xF8FF)// 私有保留区域
            || (codePoint >= 0xFE00 && codePoint <= 0xFE0F)// 变异选择器
            || codePoint >= 0x10000; // Plane在第二平面以上的，char都不可以存，全部都转
    }


    public static byte countUTF8CharBytes(byte b) {
        byte offset = 0;
        while (offset < 8) {
            if (((b >> (7 - offset)) & 1) != 1) {
                break;
            }
            offset += 1;
        }
        return offset;
    }

    public static StringPart partUTF8String(String src) {

        byte[] bytes = src.getBytes();
        StringPart stringPart = new StringPart();
        StringBuilder latinCharactersBuilder = new StringBuilder();
        StringBuilder otherCharactersBuilder = new StringBuilder();

        int cursor = 0;
        while (cursor < bytes.length) {
            byte buff = bytes[cursor];
            byte offset = countUTF8CharBytes(buff);
            byte numBytes = (offset == 0 || offset == 1) ? 1 : offset;
            if (numBytes == 1) {
                latinCharactersBuilder.append((char) bytes[cursor]);
            } else {
                byte[] tmp = new byte[numBytes];
                System.arraycopy(bytes, cursor, tmp, 0, numBytes);
                otherCharactersBuilder.append(new String(tmp));
            }
            cursor += numBytes;
        }
        return stringPart
            .setLatinCharacters(latinCharactersBuilder.toString().toCharArray())
            .setOtherCharacters(otherCharactersBuilder.toString().toCharArray());
    }

    public static class StringPart {
        private char[] latinCharacters;
        private char[] otherCharacters;

        public char[] getLatinCharacters() {
            return latinCharacters;
        }

        private StringPart setLatinCharacters(char[] latinCharacters) {
            this.latinCharacters = latinCharacters;
            return this;
        }

        public char[] getOtherCharacters() {
            return otherCharacters;
        }

        private StringPart setOtherCharacters(char[] otherCharacters) {
            this.otherCharacters = otherCharacters;
            return this;
        }
    }


    public static String snakeToCamel(String para, boolean firstLowerCase) {
        if (isEmpty(para)) {
            return para;
        }
        StringBuilder result = new StringBuilder();
        String[] a = para.split("_");
        for (String s : a) {
            if(!isEmpty(s)) {
                if (result.length() == 0 && firstLowerCase) {
                    result.append(s.toLowerCase());
                } else {
                    result.append(s.substring(0, 1).toUpperCase());
                    result.append(s.substring(1).toLowerCase());
                }
            }
        }
        return result.toString();
    }
}
