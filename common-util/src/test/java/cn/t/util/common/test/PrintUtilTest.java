package cn.t.util.common.test;

import cn.t.util.common.PrintUtil;
import cn.t.util.common.RandomUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @description: create: 2019-09-09 14:58
 * @author: yj
 **/
public class PrintUtilTest {

    @Test
    public void printArrayBinaryTest() {
        int[] arr = new int[32];
        for(int i=0; i< arr.length; i++) {
            arr[i] = RandomUtil.randomInt(1, 100);
        }
        System.out.println(Arrays.toString(arr));
        PrintUtil.printBinaryTree(arr);
    }

}
