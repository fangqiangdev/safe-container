package cn.truthseeker.container.safe;

import cn.truthseeker.util.Emptys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collector;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class NoneNullList<E> extends ArrayList<E> implements CommonNoneNullOper<E> {
    public NoneNullList() {
        super();
    }

    public NoneNullList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean add(E element) {
        Objects.requireNonNull(element);
        return super.add(element);
    }

    @Override
    public void add(int index, E element) {
        Objects.requireNonNull(element);
        super.add(index, element);
    }

    @Override
    public E set(int index, E element) {
        Objects.requireNonNull(element);
        return super.set(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Emptys.assertNoneNull(c);
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Emptys.assertNoneNull(c);
        return super.addAll(index, c);
    }

    // 简化操作
    public <R> NoneNullList<R> map(Function<E, R> function) {
        return stream().map(function).collect(collector());
    }

    public <R> NoneNullList<R> mapIgnoreNull(Function<E, R> function) {
        return stream().map(function).collect(collectorIgnoreNull());
    }

    // 构造工具
    public static <E> NoneNullList<E> of(E... e) {
        return Collections2.of(NoneNullList::new, e);
    }

    public static <E> NoneNullList<E> of(Iterable<E> e) {
        return Collections2.of(NoneNullList::new, e);
    }

    public static <E> NoneNullList<E> ofIgnoreNull(E... e) {
        NoneNullList<E> ret = new NoneNullList<>();
        ret.addAllIgnoreNull(e);
        return ret;
    }

    public static <E> NoneNullList<E> ofIgnoreNull(Iterable<E> e) {
        NoneNullList<E> ret = new NoneNullList<>();
        ret.addAllIgnoreNull(e);
        return ret;
    }

    public static <E> Collector<E, ?, NoneNullList<E>> collector() {
        return Collector.of(
                NoneNullList::new,
                NoneNullList::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                },
                Collector.Characteristics.IDENTITY_FINISH
        );
    }

    public static <E> Collector<E, ?, NoneNullList<E>> collectorIgnoreNull() {
        return Collector.of(
                NoneNullList::new,
                NoneNullList::addIgnoreNull,
                (left, right) -> {
                    left.addAllIgnoreNull(right);
                    return left;
                },
                Collector.Characteristics.IDENTITY_FINISH
        );
    }
}