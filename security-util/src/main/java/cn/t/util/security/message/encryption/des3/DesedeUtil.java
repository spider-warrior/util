package cn.t.util.security.message.encryption.des3;

import cn.t.util.security.message.AlgorithmName;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class DesedeUtil {


    /**
     * 转换encoded key
     * @param keyBytes xxx
     * @return xxx
     * @throws InvalidKeyException xxx
     * @throws NoSuchAlgorithmException xxx
     * @throws InvalidKeySpecException xxx
     */

    public static Key convertSecretKey(byte[] keyBytes) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        DESedeKeySpec desKeySpec = new DESedeKeySpec(keyBytes);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(AlgorithmName.DESEDE);
        return secretKeyFactory.generateSecret(desKeySpec);
    }

    /**
     * get 3des cipher
     * @return xxx
     * @throws NoSuchPaddingException xxx
     * @throws NoSuchAlgorithmException xxx
     */
    public static Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance(AlgorithmName.DESede_ECB_PKCS5PADDING);
    }
}
