package cn.t.util.common;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

public class Assert {

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }


    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }


    public static void hasLength(String text, String message) {
        if (StringUtil.isEmpty(text)) {
            throw new IllegalArgumentException(message);
        }
    }


    public static void hasText(String text, String message) {
        if (StringUtil.isEmpty(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void noNullElements(Object[] array, String message) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new IllegalArgumentException(message);
                }
            }
        }
    }


    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtil.isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }


    public static void notEmpty(Map<?, ?> map, String message) {
        if (CollectionUtil.isEmpty(map)) {
            throw new IllegalArgumentException(message);
        }
    }


    public static void isInstanceOf(Class<?> type, Object obj, String message) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            instanceCheckFailed(type, obj, message);
        }
    }

    public static void isInstanceOf(Class<?> type, Object obj) {
        isInstanceOf(type, obj, "");
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
        notNull(superType, "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            assignableCheckFailed(superType, subType, message);
        }
    }

    public static void isAssignable(Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, "");
    }


    private static void instanceCheckFailed(Class<?> type, Object obj, String msg) {
        String className = (obj != null ? obj.getClass().getName() : "null");
        String result = buildExceptionMessage(msg, className, (t) -> t + ("Object of class [" + className + "] must be an instance of " + type));
        throw new IllegalArgumentException(result);
    }

    private static void assignableCheckFailed(Class<?> superType, Class<?> subType, String msg) {
        String result = buildExceptionMessage(msg, subType, (t) -> t + (subType + " is not assignable to " + superType));
        throw new IllegalArgumentException(result);
    }

    private static boolean endsWithSeparator(String msg) {
        return (msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith("."));
    }

    private static String messageWithTypeName(String msg, Object typeName) {
        if(msg.endsWith(" ")) {
            return msg + typeName;
        } else {
            return msg + ": " + typeName;
        }
    }

    private static String buildExceptionMessage(String msg, Object typeName, Function<String, String> callback) {
        String result = "";
        boolean defaultMessage = true;
        if (!StringUtil.isEmpty(msg)) {
            if (endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = messageWithTypeName(msg, typeName);
                defaultMessage = false;
            }
        }
        if(defaultMessage) {
            result = callback.apply(result);
        }
        return result;
    }
}
