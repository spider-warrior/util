package security.base64;

import cn.t.util.common.digital.HexUtil;
import cn.t.util.security.message.base64.Base64Util;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class Base64UtilTest {

    private static final String src = "CGNwMDAQQpg=";

    public static void main(String[] args) throws Exception {
        commonsCodesBase64();
        bcBase64();
    }

    private static void commonsCodesBase64() {
        byte[] encode = Base64.encodeBase64(src.getBytes());
        System.out.println("encode: " + new String(encode));

        byte[] result = Base64.decodeBase64(encode);
        System.out.println("decode: " + new String(result));
    }

    private static void bcBase64() {
        byte[] encode = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
        System.out.println("encode: " + new String(encode));

        byte[] result = org.bouncycastle.util.encoders.Base64.decode(encode);
        System.out.println("decode: " + new String(result));
    }

    @Test
    public void encodeTest() {
        String str = "CGNwMDAQQpg=";
        byte[] bytes = Base64Util.decode(str.getBytes());
        System.out.println(HexUtil.bytesToHex(bytes));
    }
}
