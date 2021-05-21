package cn.t.util.common.reflect;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author yj
 * @since 2020-02-08 09:38
 **/
public class ReflectUtil {

    private final static WeakHashMap<String, Field> weakHashMap = new WeakHashMap<>();

    @SuppressWarnings("unchecked")
    public static Object accessValue(Object obj, String key) {
        if(obj instanceof Map) {
            return ((Map<String, Object>)obj).get(key);
        } else {
            try {
                Class<?> objectClass = obj.getClass();
                String cacheKey = buildCacheKey(objectClass.getName(), key);
                Field field = weakHashMap.get(cacheKey);
                if(field == null) {
                    synchronized (cacheKey.intern()) {
                        field = weakHashMap.get(cacheKey);
                        if(field == null) {
                            field = obj.getClass().getDeclaredField(key);
                            field.setAccessible(true);
                            weakHashMap.put(cacheKey, field);
                        }
                    }
                }
                return field.get(obj);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return null;
            }
        }
    }
    private static String buildCacheKey(String className, String key) {
        return className + "#" + key;
    }
}
