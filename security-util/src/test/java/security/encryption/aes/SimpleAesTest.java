package security.encryption.aes;

import cn.t.util.security.message.AlgorithmName;
import cn.t.util.security.message.AlgorithmUtil;
import cn.t.util.security.message.base64.Base64Util;
import cn.t.util.security.message.encryption.aes.AesUtil;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.security.Key;
import java.util.Base64;

/**
 * Aes
 */
public class SimpleAesTest {

    private static final String src = "this is the original message";

    public static void main(String[] args) {
        jdkAes();
    }

    public static void jdkAes() {
        try {
            //生成Key
            byte[] keyBytes = AlgorithmUtil.generateSecretKeyEncodedBytes(AlgorithmName.AES, 256);
            System.out.println(new String(Base64Util.encode(keyBytes)));
            //Key转换
            Key key = AesUtil.convertSecretKey(keyBytes);
            //加密
            Cipher cipher = AesUtil.getCipher();
            byte[] result = AlgorithmUtil.encrypt(cipher, key, src.getBytes());
            System.out.println("jdk aes encrypt: " + Base64.getEncoder().encodeToString(result));
            System.out.println("hex: " + Hex.encodeHexString(result));
            //解密
            result = AlgorithmUtil.decrypt(cipher, key, result);
            System.out.println("jdk aes decrypt: " + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
