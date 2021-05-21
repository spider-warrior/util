package security.encryption.dh;

import cn.t.util.security.message.AlgorithmName;
import cn.t.util.security.message.AlgorithmUtil;
import cn.t.util.security.message.encryption.des.DesUtil;
import cn.t.util.security.message.encryption.dh.DhUtil;

import javax.crypto.Cipher;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

/**
 * DH算法测试
 * 1.接受方利用发送方的公钥构建自己的密钥对
 * 2.本地密钥构建方式: 自己密钥 + 对方公钥
 */
public class SimpleDhTest {

    private static final String src = "security dh";

    public static void main(String[] args) {
        jdkDh();
    }

    public static void jdkDh() {
        try {
            //1.初始化发送发密钥
            KeyPair senderKeyPair = AlgorithmUtil.generateKeyPair(AlgorithmName.DH, 512);
            PrivateKey senderPrivateKey = senderKeyPair.getPrivate();
            byte[] senderPublicKeyEnc = senderKeyPair.getPublic().getEncoded();//发送方公钥,发给接受方(网络, 文件...)
            //2.初始化接受方密钥
            PublicKey receiverPublicKey = DhUtil.convertEncodedBytesToPublicKey(senderPublicKeyEnc);
            DHParameterSpec dhParameterSpec = ((DHPublicKey) receiverPublicKey).getParams();
            KeyPair receiverKeyPair = AlgorithmUtil.generateKeyPair(AlgorithmName.DH, dhParameterSpec);
            PrivateKey receiverPrivateKey = receiverKeyPair.getPrivate();
            byte[] receiverPublicKeyEnc = receiverKeyPair.getPublic().getEncoded();
            //3.密钥构建
            Key receiverDesKey = DhUtil.generateSecretKey(receiverPrivateKey, receiverPublicKey, AlgorithmName.DES);
            //发送方接收公钥
            PublicKey senderPublicKey = DhUtil.convertEncodedBytesToPublicKey(receiverPublicKeyEnc);
            //构建发送方key agreement, 设置私钥(发送方),公钥(接受方)
            Key senderDesKey = DhUtil.generateSecretKey(senderPrivateKey, senderPublicKey, AlgorithmName.DES);
            if (Objects.equals(receiverDesKey, senderDesKey)) {
                System.out.println("keys: " + Arrays.toString(receiverDesKey.getEncoded()));
                System.out.println("双方密钥相同");
            }
            //4.加密
            Cipher cipher = DesUtil.getCipher();
            byte[] result = AlgorithmUtil.encrypt(cipher, senderDesKey, src.getBytes());
            System.out.println("jdk dh encrypt: " + Base64.getEncoder().encodeToString(result));
            //5.解密
            result = AlgorithmUtil.decrypt(cipher, receiverDesKey, result);
            System.out.println("jdk dh decrypt: " + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
