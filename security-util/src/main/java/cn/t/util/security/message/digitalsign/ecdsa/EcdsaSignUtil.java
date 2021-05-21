package cn.t.util.security.message.digitalsign.ecdsa;

import cn.t.util.security.message.AlgorithmName;
import cn.t.util.security.message.AlgorithmUtil;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class EcdsaSignUtil {

    public static PublicKey convertEncodedBytesToPublicKey(byte[] encodedBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(encodedBytes);
        KeyFactory keyFactory = AlgorithmUtil.generateKeyFactory(AlgorithmName.EC);
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }

    public static PrivateKey convertEncodedBytesToPrivateKey(byte[] encodedBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(encodedBytes);
        KeyFactory keyFactory = AlgorithmUtil.generateKeyFactory(AlgorithmName.EC);
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }
}
