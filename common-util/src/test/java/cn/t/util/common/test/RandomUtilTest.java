package cn.t.util.common.test;

import cn.t.util.common.RandomUtil;
import org.junit.Test;

public class RandomUtilTest {

    @Test
    public void testRandomInt() {
        int min = 1000;
        int max = 1002;
        for (int i = 0; i < 1000000; i++) {
            int v = RandomUtil.randomInt(min, max);
            if (v < min || v >= max) {
                throw new RuntimeException("算法错误");
            }
        }
        System.out.println("OK");
    }

    @Test
    public void testRandomString() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(RandomUtil.randomString(16));
        }
    }
}
