package cn.t.util.common.test.digital;

import cn.t.util.common.digital.CharUtil;
import org.junit.Test;

public class CharUtilTest {

    @Test
    public void asciiCharToIntTest() {
        int value = CharUtil.hexAsciiCharToInt('B');
        System.out.println(value);
    }
}
