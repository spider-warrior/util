package cn.t.util.common.test.digital;

import cn.t.util.common.digital.ShortUtil;
import org.junit.Test;

import java.nio.ByteOrder;
import java.util.Arrays;

public class ShortUtilTest {

    @Test
    public void shortToBytesTest() {
        short max = Short.MAX_VALUE;
        System.out.println("max: " + max);
        System.out.println("小头: " + Arrays.toString(ShortUtil.shortToBytes(max, ByteOrder.LITTLE_ENDIAN)));
        System.out.println("大头: " + Arrays.toString(ShortUtil.shortToBytes(max, ByteOrder.BIG_ENDIAN)));
    }

    @Test
    public void bytesToShortTest() {
        byte[] bs1 = {-1, 127};
        byte[] bs2 = {127, -1};
        System.out.println("小头: " + ShortUtil.bytesToShort(bs1, ByteOrder.LITTLE_ENDIAN));
        System.out.println("大头: " + ShortUtil.bytesToShort(bs2, ByteOrder.BIG_ENDIAN));

        System.out.println("=============================================================");

        byte[] bs3 = {0, 1, 2, -1, 127, 3, 4};
        byte[] bs4 = {0, 1, 2, 127, -1, 3, 4};

        System.out.println("小头: " + ShortUtil.bytesToShort(bs3, 3, ByteOrder.LITTLE_ENDIAN));
        System.out.println("大头: " + ShortUtil.bytesToShort(bs4, 3, ByteOrder.BIG_ENDIAN));

        System.out.println("=============================================================");

        byte[] bs5 = {-126, 0};
        System.out.println("小头: " + ShortUtil.bytesToShort(bs5, ByteOrder.LITTLE_ENDIAN));

    }


    @Test
    public void sidiTest() {
        short huilu = 9367;
        short deviceAddr = 4;
        byte[] huiluBytes = ShortUtil.shortToBytes(huilu, ByteOrder.LITTLE_ENDIAN);
        byte[] deviceAddrBytes = ShortUtil.shortToBytes(deviceAddr, ByteOrder.LITTLE_ENDIAN);
        byte[] macBytes = Arrays.copyOf(deviceAddrBytes, huiluBytes.length + deviceAddrBytes.length);
        System.arraycopy(huiluBytes, 0, macBytes, huiluBytes.length, huiluBytes.length);
        StringBuilder builder = new StringBuilder();
        for(byte b: macBytes) {
            builder.append(b&0xFF).append('.');
        }
        builder.deleteCharAt(builder.length()-1);
        System.out.println(builder.toString());
    }

}
