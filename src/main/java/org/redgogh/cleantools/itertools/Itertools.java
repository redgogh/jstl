package org.redgogh.cleantools.itertools;

import org.redgogh.cleantools.collect.Lists;
import org.redgogh.cleantools.tuple.Tuple;

import java.util.Collection;
import java.util.List;

/**
 * `Itertools` 是一个工具类，提供了一系列操作集合或迭代器的静态方法。
 *
 * <p>该类中的方法旨在简化对集合或迭代器的常见操作，如配对、分组、排序等。通过简洁的接口，
 * 可以方便地处理迭代器相关的常见任务。
 *
 * @author RedGogh
 * @since 1.0
 */
public class Itertools {

    /**
     * #brief: 将两个集合按顺序配对，返回元组的可迭代对象
     *
     * <p>该方法将两个集合中的元素按顺序配对，生成一个包含元组的可迭代对象。每个元组的左值来自
     * 第一个集合，右值来自第二个集合。适用于需要将两个集合的元素一一对应地处理的场景。
     *
     * @param lefts 第一个集合，元素将作为元组的左值
     * @param rights 第二个集合，元素将作为元组的右值
     * @param <L> 左集合中元素的类型
     * @param <R> 右集合中元素的类型
     * @return 包含配对元组的可迭代对象
     * @throws IllegalArgumentException 如果两个集合的大小不同
     */
    public static <L, R> Iterable<Tuple<L, R>> zip(Collection<L> lefts, Collection<R> rights) {
        // 计算最小总数
        int count = lefts == null || rights == null ? 0 : Math.min(lefts.size(), rights.size());

        if (count == 0)
            return Lists.of();

        List<Tuple<L, R>> retval = Lists.of();

        List<L> leftList = Lists.of(lefts);
        List<R> rightList = Lists.of(rights);

        for (int i = 0; i < count; i++)
            retval.add(new Tuple<>(leftList.get(i), rightList.get(i)));

        return retval;
    }

}
