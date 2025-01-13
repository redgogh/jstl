package org.redgogh.cleantools.itertools;

import org.redgogh.cleantools.collection.Lists;
import org.redgogh.cleantools.tuple.Tuple;

import java.util.Collection;
import java.util.List;

/**
 * `Itertools` 是一个工具类，提供了一系列操作集合或迭代器的静态方法。
 *
 * <p>该类中的方法旨在简化对集合或迭代器的常见操作，如配对、分组、排序等。通过简洁的接口，
 * 可以方便地处理迭代器相关的常见任务。
 *
 * <p>主要特点：
 * <ul>
 *     <li>提供了对迭代器和集合的灵活操作方法。</li>
 *     <li>支持泛型操作，增强了方法的适用性和灵活性。</li>
 *     <li>简化了集合或迭代器的处理流程，避免了冗长的代码。</li>
 * </ul>
 *
 * <h2>使用示例</h2>
 * <pre>
 *     // 使用 zip 方法配对两个集合
 *     Iterable<Tuple<String, Integer>> zipped = Itertools.zip(names, ages);
 *
 *     // 通过 map 方法对集合中的元素进行转换
 *     List<String> uppercased = Itertools.map(names, String::toUpperCase);
 * </pre>
 *
 * <h2>注意事项</h2>
 * <ul>
 *     <li>部分方法要求传入的集合或迭代器不能为空。</li>
 *     <li>本类中的所有方法均为静态方法，调用时无需创建对象。</li>
 * </ul>
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
