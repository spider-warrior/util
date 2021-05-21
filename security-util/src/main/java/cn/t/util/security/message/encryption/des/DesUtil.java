package cn.t.util.security.message.encryption.des;

import cn.t.util.security.message.AlgorithmName;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class DesUtil {


    /**
     * 转换encoded key
     * @param keyBytes xxx
     * @return xxx
     * @throws InvalidKeyException xxx
     * @throws NoSuchAlgorithmException xxx
     * @throws InvalidKeySpecException xxx
     */
    public static Key convertSecretKey(byte[] keyBytes) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(AlgorithmName.DES);
        return secretKeyFactory.generateSecret(desKeySpec);
    }

    /**
     * get des cipher
     * @return xxx
     * @throws NoSuchPaddingException xxx
     * @throws NoSuchAlgorithmException xxx
     */
    public static Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance(AlgorithmName.DES_ECB_PKCS5PADDING);
    }
}
