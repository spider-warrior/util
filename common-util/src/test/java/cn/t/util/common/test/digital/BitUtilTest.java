package cn.t.util.common.test.digital;

import cn.t.util.common.digital.BitUtil;
import cn.t.util.common.digital.ByteUtil;
import org.junit.Test;

public class BitUtilTest {

    @Test
    public void bitArrayTest() {
        byte b = -128;
        byte[] unsignedBits = BitUtil.toUnsignedBitArray(b);
        byte[] signedBits = BitUtil.toSignedBitArray(b);
        ByteUtil.printBytesSimple(unsignedBits);
        ByteUtil.printBytesSimple(signedBits);
    }

    @Test
    public void bitStrTest() {
        byte b = -128;
        String unsignedBits = BitUtil.toUnsignedBitStr(b);
        String signedBits = BitUtil.toSignedBitStr(b);
        System.out.println("unsigned bits: " + unsignedBits);
        System.out.println("signed bits: " + signedBits);
        byte bb = BitUtil.toByte(signedBits);
        System.out.println("byte: " + bb);
    }
}

