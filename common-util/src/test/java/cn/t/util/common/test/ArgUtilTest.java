package cn.t.util.common.test;

import cn.t.util.common.ArgUtil;
import org.junit.Test;

public class ArgUtilTest {

    @Test
    public void resolveArgsTest() {
        String str = "a=1 b=222    c=333";
        System.out.println(ArgUtil.resolveArgs(str));
    }

}
