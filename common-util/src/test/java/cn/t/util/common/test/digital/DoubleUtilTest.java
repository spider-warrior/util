package cn.t.util.common.test.digital;

import cn.t.util.common.digital.DoubleUtil;
import org.junit.Test;

public class DoubleUtilTest {


    @Test
    public void doubleToHexStringTest() {
        double d = Double.MIN_VALUE;
        System.out.println(DoubleUtil.doubleToHexString(d));
    }

    @Test
    public void hexStringToDoubleTest() {
        String hexString1 = "1";
        String hexString2 = "01";
        System.out.println(DoubleUtil.hexStringToDouble(hexString1));
        System.out.println(DoubleUtil.hexStringToDouble(hexString2));
    }


}

