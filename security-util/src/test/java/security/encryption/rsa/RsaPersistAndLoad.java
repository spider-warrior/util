package security.encryption.rsa;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaPersistAndLoad {
    public static void main(String[] args) throws Exception {
        // 第一步：生成RSA密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // 第二步：将公钥和私钥导出到文件
        Files.write(Paths.get("publicKey.key"), publicKey.getEncoded());
        Files.write(Paths.get("privateKey.key"), privateKey.getEncoded());

        // 第三步：从文件中加载公钥和私钥
        byte[] publicKeyBytes = Files.readAllBytes(Paths.get("publicKey.key"));
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get("privateKey.key"));

        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey loadedPublicKey = keyFactory.generatePublic(publicKeySpec);
        PrivateKey loadedPrivateKey = keyFactory.generatePrivate(privateKeySpec);

        // 第四步：使用公钥加密数据
        String originalText = "Hello, RSA!";
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, loadedPublicKey);
        byte[] encryptedBytes = encryptCipher.doFinal(originalText.getBytes(StandardCharsets.UTF_8));
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);

        System.out.println("加密后的文本: " + encryptedText);

        // 第五步：使用私钥解密数据
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, loadedPrivateKey);
        byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedText));
        String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);

        System.out.println("解密后的文本: " + decryptedText);
    }
}
