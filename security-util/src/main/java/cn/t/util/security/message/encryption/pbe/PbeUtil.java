package cn.t.util.security.message.encryption.pbe;

import cn.t.util.security.message.AlgorithmName;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PbeUtil {

    public static Key generateSecretKey(char[] password) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(AlgorithmName.PBE_MD5_DES);
        return secretKeyFactory.generateSecret(pbeKeySpec);
    }

    public static Cipher generateCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance(AlgorithmName.PBE_MD5_DES);
    }
}
