package cn.t.util.society;

import cn.t.util.common.digital.CharUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 身份证工具
 */
public class IdCardUtil {

    private static final Logger logger = LoggerFactory.getLogger(IdCardUtil.class);

    private static final byte[] coefficients = {(byte) 7, (byte) 9, (byte) 10, (byte) 5, (byte) 8, (byte) 4, (byte) 2, (byte) 1, (byte) 6, (byte) 3, (byte) 7, (byte) 9, (byte) 10, (byte) 5, (byte) 8, (byte) 4, (byte) 2};
    private static final char[] tail = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    public static boolean isValid(String idCardNum) {
        logger.debug("id card number: {}", idCardNum);
        idCardNum = idCardNum.trim().replace(" ", "");
        char[] chars = idCardNum.toCharArray();
        char checkSum = calculateCheckSum(chars);
        return checkSum == chars[17];
    }

    public static char calculateCheckSum(char[] chars) {
        int[] results = new int[chars.length];
        for (int i = 0; i < 17; i++) {
            results[i] = CharUtil.decAsciiCharToInt(chars[i]) * coefficients[i];
        }
        int sum = Arrays.stream(results).sum();
        logger.debug("sum: {}", sum);
        char checkSum = tail[sum % 11];
        logger.debug("check sum: {}", checkSum);
        return checkSum;
    }
}
