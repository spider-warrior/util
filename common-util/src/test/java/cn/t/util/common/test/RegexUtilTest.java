package cn.t.util.common.test;

import cn.t.util.common.RegexUtil;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtilTest {

    @Test
    public void getIpTest() {
        String str1 = "127.0.0.1";
        String str2 = "127.0.0.1:8080";
        String str3 = "fdjkljakd127.0.0.1:dfdhjkfda";
        System.out.println(RegexUtil.getIp(str1));
        System.out.println(RegexUtil.getIp(str2));
        System.out.println(RegexUtil.getIp(str3));
    }

    @Test
    public void isIpTest() {
        String str1 = "127.0.0.1";
        String str2 = "127.0.0.1:8080";
        String str3 = "fdjkljakd127.0.0.1:dfdhjkfda";
        System.out.println(RegexUtil.isIp(str1));
        System.out.println(RegexUtil.isIp(str2));
        System.out.println(RegexUtil.isIp(str3));
    }

    @Test
    public void tableNameMatch() {
        Pattern tableNameMatcher = Pattern.compile("into\\s+([_a-z]*)");
        String sql1 = "insert into xf_user (id,name) values (1,2)";
        String sql2 = "insert into xf_user (id,name) values (1,'xiaoming')";
        String sql3 = "insert all into xf_user (column1, column2, column_n) VALUES (expr1, expr2, expr_n)";
        String sql4 = "insert all into (column1, column2, column_n) VALUES (expr1, expr2, expr_n)";
        System.out.println(RegexUtil.getInsertSqlTableName(sql1));
        System.out.println(RegexUtil.getInsertSqlTableName(sql2));
        System.out.println(RegexUtil.getInsertSqlTableName(sql3));
        System.out.println(RegexUtil.getInsertSqlTableName(sql4));
    }

    @Test
    public void findCity1Test() {
        String str1 = "浙江省杭州市天目山路176号数源科技园11号楼3F";
        String str2 = "杭州市天目山路176号数源科技园11号楼3F";
        //正则表达式解释: 以省前可有2-3个任意字符开头，省组可以匹配上也可以匹配不上，市前可有2-3个任意字符
        Pattern pattern = Pattern.compile("^(.{2,3}省)?(.{2,3}市)");
        Matcher matcher = pattern.matcher(str1);
        boolean str1Matched = matcher.find();
        System.out.println("str1 match result: " + str1Matched);
        if(str1Matched) {
            System.out.println(matcher.group(matcher.groupCount()));
        }
        matcher = pattern.matcher(str2);
        boolean str2Matched = matcher.find();
        System.out.println("str2 match result: " + str2Matched);
        if(str2Matched) {
            System.out.println(matcher.group(matcher.groupCount()));
        }
    }

    @Test
    public void findCity2Test() {
        String str1 = "浙江省杭州市天目山路176号数源科技园11号楼3F";
        String str2 = "杭州市天目山路176号数源科技园11号楼3F";
        //正则表达式解释: 以省前可有2-3个任意字符开头，省组进行反向查询(匹配的省不会拼接上后面的字符), 省组可以匹配上也可以匹配不上，或者匹配市前可有2-3个任意字符开头
        Pattern pattern = Pattern.compile("(?<=(^.{2,3}省)|^).{2,3}市");
        Matcher matcher = pattern.matcher(str1);
        boolean str1Matched = matcher.find();
        System.out.println("str1 match result: " + str1Matched);
        if(str1Matched) {
            System.out.println(matcher.group());
        }
        matcher = pattern.matcher(str2);
        boolean str2Matched = matcher.find();
        System.out.println("str2 match result: " + str2Matched);
        if(str2Matched) {
            System.out.println(matcher.group());
        }
    }


}

