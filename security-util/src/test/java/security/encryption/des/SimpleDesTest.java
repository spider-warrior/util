package security.encryption.des;

import cn.t.util.security.message.AlgorithmName;
import cn.t.util.security.message.AlgorithmProviderName;
import cn.t.util.security.message.AlgorithmUtil;
import cn.t.util.security.message.encryption.des.DesUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.Security;

/**
 * DES
 */
public class SimpleDesTest {

    private static final String src = "this is message";

    public static void main(String[] args) {
        jdkDes();
        bcDes();
    }

    public static void jdkDes() {
        try {
            //1.生成Key
            byte[] keyBytes = AlgorithmUtil.generateSecretKeyEncodedBytes(AlgorithmName.DES, 56);
            //2.Key转换
            Key convertedSecretKey = DesUtil.convertSecretKey(keyBytes);
            //3.加密
            Cipher cipher = DesUtil.getCipher();
            byte[] result = AlgorithmUtil.encrypt(cipher, convertedSecretKey, src.getBytes());
            System.out.println("jdk des encrypt: " + Hex.toHexString(result));
            //4.解密
            result = AlgorithmUtil.decrypt(cipher, convertedSecretKey, result);
            System.out.println("jdk des decrypt: " + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bcDes() {
        try {
            //添加provider
            Security.addProvider(new BouncyCastleProvider());
            //1.生成Key
            KeyGenerator keyGenerator = AlgorithmUtil.generateKeyGenerator(AlgorithmName.DES, 56, AlgorithmProviderName.BC);
            keyGenerator.getProvider();
            byte[] keyBytes = keyGenerator.generateKey().getEncoded();
            //2.Key转换
            Key convertedSecretKey = DesUtil.convertSecretKey(keyBytes);
            //3.加密
            Cipher cipher = DesUtil.getCipher();
            byte[] result = AlgorithmUtil.encrypt(cipher, convertedSecretKey, src.getBytes());
            System.out.println("bc des encrypt: " + Hex.toHexString(result));
            //4.解密
            result = AlgorithmUtil.decrypt(cipher, convertedSecretKey, result);
            System.out.println("bc des decrypt: " + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
