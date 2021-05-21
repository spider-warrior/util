package cn.t.util.security.message.encryption.rsa;

import cn.t.util.security.message.AlgorithmName;
import cn.t.util.security.message.AlgorithmUtil;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaUtil {

    public static PublicKey convertEncodedBytesToPublicKey(byte[] encodedBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(encodedBytes);
        KeyFactory keyFactory = AlgorithmUtil.generateKeyFactory(AlgorithmName.RSA);
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }

    public static PrivateKey convertEncodedBytesToPrivateKey(byte[] encodedBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(encodedBytes);
        KeyFactory keyFactory = AlgorithmUtil.generateKeyFactory(AlgorithmName.RSA);
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    public static Cipher generateCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance(AlgorithmName.RSA);
    }
}
