package security.encryption.dh;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

/**
 * DH算法测试
 * 1.接受方利用发送方的公钥构建自己的密钥对
 * 2.本地密钥构建方式: 自己密钥 + 对方公钥
 */
public class DhTest {

    private static final String src = "security dh";

    public static void main(String[] args) {
        jdkDh();
    }

    public static void jdkDh() {
        try {
            //1.初始化发送发密钥
            KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance("DH");
            senderKeyPairGenerator.initialize(512);
            KeyPair senderKeyPair = senderKeyPairGenerator.generateKeyPair();
            PrivateKey senderPrivateKey = senderKeyPair.getPrivate();
            byte[] senderPublicKeyEnc = senderKeyPair.getPublic().getEncoded();//发送方公钥,发给接受方(网络, 文件...)

            //2.初始化接受方密钥
            KeyFactory receiverKeyFactory = KeyFactory.getInstance("DH");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(senderPublicKeyEnc);
            //receiverPublicKey.equals(senderKeyPair.getPublic()) 为 true
            PublicKey receiverPublicKey = receiverKeyFactory.generatePublic(x509EncodedKeySpec);
            DHParameterSpec dhParameterSpec = ((DHPublicKey) receiverPublicKey).getParams();
            KeyPairGenerator receiverKeyPairGenerator = KeyPairGenerator.getInstance("DH");
            //使用发送方公钥信息构建自己的密钥对生成器
            receiverKeyPairGenerator.initialize(dhParameterSpec);
            KeyPair receiverKeyPair = receiverKeyPairGenerator.generateKeyPair();
            PrivateKey receiverPrivateKey = receiverKeyPair.getPrivate();
            byte[] receiverPublicKeyEnc = receiverKeyPair.getPublic().getEncoded();

            //3.密钥构建

            //构建接收方key agreement,设置私钥(接受方),公钥(发送方)
            KeyAgreement receiverKeyAgreement = KeyAgreement.getInstance("DH");
            receiverKeyAgreement.init(receiverPrivateKey);
            receiverKeyAgreement.doPhase(receiverPublicKey, true);
            //接收方本地密钥
            SecretKey receiverDesKey = receiverKeyAgreement.generateSecret("DES");

            //发送方接收公钥
            KeyFactory senderKeyFactory = KeyFactory.getInstance("DH");
            x509EncodedKeySpec = new X509EncodedKeySpec(receiverPublicKeyEnc);
            PublicKey senderPublicKey = senderKeyFactory.generatePublic(x509EncodedKeySpec);

            //构建发送方key agreement, 设置私钥(发送方),公钥(接受方) 
            KeyAgreement senderKeyAgreement = KeyAgreement.getInstance("DH");
            senderKeyAgreement.init(senderPrivateKey);
            senderKeyAgreement.doPhase(senderPublicKey, true);
            //发送方本地密钥
            SecretKey senderDesKey = senderKeyAgreement.generateSecret("DES");

            if (Objects.equals(receiverDesKey, senderDesKey)) {
                System.out.println("keys: " + Arrays.toString(receiverDesKey.getEncoded()));
                System.out.println("双方密钥相同");
            }

            //4.加密
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, senderDesKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk dh encrypt: " + Base64.getEncoder().encodeToString(result));

            //5.解密
            cipher.init(Cipher.DECRYPT_MODE, receiverDesKey);
            result = cipher.doFinal(result);
            System.out.println("jdk dh decrypt: " + new String(result));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
