package cn.t.util.security.message;

public final class AlgorithmName {


    public static final String DES = "DES";

    public static final String DES_ECB_PKCS5PADDING = "DES/ECB/PKCS5Padding";//加密方式/工作模式/填充方式

    public static final String DESEDE = "DESede";

    public static final String DESede_ECB_PKCS5PADDING = "DESede/ECB/PKCS5Padding";//加密方式/工作模式/填充方式

    public static final String AES = "AES";

    public static final String AES_ECB_PKCS5Padding = "AES/ECB/PKCS5Padding";

    public static final String DH = "DH";

    public static final String EL_GAMAL = "ElGamal";

    public static final String PBE_MD5_DES = "PBEWITHMD5andDES";

    public static final String RSA = "RSA";

    public static final String MD5_RSA = "MD5withRSA";

    public static final String MD2 = "MD2";

    public static final String MD4 = "MD4";

    public static final String MD5 = "MD5";

    public static final String SHA = "SHA";

    public static final String SHA224 = "SHA224";

    public static final String HMAC_MD5 = "HmacMD5";

    public static final String DSA = "DSA";

    public static final String SHA1_DSA = "SHA1withDSA";

    public static final String EC = "EC";

    public static final String SHA1_ECDSA = "SHA1withECDSA";

    private AlgorithmName() {
    }
}
