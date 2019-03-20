package cn.truthseeker.container.safe.list;

import cn.truthseeker.container.safe.Safes;
import cn.truthseeker.container.safe.map.Maps;
import cn.truthseeker.container.safe.map.SafeHashMap;
import cn.truthseeker.container.safe.map.SafeMap;
import cn.truthseeker.container.util.Emptys;
import cn.truthseeker.container.util.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public interface SafeList<E> extends List<E> {
    default void addIgnoreNull(E e){
        if(e != null){
            add(e);
        }
    }

    default void addIgnoreEmpty(E e){
        if(Emptys.isNotEmpty(e)){
            add(e);
        }
    }

    default SafeList<E> cleanEmpty(){
        removeIf(Emptys::isEmpty);
        return this;
    }

    default boolean anySatisfied(Predicate<E> predicate){
        return Utils.anySatisfied(this, predicate);
    }

    default SafeList add2(E e){
        add(e);
        return this;
    }

    default <V> Map<E,V> toMap(Function<E,V> function){
        Map<E,V> ret = new HashMap();
        for (E k : this) {
            ret.put(k, function.apply(k));
        }
        return ret;
    }

    default <V> SafeMap<E,V> toSafeMapOmitNull(Function<E,V> function){
        SafeMap<E,V> ret = Safes.newSafeMap();
        for (E k : this) {
            V v = function.apply(k);
            if(v!=null) {
                ret.put(k, v);
            }
        }
        return ret;
    }
}