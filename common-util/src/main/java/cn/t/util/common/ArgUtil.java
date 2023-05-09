package cn.t.util.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ArgUtil {

    public static Map<String, String> resolveMainArgs(String[] argArr) {
        if(ArrayUtil.isEmpty(argArr)) {
            return Collections.emptyMap();
        } else {
            return getArgMap(argArr);
        }
    }

    public static Map<String, String> resolveArgs(String argStr) {
        return doResolveArgs(preDealArgStr(argStr));
    }

    private static String preDealArgStr(String argStr) {
        if(argStr == null) {
            argStr = "";
        }
        while (argStr.contains("  ")) {
            argStr = argStr.replace("  ", " ");
        }
        return argStr;
    }

    private static Map<String, String> doResolveArgs(String args) {
        if(args.length() == 0) {
            return Collections.emptyMap();
        }
        String[] entries = args.split(" ");
        return getArgMap(entries);
    }

    private static Map<String, String> getArgMap(String[] argArr) {
        Map<String, String> mapping = new HashMap<>();
        for(String entry: argArr) {
            String[] kv = entry.split("=");
            mapping.put(kv[0], kv.length == 1 ? "" : kv[1]);
        }
        return mapping;
    }
}
