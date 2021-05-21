package cn.t.util.common.test.digital;

import cn.t.util.common.digital.ByteUtil;
import cn.t.util.common.digital.IntUtil;
import org.junit.Test;

import java.nio.ByteOrder;
import java.util.Arrays;

public class IntUtilTest {

    @Test
    public void intToBytesTest() {
        int value = 555555555;
//        byte[] bytes = IntUtil.intToBytes(value, ByteOrder.nativeOrder());
//        byte[] bytes = IntUtil.intToBytes(value, ByteOrder.BIG_ENDIAN);
        byte[] bytes = IntUtil.intToBytes(value, ByteOrder.LITTLE_ENDIAN);
        System.out.println(Arrays.toString(bytes));
//        ByteUtil.printBytesInline(bytes, " ", false);

    }

    @Test
    public void strBytesToInt() {
//        String source = "227,26,29,33";
        String source = "-29,26,29,33";
//        String source = "98, 4, 0, 0";
        byte[] bytes = ByteUtil.stringsToBytes(source, ",");
        int value = IntUtil.bytesToInt(bytes, 0, ByteOrder.LITTLE_ENDIAN);
        System.out.println("value: " + value);
    }

    @Test
    public void intToPositiveBytesTest() {
        int value = 555555555;
        int[] bytes = IntUtil.intToPositiveBytes(value, ByteOrder.LITTLE_ENDIAN);
        System.out.println(Arrays.toString(bytes));
//        ByteUtil.printIntsInline(bytes, " ", false);
    }
}

