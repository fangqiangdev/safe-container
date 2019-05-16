package cn.truthseeker.container.safe;


import cn.truthseeker.util.Emptys;

import javax.annotation.CheckForNull;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/4/7
 */
public class NoneNullMap<K, V> extends HashMap<K, V> implements CommonNoneNullMapOper<K, V> {
    public NoneNullMap() {
        super();
    }

    public NoneNullMap(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            Emptys.assertNotNull(entry.getKey());
            Emptys.assertNotNull(entry.getValue());
        }

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

    @Override
    @CheckForNull
    public V get(Object key) {
        return super.get(key);
    }

    // 构造工具

    /**
     * 快速构建方法
     */
    public static <K, V> NoneNullMap<K, V> of(K k1, V v1) {
        return Maps.of(k1, v1, NoneNullMap::new);
    }

    /**
     * 快速构建方法
     */
    public static <K, V> NoneNullMap<K, V> of(K k1, V v1, K k2, V v2) {
        return Maps.of(k1, v1, k2, v2, NoneNullMap::new);
    }

    /**
     * 快速构建方法
     */
    public static <K, V> NoneNullMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return Maps.of(k1, v1, k2, v2, k3, v3, NoneNullMap::new);
    }

    /**
     * 快速构建方法，忽略null元素
     */
    public static <K, V> NoneNullMap<K, V> ofOmitNullElement(Map<K, V> m) {
        NoneNullMap<K, V> ret = new NoneNullMap<>();
        ret.putAllIfNotNull(m);
        return ret;
    }

    // 简化操作

    /**
     * Map<K,V> -> Map<RK,V>
     */
    public <RK> NoneNullMap<RK, V> mapByKey(Function<K, RK> kFun) {
        return Maps.mapByKey(this, kFun, NoneNullMap::new);
    }

    /**
     * Map<K,V> -> Map<K,RV>
     */
    public <RV> NoneNullMap<K, RV> mapByValue(Function<V, RV> vFun) {
        return Maps.mapByValue(this, vFun, NoneNullMap::new);
    }

    /**
     * Map<K,V> -> Map<RK,RV>
     */
    public <RK, RV> NoneNullMap<RK, RV> mapByKeyValue(Function<K, RK> kFun, Function<V, RV> vFun) {
        return Maps.mapByKeyValue(this, kFun, vFun, NoneNullMap::new);
    }

    /**
     * Map1 (按key过滤) -> Map2
     */
    public NoneNullMap<K, V> filterByKey(Predicate<K> kFun) {
        return Maps.filterByKey(this, kFun, NoneNullMap::new);
    }

    /**
     * Map1 (按value过滤) -> Map2
     */
    public NoneNullMap<K, V> filterByValue(Predicate<V> vFun) {
        return Maps.filterByValue(this, vFun, NoneNullMap::new);
    }

    /**
     * Map1 (按key和value过滤) -> Map2
     */
    public NoneNullMap<K, V> filterByKeyValue(BiPredicate<K, V> predicate) {
        return Maps.filterByKeyValue(this, predicate, NoneNullMap::new);
    }

    /**
     * 对keys中每个元素从map中提取出value，并放入一个新的map
     */
    public NoneNullMap<K, V> getSubMap(Collection<K> keys) {
        return Maps.getSubMap(this, keys, NoneNullMap::new);
    }
}
