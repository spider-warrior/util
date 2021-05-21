package cn.t.util.security.message.padding;

import java.util.Arrays;

public class PkcsUtil {

    /**
     * pkcs5 padding
     * @param keyBytes xxx
     * @return xxx
     */
    public static byte[] pkcs5Padding(byte[] keyBytes) {
        int length = keyBytes.length;
        int tailLength = length % 16;
        if (tailLength > 0) {
            int missing = 16 - tailLength;
            keyBytes = Arrays.copyOf(keyBytes, length + missing);
        }
        return keyBytes;
    }
}
