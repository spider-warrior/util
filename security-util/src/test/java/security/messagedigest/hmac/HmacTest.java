package security.messagedigest.hmac;

import cn.t.util.security.message.AlgorithmName;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class HmacTest {

    private static final String src = "123456789";

    public static void main(String[] args) throws Exception {
        jdkHmacMD5();
        bcHmacMd5();
    }

    public static void jdkHmacMD5() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AlgorithmName.HMAC_MD5);//初始化key generator
        SecretKey secretKey = keyGenerator.generateKey();//产生密钥
//        byte[] key = secretKey.getEncoded();//获得密钥
        byte[] key = Hex.decode("1234567890");

        SecretKey restoreSecretKey = new SecretKeySpec(key, AlgorithmName.HMAC_MD5);//还原密钥
        Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());//实例化MAC
        mac.init(restoreSecretKey);//初始化MAC
        byte[] hmacMd5Bytes = mac.doFinal(src.getBytes());//执行摘要
        System.out.println("jdk hmacMd5: " + Hex.toHexString(hmacMd5Bytes));
    }

    public static void bcHmacMd5() {
        HMac hmac = new HMac(new MD5Digest());
        hmac.init(new KeyParameter(Hex.decode("1234567890")));
        hmac.update(src.getBytes(), 0, src.getBytes().length);

        byte[] hmacMd5Bytes = new byte[hmac.getMacSize()];//执行摘要
        hmac.doFinal(hmacMd5Bytes, 0);
        System.out.println("bc  hmacMd5: " + Hex.toHexString(hmacMd5Bytes));
    }
}
