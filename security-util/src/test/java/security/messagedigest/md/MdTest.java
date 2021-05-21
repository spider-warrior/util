package security.messagedigest.md;

import cn.t.util.security.message.AlgorithmName;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.Security;

public class MdTest {

    private static final String src = "123456";

    public static void main(String[] args) throws Exception {
        jdkMd2();
        jdkMd5();
        bcMd4();
        bcMd5();
        ccMD2();
        ccMD5();
    }

    public static void jdkMd2() throws Exception {
        MessageDigest md = MessageDigest.getInstance(AlgorithmName.MD2);
        byte[] md5Bytes = md.digest(src.getBytes());
        System.out.println("jdk MD2: " + Hex.toHexString(md5Bytes));
    }

    public static void jdkMd5() throws Exception {
        MessageDigest md = MessageDigest.getInstance(AlgorithmName.MD5);
//        byte[] md5Bytes = md.digest(src.getBytes());

        File file = new File("C:\\Users\\36296\\Downloads\\ed1f72a8c2ae4ee717919a4fa4db2ff7.mp3");
        FileInputStream fi = new FileInputStream(file);
        byte[] bytes = new byte[fi.available()];
        long length = fi.read(bytes);
        byte[] md5Bytes = md.digest(bytes);

        System.out.println("jdk MD5: " + Hex.toHexString(md5Bytes));
    }

    public static void bcMd4() throws Exception {
        //添加Provider
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest md = MessageDigest.getInstance(AlgorithmName.MD4);
        md.getProvider();
        byte[] md4Bytes = md.digest(src.getBytes());
        System.out.println("BC MD4:  " + Hex.toHexString(md4Bytes));
    }

    public static void bcMd5() throws Exception {
        Digest digest = new MD5Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] md4Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(md4Bytes, 0);
        System.out.println("BC MD5:  " + Hex.toHexString(md4Bytes));
    }

    public static void ccMD2() {
        System.out.println("CC MD2:  " + DigestUtils.md2Hex(src.getBytes()));
    }

    public static void ccMD5() {
        System.out.println("CC MD5:  " + DigestUtils.md5Hex(src.getBytes()));
    }

}
