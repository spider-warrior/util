package security.encryption.elgamal;

import cn.t.util.security.message.AlgorithmName;
import cn.t.util.security.message.AlgorithmUtil;
import cn.t.util.security.message.encryption.elgamal.ElGamalUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

/**
 * ElGamL算法测试
 */
public class SimpleElGamalTest {

    private static final String src = "1234567890";

    public static void main(String[] args) {
        bcElGamal();
    }

    public static void bcElGamal() {
        //公钥加密,私钥解密
        Security.addProvider(new BouncyCastleProvider());
        try {
            //1.初始化密钥(接收者创建密钥对,发送公钥给发送者)
            AlgorithmParameterSpec dhParameterSpec = AlgorithmUtil.generateAlgorithmParameterSpec(AlgorithmName.EL_GAMAL, 256, DHParameterSpec.class);
            KeyPair keyPair = AlgorithmUtil.generateKeyPair(AlgorithmName.EL_GAMAL, dhParameterSpec, new SecureRandom());
            PublicKey elGamalPublicKey = keyPair.getPublic();
            PrivateKey elGamalPrivateKey = keyPair.getPrivate();
            System.out.println("Public Key: " + Base64.getEncoder().encodeToString(elGamalPublicKey.getEncoded()));
            System.out.println("Private Key: " + Base64.getEncoder().encodeToString(elGamalPrivateKey.getEncoded()));
            //2.公钥加密私钥解密 --加密
            PublicKey publicKey = ElGamalUtil.convertEncodedBytesToPublicKey(elGamalPublicKey.getEncoded());
            Cipher cipher = ElGamalUtil.getCipher();
            byte[] result = AlgorithmUtil.encrypt(cipher, publicKey, src.getBytes());
            System.out.println("公钥加密私钥解密 --加密: " + Base64.getEncoder().encodeToString(result));
            //3.公钥加密私钥解密 --解密
            PrivateKey privateKey = ElGamalUtil.convertEncodedBytesToPrivateKey(elGamalPrivateKey.getEncoded());
            cipher = ElGamalUtil.getCipher();
            result = AlgorithmUtil.decrypt(cipher, privateKey, result);
            System.out.println("公钥加密私钥解密 --解密: " + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
