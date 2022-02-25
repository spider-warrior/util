package security.messagedigest.hmac;

import cn.t.util.security.message.AlgorithmName;
import cn.t.util.security.message.AlgorithmUtil;
import cn.t.util.security.message.digest.HmacUtil;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Mac;
import java.security.Key;

public class SimpleHmacTest {

    private static final String src = "123456789";

    private static byte[] key;

    public static void main(String[] args) throws Exception {
        jdkHmacMD5();
        bcHmacMd5();
    }

    private static void jdkHmacMD5() throws Exception {
        key = AlgorithmUtil.generateSecretKeyEncodedBytes(AlgorithmName.HMAC_MD5);
//      key = Hex.decode("1234567890");
        Key restoreSecretKey = HmacUtil.convertSecretKey(key);
        Mac mac = AlgorithmUtil.generateMac(restoreSecretKey.getAlgorithm(), restoreSecretKey);
        byte[] hmacMd5Bytes = mac.doFinal(src.getBytes());//执行摘要
        System.out.println("jdk hmacMd5: " + Hex.toHexString(hmacMd5Bytes));
    }

    private static void bcHmacMd5() {
        HMac hmac = new HMac(new MD5Digest());
        hmac.init(new KeyParameter(key));
//        hmac.init(new KeyParameter(Hex.decode("1234567890")));
        hmac.update(src.getBytes(), 0, src.getBytes().length);

        byte[] hmacMd5Bytes = new byte[hmac.getMacSize()];//执行摘要
        hmac.doFinal(hmacMd5Bytes, 0);
        System.out.println("bc  hmacMd5: " + Hex.toHexString(hmacMd5Bytes));
    }
}
