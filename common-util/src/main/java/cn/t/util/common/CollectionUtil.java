package cn.t.util.common;

import java.util.*;

public class CollectionUtil {

    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    public static boolean isSizeEqualAndGreaterThan(Collection<?> collection, int size) {
        return collection != null && collection.size() >= size;
    }

    public static boolean isSizeEqualAndLessThan(Collection<?> collection, int size) {
        return collection == null || collection.size() <= size;
    }

    public static <T> List<List<T>> splitList(List<T> list) {
        if(isEmpty(list)) {
            return Collections.emptyList();
        }
        int maxHandleEachTime = 10;
        int times = list.size() / maxHandleEachTime;
        List<List<T>> result = new ArrayList<>(times);
        for(int i=0; i<times; i++) {
            int startIndex = i * maxHandleEachTime;
            result.add(list.subList(startIndex, startIndex + maxHandleEachTime));
        }
        int remain = list.size()%maxHandleEachTime;
        if(remain > 0) {
            int startIndex = times * maxHandleEachTime;
            result.add(list.subList(times * maxHandleEachTime, startIndex + remain));
        }
        return result;
    }

    public static String join(List<String> strList, String delimiter) {
        if(strList == null) {
            return null;
        } else if(strList.size() == 0) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            if(StringUtil.isEmpty(delimiter)) {
                for(String str: strList) {
                    builder.append(str);
                }
            } else {
                for(String str: strList) {
                    builder.append(str).append(delimiter);
                }
            }
            int end = builder.length();
            int begin = end - delimiter.length();
            return builder.delete(begin, end).toString();
        }
    }
}
