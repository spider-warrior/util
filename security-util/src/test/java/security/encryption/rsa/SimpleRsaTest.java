package security.encryption.rsa;

import cn.t.util.security.message.AlgorithmName;
import cn.t.util.security.message.AlgorithmUtil;
import cn.t.util.security.message.encryption.rsa.RsaUtil;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/**
 * DH算法测试
 */
public class SimpleRsaTest {

    private static final String src = "this is the content";

    public static void main(String[] args) {
        jdkRsa();
    }

    public static void jdkRsa() {
        try {
            //1.初始化密钥
            KeyPair keyPair = AlgorithmUtil.generateKeyPair(AlgorithmName.RSA, 512);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            System.out.println("Public Key: " + Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded()));
            System.out.println("Private Key: " + Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded()));

            //2.私钥加密公钥解密 --加密
            PrivateKey privateKey = RsaUtil.convertEncodedBytesToPrivateKey(rsaPrivateKey.getEncoded());
            Cipher cipher = RsaUtil.generateCipher();
            byte[] result = AlgorithmUtil.encrypt(cipher, privateKey, src.getBytes());
            System.out.println("私钥加密公钥解密 --加密: " + Base64.getEncoder().encodeToString(result));

            //3.私钥加密公钥解密 --解密
            PublicKey publicKey = RsaUtil.convertEncodedBytesToPublicKey(rsaPublicKey.getEncoded());
            result = AlgorithmUtil.decrypt(cipher, publicKey, result);
            System.out.println("私钥加密公钥解密 --解密: " + new String(result));

            //4.公钥加密私钥解密 --加密
            publicKey = RsaUtil.convertEncodedBytesToPublicKey(rsaPublicKey.getEncoded());
            result = AlgorithmUtil.encrypt(cipher, publicKey, src.getBytes());
            System.out.println("公钥加密私钥解密 --加密: " + Base64.getEncoder().encodeToString(result));

            //5.公钥加密私钥解密 --解密
            privateKey = RsaUtil.convertEncodedBytesToPrivateKey(rsaPrivateKey.getEncoded());
            result = AlgorithmUtil.decrypt(cipher, privateKey, result);
            System.out.println("公钥加密私钥解密 --解密: " + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
