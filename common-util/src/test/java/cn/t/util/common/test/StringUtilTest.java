package cn.t.util.common.test;

import cn.t.util.common.StringUtil;
import cn.t.util.common.digital.HexUtil;
import org.junit.Test;

public class StringUtilTest {

    @Test
    public void isAllNumericTest() {
        String str1 = "123456789";
        String str2 = "/0123456789:";
        System.out.println(StringUtil.isAllNumeric(str1));
        System.out.println(StringUtil.isAllNumeric(str2));
    }

    @Test
    public void partUTF8StringTest() {
        String str = "123abc~!@#$%^&*()阿门がっこう...";
        StringUtil.StringPart stringPart = StringUtil.partUTF8String(str);
        System.out.println(String.format("latin: %s", new String(stringPart.getLatinCharacters())));
        System.out.println(String.format("other: %s", new String(stringPart.getOtherCharacters())));
    }

    @Test
    public void removeEmojiTest() {
        //# \"\"
        String str = "我们阿达西がっこうsasa😄\uD83D\uDC74asas我们阿达西がっこう";
        System.out.println(StringUtil.removeEmoji(str));
    }


    @Test
    public void emkojiToUnicodeTest() {
        String str = "我们阿达西がっこうsasa😄asas我们阿达西がっこう";
        System.out.println(StringUtil.emojiToUnicode(str));
    }

    @Test
    public void unicodeToEmojiTest() {
        String str = "我们阿达西がっこうsasa\\ud83d\\ude04asas我们阿达西がっこう";
        System.out.println(StringUtil.unicodeToEmoji(str));
    }

    @Test
    public void testEmojiToUnicode() {
        String str = "😄";
        System.out.println(str.length());
    }

    @Test
    public void hexStringTest() {
        String get = "GET";
        String post = "POST";
        System.out.println(HexUtil.bytesToHex(get.getBytes()));
        System.out.println(HexUtil.bytesToHex(post.getBytes()));
    }

    @Test
    public void snakeToCamelTest() {
//        System.out.println(StringUtil.snakeToCamel("a___d", true));
//        System.out.println(StringUtil.snakeToCamel("a___d", false));
//        System.out.println(StringUtil.snakeToCamel("a_b_c_d", true));
//        System.out.println(StringUtil.snakeToCamel("a_b_c_d", false));
//        System.out.println(StringUtil.snakeToCamel("aaa_bbb_ccc_ddd", true));
//        System.out.println(StringUtil.snakeToCamel("aaa_bbb_ccc_ddd", false));

        System.out.println(StringUtil.snakeToCamel("employee_app_auth_config", false));
        System.out.println(StringUtil.snakeToCamel("employee_department", false));
        System.out.println(StringUtil.snakeToCamel("employee_department_relationship", false));
        System.out.println(StringUtil.snakeToCamel("employee_menu_source", false));
        System.out.println(StringUtil.snakeToCamel("employee_menu_source_uri_source_relationship", false));
        System.out.println(StringUtil.snakeToCamel("employee_role", false));
        System.out.println(StringUtil.snakeToCamel("employee_role_menu_source_relationship", false));
        System.out.println(StringUtil.snakeToCamel("employee_role_relationship", false));
        System.out.println(StringUtil.snakeToCamel("employee_uri_source", false));

    }
}
