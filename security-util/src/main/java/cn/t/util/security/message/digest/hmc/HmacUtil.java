package cn.t.util.security.message.digest.hmc;

import cn.t.util.security.message.AlgorithmName;

import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class HmacUtil {

    /**
     * 转换encoded key
     * @param keyBytes xxx
     * @return xxx
     * @throws InvalidKeyException xxx
     * @throws NoSuchAlgorithmException xxx
     * @throws InvalidKeySpecException xxx
     */
    public static Key convertSecretKey(byte[] keyBytes) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        return new SecretKeySpec(keyBytes, AlgorithmName.HMAC_MD5);
    }
}
