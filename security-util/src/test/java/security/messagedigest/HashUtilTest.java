package security.messagedigest;

import cn.t.util.common.digital.HexUtil;
import cn.t.util.security.message.digest.HashUtil;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class HashUtilTest {

    @Test
    public void md5Test() throws NoSuchAlgorithmException {
        System.out.println(HashUtil.md5("123456"));
    }

    @Test
    public void sha1Test() throws NoSuchAlgorithmException {
        System.out.println(HashUtil.sha1("123456"));
    }

    @Test
    public void hexStringMd5Test() throws Exception {
        String hexString = "";
        byte[] bytes = HexUtil.stringToBytes(hexString);
        System.out.println(HashUtil.md5(bytes));
    }
}
