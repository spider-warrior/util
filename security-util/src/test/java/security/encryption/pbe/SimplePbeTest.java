package security.encryption.pbe;

import cn.t.util.security.message.AlgorithmUtil;
import cn.t.util.security.message.encryption.pbe.PbeUtil;

import javax.crypto.Cipher;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.util.Base64;

/**
 * PBE
 */
public class SimplePbeTest {

    private static final String src = "imooc security pbe";

    public static void main(String[] args) {
        jdkPbe();
    }

    public static void jdkPbe() {
        try {
            //初始化盐
            byte[] salt = AlgorithmUtil.generateSeedBytes(8);
            //口令与密钥
            String password = "imooc";
            Key key = PbeUtil.generateSecretKey(password.toCharArray());
            //加密
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
            Cipher cipher = PbeUtil.generateCipher();
            byte[] result = AlgorithmUtil.encrypt(cipher, key, pbeParameterSpec, src.getBytes());
            System.out.println("jdk pbe encrypt: " + Base64.getEncoder().encodeToString(result));
            //解密
            result = AlgorithmUtil.decrypt(cipher, key, pbeParameterSpec, result);
            System.out.println("jdk pbe decrypt: " + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
