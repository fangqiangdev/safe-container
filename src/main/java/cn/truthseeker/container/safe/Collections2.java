package cn.truthseeker.container.safe;

import cn.truthseeker.util.Assert;
import cn.truthseeker.util.Emptys;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/21
 */
public class Collections2 {

    public static <E> void clearEmpty(Collection<E> e) {
        e.removeIf(Emptys::isEmpty);
    }

    public static <E> void clearNull(Collection<E> e) {
        e.removeIf(Objects::isNull);
    }

    public static <E> List<E> ofList(E... e) {
        return of(ArrayList::new, e);
    }

    public static <E> Set<E> ofSet(E... e) {
        return of(HashSet::new, e);
    }

    public static <E, V> List<V> map(Function<E, V> function, E... e) {
        List<V> list = new ArrayList<>(e.length);
        for (E e1 : e) {
            list.add(function.apply(e1));
        }
        return list;
    }

    public static <E, V> List<V> map(Function<E, V> function, Collection<E> e) {
        List<V> list = new ArrayList<>(e.size());
        for (E e1 : e) {
            list.add(function.apply(e1));
        }
        return list;
    }

    public static <E, T extends Collection<E>> T of(Supplier<T> supplier, E... e) {
        T t = supplier.get();
        for (E e1 : e) {
            t.add(e1);
        }
        return t;
    }

    public static <E, T extends Collection<E>> T of(Supplier<T> supplier, Iterable<E> e) {
        T t = supplier.get();
        for (E e1 : e) {
            t.add(e1);
        }
        return t;
    }

    public static <K, V> Map<K, V> toHashMap(Iterable<K> objects, Function<K, V> function) {
        return toMap(objects, function, HashMap::new);
    }

    public static <K, V, T extends Map<K, V>> T toMap(Iterable<K> objects, Function<K, V> function, Supplier<T> supplier) {
        T t = supplier.get();
        for (K k : objects) {
            t.put(k, function.apply(k));
        }
        return t;
    }

    public static <K, RK, RV> Map<RK, RV> toHashMap(Iterable<K> objects, Function<K, RK> kFun, Function<K, RV> vFun) {
        return toMap(objects, kFun, vFun, HashMap::new);
    }

    public static <K, RK, RV, T extends Map<RK, RV>> T toMap(Iterable<K> objects, Function<K, RK> kFun, Function<K, RV> vFun, Supplier<T> supplier) {
        T t = supplier.get();
        for (K k : objects) {
            t.put(kFun.apply(k), vFun.apply(k));
        }
        return t;
    }

    public static <K, V> Map<K, V> toHashMap(K[] objects, Function<K, V> function) {
        return toMap(objects, function, HashMap::new);
    }

    public static <K, V, T extends Map<K, V>> T toMap(K[] objects, Function<K, V> function, Supplier<T> supplier) {
        T t = supplier.get();
        for (K k : objects) {
            t.put(k, function.apply(k));
        }
        return t;
    }

    public static <K, RK, RV> Map<RK, RV> toHashMap(K[] objects, Function<K, RK> kFun, Function<K, RV> vFun) {
        return toMap(objects, kFun, vFun, HashMap::new);
    }

    public static <K, RK, RV, T extends Map<RK, RV>> T toMap(K[] objects, Function<K, RK> kFun, Function<K, RV> vFun, Supplier<T> supplier) {
        T t = supplier.get();
        for (K k : objects) {
            t.put(kFun.apply(k), vFun.apply(k));
        }
        return t;
    }

    public static <T> boolean anySatisfied(Iterable<T> objects, Predicate<T> predicate) {
        for (T object : objects) {
            if (predicate.test(object)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean anySatisfied(T[] objects, Predicate<T> predicate) {
        for (T object : objects) {
            if (predicate.test(object)) {
                return true;
            }
        }
        return false;
    }

    public static <E> void assertTrue(Iterable<E> objects, Predicate<E> predicate, String errMsg) {
        for (E e : objects) {
            Assert.isTrue(predicate.test(e), errMsg);
        }
    }

    public static <E> void assertTrue(E[] objects, Predicate<E> predicate, String errMsg) {
        for (E e : objects) {
            Assert.isTrue(predicate.test(e), errMsg);
        }
    }
}