package security.encryption.aes;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

/**
 * Aes
 */
public class AesTest {

    private static final String src = "this is the original message";

    public static void main(String[] args) {
        jdkAes();
    }

    public static void jdkAes() {
        try {
            //生成Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
//            keyGenerator.init(new SecureRandom());//实现默认长度初始化
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            //Key转换
            Key key = new SecretKeySpec(keyBytes, "AES");

            //加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(src.getBytes());

            System.out.println("jdk aes encrypt: " + Base64.getEncoder().encodeToString(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(result);
            System.out.println("jdk aes decrypt: " + new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
