package security.digitalsign;

import cn.t.util.security.message.AlgorithmName;
import cn.t.util.security.message.AlgorithmUtil;
import cn.t.util.security.message.digitalsign.dsa.DsaSignUtil;
import org.bouncycastle.util.encoders.Hex;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;

public class DsaSignTest {

    private static final String src = "123456";

    public static void main(String[] args) throws Exception {
        jdkDsa();
    }

    public static void jdkDsa() throws Exception {
        //1.初始化密钥
        KeyPair keyPair = AlgorithmUtil.generateKeyPair(AlgorithmName.DSA, 512);
        DSAPublicKey dsaPublicKey = (DSAPublicKey) keyPair.getPublic();
        DSAPrivateKey dsaPrivateKey = (DSAPrivateKey) keyPair.getPrivate();

        //2.执行签名
        PrivateKey privateKey = DsaSignUtil.convertEncodedBytesToPrivateKey(dsaPrivateKey.getEncoded());
        byte[] result = AlgorithmUtil.signData(AlgorithmName.SHA1_DSA, privateKey, src.getBytes());
        System.out.println("jdk DSA sign: " + Hex.toHexString(result));

        //3.验证签名
        PublicKey publicKey = DsaSignUtil.convertEncodedBytesToPublicKey(dsaPublicKey.getEncoded());
        boolean success = AlgorithmUtil.verifySign(AlgorithmName.SHA1_DSA, publicKey, src.getBytes(), result);
        System.out.println("verify result: " + success);

    }
}
