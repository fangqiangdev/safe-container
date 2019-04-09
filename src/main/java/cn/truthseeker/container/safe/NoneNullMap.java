package cn.truthseeker.container.safe;


import cn.truthseeker.container.util.Emptys;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/4/7
 */
public class NoneNullMap<K, V> extends HashMap<K, V> implements CommonMapOper<K, V> {
    public NoneNullMap() {
        super();
    }

    public NoneNullMap(Map<? extends K, ? extends V> m) {
        super();
        putAll(m);
    }

    public NoneNullMap(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V put(K key, V value) {
        Emptys.assertNotNull(key);
        Emptys.assertNotNull(value);
        return super.put(key, value);
    }

    @Deprecated
    @Override
    public V get(Object key) {
        return super.get(key);
    }

    // 构造工具
    public static <K, V> NoneNullMap<K, V> of(K k1, V v1) {
        return Maps.of(k1, v1, NoneNullMap::new);
    }

    public static <K, V> NoneNullMap<K, V> of(K k1, V v1, K k2, V v2) {
        return Maps.of(k1, v1, k2, v2, NoneNullMap::new);
    }

    public static <K, V> NoneNullMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return Maps.of(k1, v1, k2, v2, k3, v3, NoneNullMap::new);
    }

    public static <K, V> Collector<Entry<K, V>, ?, NoneNullMap<K, V>> getCollector() {
        return Collector.of(
                NoneNullMap::new,
                (map, entry) -> map.put(entry.getKey(), entry.getValue()),
                (left, right) -> {
                    left.putAll(right);
                    return left;
                },
                Collector.Characteristics.IDENTITY_FINISH
        );
    }

    // 简化操作

    /**
     * 按key映射
     */
    public <RK> NoneNullMap<RK, V> mapKey(Function<K, RK> kFun) {
        return Maps.mapKey(this, kFun, NoneNullMap::new);
    }

    /**
     * 按value映射
     */
    public <RV> NoneNullMap<K, RV> mapValue(Function<V, RV> vFun) {
        return Maps.mapValue(this, vFun, NoneNullMap::new);
    }

    /**
     * 按key,value映射
     */
    public <RK, RV> NoneNullMap<RK, RV> mapKeyValue(Function<K, RK> kFun, Function<V, RV> vFun) {
        return Maps.mapKeyValue(this, kFun, vFun, NoneNullMap::new);
    }

    /**
     * 按key过滤
     */
    public NoneNullMap<K, V> filterByKey(Predicate<K> kFun) {
        return Maps.filterByKey(this, kFun, NoneNullMap::new);
    }

    /**
     * 按value过滤
     */
    public NoneNullMap<K, V> filterByValue(Predicate<V> vFun) {
        return Maps.filterByValue(this, vFun, NoneNullMap::new);
    }

    /**
     * 按key,value过滤
     */
    public NoneNullMap<K, V> filterByKeyValue(BiPredicate<K, V> predicate) {
        return Maps.filterByKeyValue(this, predicate, NoneNullMap::new);
    }

    /**
     * 按keys返回子map
     */
    public NoneNullMap<K, V> getSubMap(Collection<K> keys) {
        return Maps.getSubMap(this, keys, NoneNullMap::new);
    }
}