package cn.t.util.security.message;

import javax.crypto.*;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;

public class AlgorithmUtil {

    /**
     * generate key generator
     *
     * @param algorithm 算法
     * @param length    密钥长度
     * @param provider  算法提供商
     * @return xxx
     * @throws NoSuchAlgorithmException xxx
     * @throws NoSuchProviderException xxx
     */
    public static KeyGenerator generateKeyGenerator(String algorithm, int length, String provider) throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyGenerator keyGenerator;
        if (provider != null) {
            keyGenerator = KeyGenerator.getInstance(algorithm, provider);
        } else {
            keyGenerator = KeyGenerator.getInstance(algorithm);
        }
        if (length > 0) {
            keyGenerator.init(length);
        }
        return keyGenerator;
    }

    /**
     * generate key generator
     *
     * @param algorithm 算法
     * @param length    密钥长度
     * @return xxx
     * @throws NoSuchAlgorithmException xxx
     * @throws NoSuchProviderException xxx
     */
    public static KeyGenerator generateKeyGenerator(String algorithm, int length) throws NoSuchAlgorithmException, NoSuchProviderException {
        return generateKeyGenerator(algorithm, length, null);
    }

    /**
     * generate key generator
     *
     * @param algorithm 算法
     * @return xxx
     * @throws NoSuchAlgorithmException xxx
     * @throws NoSuchProviderException xxx
     */
    public static KeyGenerator generateKeyGenerator(String algorithm) throws NoSuchAlgorithmException, NoSuchProviderException {
        return generateKeyGenerator(algorithm, -1, null);
    }


    /**
     * secret key generator
     *
     * @param algorithm 算法
     * @param length    密钥长度
     * @param provider  算法提供商
     * @return xxx
     * @throws NoSuchAlgorithmException xxx
     * @throws NoSuchProviderException xxx
     */
    public static Key generateSecretKey(String algorithm, int length, String provider) throws NoSuchAlgorithmException, NoSuchProviderException {
        return generateKeyGenerator(algorithm, length, provider).generateKey();
    }

    /**
     * secret key generator
     *
     * @param algorithm 算法
     * @param length    密钥长度
     * @return xxx
     * @throws NoSuchAlgorithmException xxx
     * @throws NoSuchProviderException xxx
     */
    public static Key generateSecretKey(String algorithm, int length) throws NoSuchAlgorithmException, NoSuchProviderException {
        return generateKeyGenerator(algorithm, length).generateKey();
    }

    /**
     * secret key generator
     *
     * @param algorithm 算法
     * @return xxx
     * @throws NoSuchAlgorithmException xxx
     * @throws NoSuchProviderException xxx
     */
    public static Key generateSecretKey(String algorithm) throws NoSuchAlgorithmException, NoSuchProviderException {
        return generateKeyGenerator(algorithm).generateKey();
    }


    public static KeyPairGenerator generateKeyPairGenerator(String algorithm, int length) throws NoSuchAlgorithmException {
        KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        senderKeyPairGenerator.initialize(length);
        return senderKeyPairGenerator;
    }

    public static KeyPairGenerator generateKeyPairGenerator(String algorithm, AlgorithmParameterSpec params, SecureRandom secureRandom) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        if (secureRandom != null) {
            senderKeyPairGenerator.initialize(params, secureRandom);
        } else {
            senderKeyPairGenerator.initialize(params);
        }
        return senderKeyPairGenerator;
    }

    public static KeyPairGenerator generateKeyPairGenerator(String algorithm, AlgorithmParameterSpec params) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        return generateKeyPairGenerator(algorithm, params, null);
    }

    public static KeyPair generateKeyPair(String algorithm, int length) throws NoSuchAlgorithmException {
        KeyPairGenerator senderKeyPairGenerator = generateKeyPairGenerator(algorithm, length);
        return senderKeyPairGenerator.generateKeyPair();
    }

    public static KeyPair generateKeyPair(String algorithm, AlgorithmParameterSpec params, SecureRandom secureRandom) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator senderKeyPairGenerator = generateKeyPairGenerator(algorithm, params, secureRandom);
        return senderKeyPairGenerator.generateKeyPair();
    }

    public static KeyPair generateKeyPair(String algorithm, AlgorithmParameterSpec params) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        return generateKeyPair(algorithm, params, null);
    }

    public static KeyFactory generateKeyFactory(String algorithm) throws NoSuchAlgorithmException {
        return KeyFactory.getInstance(algorithm);
    }

    /**
     * generate secret key encoded bytes
     *
     * @param algorithm 算法
     * @param length    密钥长度
     * @param provider  算法提供商
     * @return xxx
     * @throws NoSuchAlgorithmException xxx
     * @throws NoSuchProviderException xxx
     */
    public static byte[] generateSecretKeyEncodedBytes(String algorithm, int length, String provider) throws NoSuchAlgorithmException, NoSuchProviderException {
        return generateSecretKey(algorithm, length, provider).getEncoded();
    }

    /**
     * generate secret key encoded bytes
     *
     * @param algorithm 算法
     * @param length    密钥长度
     * @return xxx
     * @throws NoSuchAlgorithmException xxx
     * @throws NoSuchProviderException xxx
     */
    public static byte[] generateSecretKeyEncodedBytes(String algorithm, int length) throws NoSuchAlgorithmException, NoSuchProviderException {
        return generateSecretKey(algorithm, length).getEncoded();
    }

    /**
     * generate secret key encoded bytes
     *
     * @param algorithm 算法
     * @return xxx
     * @throws NoSuchAlgorithmException xxx
     * @throws NoSuchProviderException xxx
     */
    public static byte[] generateSecretKeyEncodedBytes(String algorithm) throws NoSuchAlgorithmException, NoSuchProviderException {
        return generateSecretKey(algorithm).getEncoded();
    }

    /**
     * encrypt data
     *
     * @param cipher                 cipher
     * @param key                    key
     * @param algorithmParameterSpec 算法参数
     * @param content                要加密的内容
     * @return xxx
     * @throws InvalidKeyException xxx
     * @throws BadPaddingException xxx
     * @throws IllegalBlockSizeException xxx
     * @throws InvalidAlgorithmParameterException xxx
     */
    public static byte[] encrypt(Cipher cipher, Key key, AlgorithmParameterSpec algorithmParameterSpec, byte[] content) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        if (algorithmParameterSpec != null) {
            cipher.init(Cipher.ENCRYPT_MODE, key, algorithmParameterSpec);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        }
        return cipher.doFinal(content);
    }

    /**
     * encrypt data
     *
     * @param cipher  cipher
     * @param key     key
     * @param content 要加密的内容
     * @return xxx
     * @throws InvalidKeyException xxx
     * @throws BadPaddingException xxx
     * @throws IllegalBlockSizeException xxx
     * @throws InvalidAlgorithmParameterException xxx
     */
    public static byte[] encrypt(Cipher cipher, Key key, byte[] content) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return encrypt(cipher, key, null, content);
    }

    /**
     * decrypt data
     *
     * @param cipher  cipher
     * @param key     key
     * @param algorithmParameterSpec  xxx
     * @param content 要解密的内容
     * @return xxx
     * @throws InvalidKeyException xxx
     * @throws BadPaddingException xxx
     * @throws IllegalBlockSizeException xxx
     * @throws InvalidAlgorithmParameterException xxx
     */
    public static byte[] decrypt(Cipher cipher, Key key, AlgorithmParameterSpec algorithmParameterSpec, byte[] content) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        if (algorithmParameterSpec != null) {
            cipher.init(Cipher.DECRYPT_MODE, key, algorithmParameterSpec);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key);
        }
        return cipher.doFinal(content);
    }

    /**
     * decrypt data
     *
     * @param cipher  cipher
     * @param key     key
     * @param content 要解密的内容
     * @return xxx
     * @throws InvalidKeyException xxx
     * @throws BadPaddingException xxx
     * @throws IllegalBlockSizeException xxx
     * @throws InvalidAlgorithmParameterException xxx
     */
    public static byte[] decrypt(Cipher cipher, Key key, byte[] content) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return decrypt(cipher, key, null, content);
    }

    public static AlgorithmParameterGenerator generateAlgorithmParameterGenerator(String algorithm) throws NoSuchAlgorithmException {
        return AlgorithmParameterGenerator.getInstance(algorithm);
    }

    public static AlgorithmParameters generateAlgorithmParameters(String algorithm, int length) throws NoSuchAlgorithmException {
        AlgorithmParameterGenerator algorithmParameterGenerator = generateAlgorithmParameterGenerator(algorithm);
        algorithmParameterGenerator.init(length);
        return algorithmParameterGenerator.generateParameters();
    }

    public static AlgorithmParameterSpec generateAlgorithmParameterSpec(String algorithm, int length, Class<? extends AlgorithmParameterSpec> type) throws NoSuchAlgorithmException, InvalidParameterSpecException {
        AlgorithmParameters algorithmParameters = generateAlgorithmParameters(algorithm, length);
        return algorithmParameters.getParameterSpec(type);
    }

    /**
     * 生成随机种子
     * @param length xxx
     * @return xxx
     */
    public static byte[] generateSeedBytes(int length) {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.generateSeed(length);
    }

    public static Mac generateMac(String algorithm, Key secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(algorithm);
        mac.init(secretKey);
        return mac;
    }

    public static Signature generateSenderSignature(String algorithm, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException {
        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(privateKey);
        return signature;
    }

    public static byte[] signData(String algorithm, PrivateKey privateKey, byte[] content) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
        Signature signature = generateSenderSignature(algorithm, privateKey);
        signature.update(content);
        return signature.sign();
    }

    public static Signature generateReceiverSignature(String algorithm, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException {
        Signature signature = Signature.getInstance(algorithm);
        signature.initVerify(publicKey);
        return signature;
    }

    public static boolean verifySign(String algorithm, PublicKey publicKey, byte[] content, byte[] sign) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
        Signature signature = generateReceiverSignature(algorithm, publicKey);
        signature.update(content);
        return signature.verify(sign);
    }

}
