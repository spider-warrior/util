package cn.t.util.security.message.encryption.dh;

import cn.t.util.security.message.AlgorithmName;
import cn.t.util.security.message.AlgorithmUtil;

import javax.crypto.KeyAgreement;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class DhUtil {

    public static PublicKey convertEncodedBytesToPublicKey(byte[] encodedBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = AlgorithmUtil.generateKeyFactory(AlgorithmName.DH);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(encodedBytes);
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }

    public static KeyAgreement generateKeyAgreement(PrivateKey privateKey, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException {
        KeyAgreement keyAgreement = KeyAgreement.getInstance(AlgorithmName.DH);
        keyAgreement.init(privateKey);
        keyAgreement.doPhase(publicKey, true);
        return keyAgreement;
    }

    public static Key generateSecretKey(PrivateKey privateKey, PublicKey publicKey, String algorithm) throws InvalidKeyException, NoSuchAlgorithmException {
        KeyAgreement keyAgreement = generateKeyAgreement(privateKey, publicKey);
        return keyAgreement.generateSecret(algorithm);
    }


}
