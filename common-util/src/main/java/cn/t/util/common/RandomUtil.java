package cn.t.util.common;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数工具类
 */
public class RandomUtil {

    private static final char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0',
        '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static int randomInt(int min, int max) {
        int value = ThreadLocalRandom.current().nextInt(max);
        if(min >= max) {
            throw new IllegalArgumentException("min is greater than max");
        }
        //不在范围内
        if (value < min) {
            value = min + (min - value) % (max - min);
        } else if (value >= max) {
            value = max - (value - max) % (max - min) - 1;
        }
        return value;
    }

    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            sb.append(chars[threadLocalRandom.nextInt(chars.length)]);
        }
        return sb.toString();
    }


}
