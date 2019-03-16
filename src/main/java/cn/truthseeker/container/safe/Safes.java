package cn.truthseeker.container.safe;

import cn.truthseeker.container.safe.list.SafeArrayList;
import cn.truthseeker.container.safe.list.SafeLinkedList;
import cn.truthseeker.container.safe.list.SafeList;
import cn.truthseeker.container.safe.map.Maps;
import cn.truthseeker.container.safe.map.SafeHashMap;
import cn.truthseeker.container.safe.map.SafeMap;
import cn.truthseeker.container.safe.map.SafeTreeMap;
import cn.truthseeker.container.safe.set.SafeHashSet;
import cn.truthseeker.container.safe.set.SafeSet;
import cn.truthseeker.container.safe.set.SafeTreeSet;
import cn.truthseeker.container.util.Utils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/15
 */
public final class Safes {

    public static <K, V> SafeMap<K, V> newSafeMap() {
        return new SafeHashMap();
    }

    public static <K, V> SafeMap<K, V> newSafeSortMap() {
        return new SafeTreeMap();
    }

    /**
     * 包装成SafeMap，并将其中的null值（key或value为null）删除
     */
    public static <K, V> SafeMap<K, V> newSafeMapOmitNull(Map<K, V> map) {
        return Maps.filterByKeyValue(map, (k, v) -> Utils.isNoneNull(k, v), SafeHashMap::new);
    }

    /**
     * 包装成SafeMap，并将其中的null值（key或value为null）删除
     */
    public static <K, V> SafeMap<K, V> newSafeSortMapOmitNull(Map<K, V> map) {
        return Maps.filterByKeyValue(map, (k, v) -> Utils.isNoneNull(k, v), SafeTreeMap::new);
    }

    /**
     * 包装成SafeMap，并将其中的空值（key或value为null或是一个空字符串）删除
     */
    public static <K, V> SafeMap<K, V> newSafeMapOmitEmpty(Map<K, V> map) {
        return Maps.filterByKeyValue(map, (k, v) -> Utils.isNoneEmpty(k, v), SafeHashMap::new);
    }

    /**
     * 包装成SafeMap，并将其中的空值（key或value为null或是一个空字符串）删除
     */
    public static <K, V> SafeMap<K, V> newSafeSortMapOmitEmpty(Map<K, V> map) {
        return Maps.filterByKeyValue(map, (k, v) -> Utils.isNoneEmpty(k, v), SafeTreeMap::new);
    }


    public static <E> SafeList<E> newSafeList() {
        return new SafeArrayList<>();
    }

    public static <E> SafeList<E> newSafeLinkedList() {
        return new SafeLinkedList<>();
    }

    /**
     * 包装成SafeList，并将其中的null值（key或value为null）删除
     */
    public static <E> SafeList<E> newSafeListOmitNull(List<E> list) {
        return list.stream().filter(Objects::nonNull).collect(Collectors.toCollection(SafeArrayList::new));
    }

    /**
     * 包装成SafeList，并将其中的null值（key或value为null）删除
     */
    public static <E> SafeList<E> newSafeLinkedListOmitNull(List<E> list) {
        return list.stream().filter(Objects::nonNull).collect(Collectors.toCollection(SafeLinkedList::new));
    }

    /**
     * 包装成SafeList，并将其中的空值（key或value为null或是一个空字符串）删除
     */
    public static <E> SafeList<E> newSafeListOmitEmpty(List<E> list) {
        return list.stream().filter(Utils::isNotEmpty).collect(Collectors.toCollection(SafeArrayList::new));
    }

    /**
     * 包装成SafeList，并将其中的空值（key或value为null或是一个空字符串）删除
     */
    public static <E> SafeList<E> newSafeLinkedListOmitEmpty(List<E> list) {
        return list.stream().filter(Utils::isNotEmpty).collect(Collectors.toCollection(SafeLinkedList::new));
    }

    public static <E> SafeSet<E> newSafeSet() {
        return new SafeHashSet<>();
    }

    public static <E> SafeSet<E> newSafeSortSet() {
        return new SafeTreeSet<>();
    }

    /**
     * 包装成SafeSet，并将其中的null值（key或value为null）删除
     */
    public static <E> SafeSet<E> newSafeSetOmitNull(List<E> list) {
        return list.stream().filter(Objects::nonNull).collect(Collectors.toCollection(SafeHashSet::new));
    }

    /**
     * 包装成SafeSet，并将其中的null值（key或value为null）删除
     */
    public static <E> SafeSet<E> newSafeSortSetOmitNull(List<E> list) {
        return list.stream().filter(Objects::nonNull).collect(Collectors.toCollection(SafeTreeSet::new));
    }

    /**
     * 包装成SafeSet，并将其中的空值（key或value为null或是一个空字符串）删除
     */
    public static <E> SafeSet<E> newSafeSetOmitEmpty(List<E> list) {
        return list.stream().filter(Utils::isNotEmpty).collect(Collectors.toCollection(SafeHashSet::new));
    }

    /**
     * 包装成SafeSet，并将其中的空值（key或value为null或是一个空字符串）删除
     */
    public static <E> SafeSet<E> newSafeSortSetOmitEmpty(List<E> list) {
        return list.stream().filter(Utils::isNotEmpty).collect(Collectors.toCollection(SafeTreeSet::new));
    }
}