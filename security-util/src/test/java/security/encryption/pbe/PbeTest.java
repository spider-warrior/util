package security.encryption.pbe;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * PBE
 */
public class PbeTest {

    private static final String src = "imooc security pbe";

    public static void main(String[] args) {
        jdkPbe();
    }

    public static void jdkPbe() {
        try {
            //初始化盐
            SecureRandom secureRandom = new SecureRandom();
            //8位
            byte[] salt = secureRandom.generateSeed(8);

            //口令与密钥
            String password = "imooc";
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            Key key = secretKeyFactory.generateSecret(pbeKeySpec);

            //加密
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk pbe encrypt: " + Base64.getEncoder().encodeToString(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec);
            result = cipher.doFinal(result);
            System.out.println("jdk pbe decrypt: " + new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
