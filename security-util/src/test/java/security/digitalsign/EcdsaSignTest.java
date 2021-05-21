package security.digitalsign;

import cn.t.util.security.message.AlgorithmName;
import cn.t.util.security.message.AlgorithmUtil;
import cn.t.util.security.message.digitalsign.ecdsa.EcdsaSignUtil;
import org.bouncycastle.util.encoders.Hex;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

public class EcdsaSignTest {

    private static final String src = "123456";

    public static void main(String[] args) throws Exception {
        jdkEcdsa();
    }

    public static void jdkEcdsa() throws Exception {
        //1.初始化密钥
        KeyPair keyPair = AlgorithmUtil.generateKeyPair(AlgorithmName.EC, 256);
        ECPublicKey ecPublicKey = (ECPublicKey) keyPair.getPublic();
        ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair.getPrivate();
        //2.执行签名
        PrivateKey privateKey = EcdsaSignUtil.convertEncodedBytesToPrivateKey(ecPrivateKey.getEncoded());
        byte[] result = AlgorithmUtil.signData(AlgorithmName.SHA1_ECDSA, privateKey, src.getBytes());
        System.out.println("jdk EC sign: " + Hex.toHexString(result));
        //3.验证签名
        PublicKey publicKey = EcdsaSignUtil.convertEncodedBytesToPublicKey(ecPublicKey.getEncoded());
        boolean success = AlgorithmUtil.verifySign(AlgorithmName.SHA1_ECDSA, publicKey, src.getBytes(), result);
        System.out.println("verify result: " + success);

    }
}
