package cn.truthseeker.util;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/17
 */
public class Emptys {
    public static void assertNotNull(Object o) {
        if (o == null) {
            throw new RuntimeException("NullValueException");
        }
    }

    public static void assertNoneNull(Iterable objects) {
        if (!isNoneNull(objects)) {
            throw new RuntimeException("NullValueException");
        }
    }

    public static void assertNoneNull(Object... objects) {
        if (!isNoneNull(objects)) {
            throw new RuntimeException("NullValueException");
        }
    }

    public static void assertNotEmpty(Object o) {
        if (isEmpty(o)) {
            throw new RuntimeException("EmptyValueException");
        }
    }

    public static void assertNoneEmpty(Iterable objects) {
        if (!isNoneEmpty(objects)) {
            throw new RuntimeException("EmptyValueException");
        }
    }

    public static void assertNoneEmpty(Object... objects) {
        if (!isNoneEmpty(objects)) {
            throw new RuntimeException("EmptyValueException");
        }
    }

    public static boolean isAnyNull(Iterable objects) {
        return !isNoneNull(objects);
    }

    public static boolean isAnyNull(Object... objects) {
        return !isNoneNull(objects);
    }

    public static boolean isNoneNull(Iterable objects) {
        for (Object object : objects) {
            if (object == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNoneNull(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnyEmpty(Iterable objects) {
        return !isNoneEmpty(objects);
    }

    public static boolean isAnyEmpty(Object... objects) {
        return !isNoneEmpty(objects);
    }

    public static boolean isNoneEmpty(Iterable objects) {
        for (Object object : objects) {
            if (isEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNoneEmpty(Object... objects) {
        for (Object object : objects) {
            if (isEmpty(object)) {
                return false;
            }
        }
        return true;
    }

    public static boolean oneNull(Object a, Object b) {
        return a == null ^ b == null;
    }

    public static boolean oneEmpty(String a, String b) {
        return isEmpty(a) ^ isEmpty(b);
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    /**
     * 1. ??????null
     * 2. String???????????????????????????
     * 3. ?????????????????????
     * 3. ??????????????????0
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof String) {
            return "".equals(((String) object).trim());
        } else if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        } else if (object instanceof Map) {
            return ((Map) object).isEmpty();
        } else if (object instanceof Object[]) {
            return ((Object[]) object).length == 0;
        } else if (object instanceof byte[]) {
            return ((byte[]) object).length == 0;
        } else if (object instanceof char[]) {
            return ((char[]) object).length == 0;
        } else if (object instanceof int[]) {
            return ((int[]) object).length == 0;
        } else if (object instanceof boolean[]) {
            return ((boolean[]) object).length == 0;
        } else if (object instanceof long[]) {
            return ((long[]) object).length == 0;
        } else if (object instanceof double[]) {
            return ((double[]) object).length == 0;
        } else if (object instanceof float[]) {
            return ((float[]) object).length == 0;
        } else if (object instanceof short[]) {
            return ((short[]) object).length == 0;
        }

        return false;
    }

    public static <T, R> R applyIfNotNull(T object, Function<T, R> function) {
        return object == null ? null : function.apply(object);
    }
}
