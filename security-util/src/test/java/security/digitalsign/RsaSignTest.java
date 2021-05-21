package security.digitalsign;

import cn.t.util.security.message.AlgorithmName;
import cn.t.util.security.message.AlgorithmUtil;
import cn.t.util.security.message.encryption.rsa.RsaUtil;
import org.bouncycastle.util.encoders.Hex;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RsaSignTest {

    private static final String src = "123456";

    public static void main(String[] args) throws Exception {
        jdkRSA();
    }

    public static void jdkRSA() throws Exception {
        //1.初始化密钥
        KeyPair keyPair = AlgorithmUtil.generateKeyPair(AlgorithmName.RSA, 512);
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        //2.执行签名
        PrivateKey privateKey = RsaUtil.convertEncodedBytesToPrivateKey(rsaPrivateKey.getEncoded());
        byte[] result = AlgorithmUtil.signData(AlgorithmName.MD5_RSA, privateKey, src.getBytes());
        System.out.println("jdk RSA sign: " + Hex.toHexString(result));

        //3.验证签名
        PublicKey publicKey = RsaUtil.convertEncodedBytesToPublicKey(rsaPublicKey.getEncoded());
        boolean success = AlgorithmUtil.verifySign(AlgorithmName.MD5_RSA, publicKey, src.getBytes(), result);
        System.out.println("verify result: " + success);
    }
}
