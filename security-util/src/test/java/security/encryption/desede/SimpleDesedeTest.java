package security.encryption.desede;

import cn.t.util.security.message.AlgorithmName;
import cn.t.util.security.message.AlgorithmProviderName;
import cn.t.util.security.message.AlgorithmUtil;
import cn.t.util.security.message.encryption.des3.DesedeUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.Security;

public class SimpleDesedeTest {

    private static final String src = "this is message";

    public static void main(String[] args) {
        jdkDes3();
        bcDes3();
    }


    public static void jdkDes3() {
        try {
            //1.生成Key
            byte[] keyBytes = AlgorithmUtil.generateSecretKeyEncodedBytes(AlgorithmName.DESEDE, 168);
            //2.Key转换
            Key convertedSecretKey = DesedeUtil.convertSecretKey(keyBytes);
            //3.加密
            Cipher cipher = DesedeUtil.getCipher();
            byte[] result = AlgorithmUtil.encrypt(cipher, convertedSecretKey, src.getBytes());
            System.out.println("jdk 3des encrypt: " + Hex.toHexString(result));
            //4.解密
            result = AlgorithmUtil.decrypt(cipher, convertedSecretKey, result);
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
            KeyGenerator keyGenerator = AlgorithmUtil.generateKeyGenerator(AlgorithmName.DESEDE, 168, AlgorithmProviderName.BC);
            keyGenerator.getProvider();
            byte[] keyBytes = keyGenerator.generateKey().getEncoded();
            //2.Key转换
            Key convertedSecretKey = DesedeUtil.convertSecretKey(keyBytes);
            //3.加密
            Cipher cipher = DesedeUtil.getCipher();
            byte[] result = AlgorithmUtil.encrypt(cipher, convertedSecretKey, src.getBytes());
            System.out.println("bc 3des encrypt: " + Hex.toHexString(result));
            //4.解密
            result = AlgorithmUtil.decrypt(cipher, convertedSecretKey, result);
            System.out.println("bc 3des decrypt: " + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

