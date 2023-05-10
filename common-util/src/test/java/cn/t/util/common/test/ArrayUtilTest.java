package cn.t.util.common.test;

import cn.t.util.common.ArrayUtil;
import cn.t.util.common.digital.ByteUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtilTest {

    @Test
    public void binarySearchTest() {
        String str1 = "abcdefghijklmnopqrstuvwxyz";
        String str2 = "ghi";
        System.out.println(ArrayUtil.binarySearch(str1.getBytes(), str2.getBytes()));
    }

    @Test
    public void combineTest() {
        String original = "abcdefghigklmnopqrstuvwxyz";
        String str1 = "abcdefg";
        String str2 = "higklmn";
        String str3 = "opqrst";
        String str4 = "uvwxyz";
        byte[] recoverBytes = ArrayUtil.combine(str1.getBytes(), str2.getBytes(), str3.getBytes(), str4.getBytes());
        System.out.println(new String(recoverBytes).equals(original));
    }

    @Test
    public void reverseTest() {
        byte[] bs = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ByteUtil.printBytesSimple(bs);
        byte[] newBs = ArrayUtil.reverse(bs);
        ByteUtil.printBytesSimple(newBs);
    }

    @Test
    public void joinTest() {
        byte[] bs = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        String str = ArrayUtil.join(bs, ":");
        System.out.println(str);
    }

    @Test
    public void convertToIntArrayTest() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(0);
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        System.out.println(Arrays.toString(ArrayUtil.convertToIntArray(integerList)));
    }
}
