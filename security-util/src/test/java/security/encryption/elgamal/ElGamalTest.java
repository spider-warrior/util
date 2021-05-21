package security.encryption.elgamal;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * ElGamL算法测试
 */
public class ElGamalTest {

    private static final String src = "1234567890";

    public static void main(String[] args) {
        bcElGamal();
    }

    public static void bcElGamal() {
        //公钥加密,私钥解密
        Security.addProvider(new BouncyCastleProvider());

        try {
            //1.初始化密钥(接收者创建密钥对,发送公钥给发送者)
            AlgorithmParameterGenerator algorithmParameterGenerator = AlgorithmParameterGenerator.getInstance("ElGamal");
            algorithmParameterGenerator.init(256);
            AlgorithmParameters algorithmParameters = algorithmParameterGenerator.generateParameters();
            DHParameterSpec dhParameterSpec = algorithmParameters.getParameterSpec(DHParameterSpec.class);
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ElGamal");
            keyPairGenerator.initialize(dhParameterSpec, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey elGamalPublicKey = keyPair.getPublic();
            PrivateKey elGamalPrivateKey = keyPair.getPrivate();
            System.out.println("Public Key: " + Base64.getEncoder().encodeToString(elGamalPublicKey.getEncoded()));
            System.out.println("Private Key: " + Base64.getEncoder().encodeToString(elGamalPrivateKey.getEncoded()));

            //2.公钥加密私钥解密 --加密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(elGamalPublicKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("ElGamal");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("ElGamal");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("公钥加密私钥解密 --加密: " + Base64.getEncoder().encodeToString(result));

            //3.公钥加密私钥解密 --解密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(elGamalPrivateKey.getEncoded());
            keyFactory = KeyFactory.getInstance("ElGamal");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher = Cipher.getInstance("ElGamal");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result = cipher.doFinal(result);
            System.out.println("公钥加密私钥解密 --解密: " + new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
