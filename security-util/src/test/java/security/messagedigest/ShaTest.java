package security.messagedigest;

import cn.t.util.security.message.AlgorithmName;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class ShaTest {

    private static final String src = "123456";

    public static void main(String[] args) throws Exception {
        jdkSha1();
        bcSha1();
        bcSha224();
        bcSha224_2();
        ccSha1();
    }

    public static void jdkSha1() throws Exception {
        MessageDigest md = MessageDigest.getInstance(AlgorithmName.SHA);
        md.update(src.getBytes());
        System.out.println("JDK SHA-1: " + Hex.toHexString(md.digest()));
    }

    public static void bcSha1() {
        Digest digest = new SHA1Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] sha1Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(sha1Bytes, 0);
        System.out.println("BC  SHA-1: " + Hex.toHexString(sha1Bytes));
    }

    public static void bcSha224() {
        Digest digest = new SHA224Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] sha224Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(sha224Bytes, 0);
        System.out.println("BC  SHA-224: " + Hex.toHexString(sha224Bytes));
    }

    public static void bcSha224_2() throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest md = MessageDigest.getInstance(AlgorithmName.SHA224);
        md.update(src.getBytes());
        System.out.println("BC  SHA-224: " + Hex.toHexString(md.digest()));
    }

    public static void ccSha1() {
        System.out.println("CC  SHA-1: " + DigestUtils.sha1Hex(src.getBytes()));
        System.out.println("CC  SHA-1: " + DigestUtils.sha1Hex(src));
    }

}
