package cn.t.util.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    private static final Pattern IP_MATCH_REG = Pattern.compile("^\\b(\\d{1,3}\\.){3}\\d{1,3}\\b$");
    private static final Pattern IP_REG = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");

    private static final Pattern INSERT_SQL_TABLE_NAME_REG = Pattern.compile("into\\s+([1-9_a-z]+)");

    public static boolean isIp(String ip) {
        Matcher matcher = IP_MATCH_REG.matcher(ip);
        return matcher.find();
    }

    public static String getIp(String str) {
        Matcher matcher = IP_REG.matcher(str);
        if(matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }

    public static String getInsertSqlTableName(String sql) {
        Matcher matcher = INSERT_SQL_TABLE_NAME_REG.matcher(sql);
        if(matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

}
