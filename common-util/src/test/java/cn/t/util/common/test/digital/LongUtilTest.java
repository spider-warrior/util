package cn.t.util.common.test.digital;

import cn.t.util.common.digital.ByteUtil;
import cn.t.util.common.digital.LongUtil;
import org.junit.Test;

public class LongUtilTest {

    @Test
    public void longToBytesTest() {
        long x = 0b1111111100000001000000110000011100001111000111110011111101111111L;
        System.out.println(x);
        byte[] bytes = LongUtil.longToBytes(x);
        ByteUtil.printBytesInline(bytes, " ", false);
    }

    @Test
    public void bytesToLongTest() {
        long x = 0b1111111100000001000000110000011100001111000111110011111101111111L;
        byte[] bytes = LongUtil.longToBytes(x);
        long xx = LongUtil.bytesToLong(bytes);
        System.out.println(xx);
    }
}
