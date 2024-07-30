package cn.t.util.common.test.digital;

import cn.t.util.common.digital.ByteUtil;
import cn.t.util.common.digital.IntUtil;
import org.junit.Test;

import java.nio.ByteOrder;

public class ByteUtilTest {

    public static void main(String[] args) {
        System.out.println((char) 92);
        System.out.println((char) 34);
    }

    @Test
    public void bytesToPositiveNumberTest() {
        byte[] content = ByteUtil.stringsToBytes("0,39,0,0,0,255", ",");
        ByteUtil.printBytesInline(content, " ", false);
        String result = ByteUtil.bytesToPositiveNumber(content, ".");
        System.out.println(result);
    }

    @Test
    public void hexStringToBytesTest() {
        String hex = "a7e1a587";
        byte[] result = ByteUtil.hexStringToBytes(hex, ByteOrder.BIG_ENDIAN);
        System.out.println("bytes: ");
        ByteUtil.printBytesInline(result, ", ", true);
        int value = IntUtil.bytesToInt(result, 0, ByteOrder.BIG_ENDIAN);
        System.out.println("int value: " + value);
    }

    @Test
    public void bytesTest() {
        System.out.println(new String(new byte[]{0, 48, 11}));
    }

    @Test
    public void printBytesInlineTest() {
        byte[] bytes = {1, 2, 3, 4};
        ByteUtil.printBytesInline(bytes, ", ", false);
        ByteUtil.printBytesInline(bytes, ", ", true);
    }

    @Test
    public void stringBytesToArrayTest() {
//        String stringBytes = "91, 34, 83, 69, 78, 68, 92, 110, 100, 101, 115, 116, 105, 110, 97, 116, 105, 111, 110, 58, 47, 97, 112, 112, 47, 108, 98, 115, 47, 100, 101, 118, 105, 99, 101, 47, 115, 121, 110, 95, 108, 111, 99, 97, 116, 105, 111, 110, 92, 110, 99, 111, 110, 116, 101, 110, 116, 45, 108, 101, 110, 103, 116, 104, 58, 52, 53, 92, 110, 92, 110, 123, 92, 34, 109, 97, 99, 92, 34, 58, 92, 34, 49, 49, 92, 34, 44, 92, 34, 108, 97, 116, 105, 116, 117, 100, 101, 92, 34, 58, 92, 34, 49, 49, 92, 34, 44, 92, 34, 108, 111, 110, 103, 105, 116, 117, 100, 101, 92, 34, 58, 92, 34, 49, 49, 92, 34, 125, 92, 117, 48, 48, 48, 48, 34, 93";
        String stringBytes = "67, 79, 78, 78, 69, 67, 84, 32, 120, 117, 105, 46, 112, 116, 108, 111, 103, 105, 110, 50, 46, 113, 113, 46, 99, 111, 109, 58, 52, 52, 51, 32, 72, 84, 84, 80, 47, 49, 46, 49, 13, 10, 72, 79, 83, 84, 58, 32, 120, 117, 105, 46, 112, 116, 108, 111, 103, 105, 110, 50, 46, 113, 113, 46, 99, 111, 109, 58, 52, 52, 51, 13, 10, 13, 10";
        byte[] originalBytes = ByteUtil.stringsToBytes(stringBytes, ",");
        System.out.println(new String(originalBytes));
    }

}

