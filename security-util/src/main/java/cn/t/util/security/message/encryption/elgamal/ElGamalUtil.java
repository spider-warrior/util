package cn.t.util.security.message.encryption.elgamal;

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

public class ElGamalUtil {


    public static PublicKey convertEncodedBytesToPublicKey(byte[] encodedBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = AlgorithmUtil.generateKeyFactory(AlgorithmName.EL_GAMAL);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(encodedBytes);
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }

    public static PrivateKey convertEncodedBytesToPrivateKey(byte[] encodedBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(encodedBytes);
        KeyFactory keyFactory = AlgorithmUtil.generateKeyFactory(AlgorithmName.EL_GAMAL);
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    /**
     * get des cipher
     * @return xxx
     * @throws NoSuchPaddingException xxx
     * @throws NoSuchAlgorithmException xxx
     */
    public static Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance(AlgorithmName.EL_GAMAL);
    }

}
