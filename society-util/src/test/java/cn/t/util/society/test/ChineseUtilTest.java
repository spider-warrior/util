package cn.t.util.society.test;

import cn.t.util.society.ChineseUtil;
import org.junit.Test;

public class ChineseUtilTest {

    @Test
    public void toPinyinTest() {
        String str = "王红杰";
        System.out.println(ChineseUtil.toPinyin(str));
    }

    @Test
    public void getFirstLetterTest() {
        String str = "王红杰";
        System.out.println(ChineseUtil.getFirstLettersUp(str));
        System.out.println(ChineseUtil.getFirstLettersLo(str));
    }
}
