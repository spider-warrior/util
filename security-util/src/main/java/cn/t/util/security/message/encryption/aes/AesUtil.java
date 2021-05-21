package cn.t.util.security.message.encryption.aes;

import cn.t.util.security.message.AlgorithmName;
import cn.t.util.security.message.padding.PkcsUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

public class AesUtil {

    /**
     * 转换encoded key
     * @param keyBytes xxx
     * @return xxx
     * @throws InvalidKeyException xxx
     * @throws NoSuchAlgorithmException xxx
     * @throws InvalidKeySpecException xxx
     */
    public static Key convertSecretKey(byte[] keyBytes) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        return new SecretKeySpec(keyBytes, AlgorithmName.AES);
    }

    /**
     * get aes cipher
     * @return xxx
     * @throws NoSuchPaddingException xxx
     * @throws NoSuchAlgorithmException xxx
     */
    public static Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance(AlgorithmName.AES_ECB_PKCS5Padding);
    }

    public static byte[] encrypt(byte[] content, byte[] secret, int length) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException {
        byte[] keyBytes = PkcsUtil.pkcs5Padding(secret);
        Key key = AesUtil.convertSecretKey(keyBytes);
        Cipher cipher = Cipher.getInstance(AlgorithmName.AES_ECB_PKCS5Padding);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(content);
    }

    public static byte[] decrypt(byte[] content, byte[] secret, int length) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException {
        byte[] keyBytes = PkcsUtil.pkcs5Padding(secret);
        Key key = AesUtil.convertSecretKey(keyBytes);
        Cipher cipher = Cipher.getInstance(AlgorithmName.AES_ECB_PKCS5Padding);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(content);
    }
}
