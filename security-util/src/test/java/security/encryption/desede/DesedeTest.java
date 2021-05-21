package security.encryption.desede;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

public class DesedeTest {

    private static final String src = "this is message";

    public static void main(String[] args) {
        jdkDes3();
        bcDes3();
    }


    public static void jdkDes3() {
        try {
            //1.生成Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
//            keyGenerator.init(168);
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //2.Key转换
            DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            secretKeyFactory.generateSecret(desKeySpec);
            Key convertedSecretKey = secretKeyFactory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");//加密方式/工作模式/填充方式
            cipher.init(Cipher.ENCRYPT_MODE, convertedSecretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk 3des encrypt: " + Hex.toHexString(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, convertedSecretKey);
            result = cipher.doFinal(result);
            System.out.println("jdk 3des decrypt: " + new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bcDes3() {
        try {

            //添加provider
            Security.addProvider(new BouncyCastleProvider());

            //1.生成Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede", "BC");
            keyGenerator.getProvider();
//            keyGenerator.init(168);
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //2.Key转换
            DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            secretKeyFactory.generateSecret(desKeySpec);
            Key convertedSecretKey = secretKeyFactory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");//加密方式/工作模式/填充方式
            cipher.init(Cipher.ENCRYPT_MODE, convertedSecretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("bc 3des encrypt: " + Hex.toHexString(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, convertedSecretKey);
            result = cipher.doFinal(result);
            System.out.println("bc 3des decrypt: " + new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

