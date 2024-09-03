package com.redgogh.jdkext.collection;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 RedGogh                                                    *|
|*                                                                                  *|
|*    This program is free software: you can redistribute it and/or modify          *|
|*    it under the terms of the GNU General Public License as published by          *|
|*    the Free Software Foundation, either version 3 of the License, or             *|
|*    (at your option) any later version.                                           *|
|*                                                                                  *|
|*    This program is distributed in the hope that it will be useful,               *|
|*    but WITHOUT ANY WARRANTY; without even the implied warranty of                *|
|*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                 *|
|*    GNU General Public License for more details.                                  *|
|*                                                                                  *|
|*    You should have received a copy of the GNU General Public License             *|
|*    along with this program.  If not, see <https://www.gnu.org/licenses/>.        *|
|*                                                                                  *|
|*    This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.    *|
|*    This is free software, and you are welcome to redistribute it                 *|
|*    under certain conditions; type `show c' for details.                          *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

/* Creates on 2023/5/6. */

import com.redgogh.jdkext.StreamMapping;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 容器工具类
 *
 * @author RedGogh   
 */
@SuppressWarnings("DuplicatedCode")
public class Collects {

    @SuppressWarnings("rawtypes")
    public static final List EMPTY_LIST = java.util.Collections.EMPTY_LIST;

    @SuppressWarnings("rawtypes")
    public static final Set EMPTY_SET = java.util.Collections.EMPTY_SET;

    /**
     * 判断参数 {@code a} 实现了 {@link Collection} 接口的对象实例，是否为空。如果要满足函数
     * 返回 {@code false} 那么该对象不能为 {@code null} 并且该对象的 {@code size} 必须大
     * 于 0。反之返回 {@code true}。<p>
     *
     * <p>
     * 这个函数主要的作用在于判断各种 {@link List}、{@link Set} 等继承了 {@link Collection}
     * 接口的子接口。
     *
     * @param a
     *        一个 {@link Collection} 接口对象实例
     *
     * @return {@code false} 表示集合不为空，集合内有真实的数据。反之 {@code true} 表示集合
     *         内是空的，没有真实的任何数据。
     *
     * @see List
     * @see Set
     */
    public static <E> boolean isEmptyList(Collection<E> a) {
        return a == null || a.isEmpty();
    }

    /**
     * 判断参数 {@code map} 实现了 {@link Map} 接口的对象实例，是否为空。如果要满足函数
     * 返回 {@code false} 那么该对象不能为 {@code null} 并且该对象的 {@code size} 必须大
     * 于 0。反之返回 {@code true}。<p>
     *
     * <p>
     * 这个函数主要的作用在于判断各种 {@link LinkedHashMap}、{@link HashMap} 等继承了
     * {@link Map} 接口的子接口。
     *
     * @param map
     *        一个 {@link Map} 接口对象实例
     *
     * @return {@code false} 表示集合不为空，集合内有真实的数据。反之 {@code true} 表示集合
     *         内是空的，没有真实的任何数据。
     *
     * @see List
     * @see Set
     */
    public static <K, V> boolean isEmptyMap(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    /**
     * @return 返回 {@link Collection} 接口实例中的第一个元素，如果 {@link Collection}
     *         对象为 {@code null} 或 {@code size} == 0 那么则会返回 {@code null}
     */
    @SuppressWarnings("unchecked")
    public static <E> E collectionBeg(Collection<E> collection) {
        if (collection == null || collection.isEmpty())
            return null;
        Object[] array = collection.toArray();
        return (E) array[0];
    }

    /**
     * @return 返回 {@link Collection} 接口实例中的最后一个元素，如果 {@link Collection}
     *         对象为 {@code null} 或 {@code size} == 0 那么则会返回 {@code null}
     */
    @SuppressWarnings("unchecked")
    public static <E> E collectionEnd(Collection<E> collection) {
        if (collection == null || collection.isEmpty())
            return null;
        Object[] array = collection.toArray();
        return (E) array[array.length - 1];
    }

    //////////////////////////////////////////////////////////////////////////////////////
    /// List
    //////////////////////////////////////////////////////////////////////////////////////

    /**
     * 传入一个 {@link List} 对象，拷贝参数 {@code a} 中的数据到新的 {@link ArrayList} 对象中，数据拷贝
     * 过去以后，修改原来的 {@link List} 对象或修改新分配到对象，并不会影响两个对象之间的数据。
     *
     * @param a 需要拷贝的数据
     * @return 拷贝后的新 {@link ArrayList} 对象实例
     */
    public static <E> List<E> listCopy(Collection<E> a) {
        return new ArrayList<>(a);
    }

    /**
     * @return 分配一个空的 {@link ArrayList} 集合对象实例。
     */
    public static <E> List<E> asList() {
        return new ArrayList<>();
    }

    /**
     * 通过传入的泛型可变参数去分配一个 {@link ArrayList} 集合对象实例。泛型可变参数不能为空
     * 否则会抛出 {@link NullPointerException} 异常。
     *
     * @param a
     *        泛型可变参数数组
     *
     * @return 一个新的 {@link ArrayList} 对象实例
     *
     * @throws NullPointerException
     *         如果泛型参数为空值
     *
     * @see #asList(Collection)
     */
    @SafeVarargs
    public static <E> ArrayList<E> asList(E... a) {
        return asList(Arrays.asList(a));
    }

    /**
     * 截取数组 {@code a} 通过 {@code off} 和 {@code len} 两个参数去截取，截取出来的新数组会
     * 分配成一个可变的 {@link ArrayList} 对象实例。
     *
     * @param a
     *        泛型可变参数数组
     *
     * @param off
     *        泛型数组开始的索引位置，偏移量。
     *
     * @param len
     *        要截取的长度，这个长度范围应该在 <= {@code a}.length()。如果长度超出 {@code a} 数组
     *        的大小，会抛出 {@link ArrayIndexOutOfBoundsException} 异常。
     *
     * @return 一个新的 {@link ArrayList} 对象实例
     *
     * @throws NullPointerException
     *         如果泛型参数为空值。
     *
     * @throws ArrayIndexOutOfBoundsException
     *         如果 {@code len} 超出整个 {@code a} 数组大小的长度。
     *
     * @see #asList(Collection)
     */
    @SuppressWarnings("unchecked")
    public static <E> ArrayList<E> asList(E[] a, int off, int len) {
        return (ArrayList<E>) asList(Arrays.asList(a, off, len));
    }

    /**
     * 通过传入的 {@link Collection} 实例去分配一个 {@link ArrayList} 集合对象实例。{@code collection} 参数不能为空
     * 否则会抛出 {@link NullPointerException} 异常。
     *
     * @param collection
     *        实现了 {@link Collection} 接口的对象实例，如 {@link ArrayList}、{@link LinkedList} 等
     *        对象实例皆实现了 {@link Collection} 接口。
     *
     * @return 一个新的 {@link ArrayList} 对象实例
     *
     * @throws NullPointerException
     *         如果 {@code collection} 参数为空值
     *
     * @see ArrayList#ArrayList(Collection)
     */
    public static <E> ArrayList<E> asList(Collection<? extends E> collection) {
        return new ArrayList<>(collection);
    }

    /**
     * 合并两个实现了 {@link Collection} 接口的对象实例，通过传入顺序合并两个对象成一个新的 {@link ArrayList} 对象
     * 实例。两个 {@link Collection} 不能为空，否则会抛出 {@link NullPointerException} 异常。
     *
     * @param a
     *        实现了 {@link Collection} 接口的对象实例，如 {@link ArrayList}、{@link LinkedList} 等
     *        对象实例皆实现了 {@link Collection} 接口。

     * @param b
     *        另一个实现了 {@link Collection} 接口的对象的实例
     *
     * @return 一个新的 {@link ArrayList} 对象实例
     *
     * @throws NullPointerException
     *         如果 {@code a} 或者 {@code b} 参数为空值
     *
     * @see ArrayList#ArrayList(Collection)
     */
    @SuppressWarnings("unchecked")
    public static <E> List<E> asList(Collection<? extends E> a, Collection<? extends E> b) {
        List<E> ret = asList();
        ret.addAll(a);
        ret.addAll(b);
        return (ArrayList<E>) ret;
    }

    /**
     * 通过实现 {@link StreamMapping} 的 Lambda 接口，将一个对象转换成另一个对象实例，并
     * 批量添加到新的 {@link List} 集合中。
     *
     * @param builder
     *        Lambda 函数实现接口，或者也可以通过实现 {@link StreamMapping} 接口的方式
     *        完成这个参数。
     *
     * @param a
     *        输入数组
     *
     * @return 返回通过 {@link StreamMapping} 转换后的集合实例。
     */
    public static <T, R> List<R> listMap(T[] a, StreamMapping<T, R> builder) {
        return listMap(asList(a), builder);
    }

    /**
     * 通过实现 {@link StreamMapping} 的 Lambda 接口，将一个对象转换成另一个对象实例，并
     * 批量添加到新的 {@link List} 集合中。
     *
     * @param builder
     *        Lambda 函数实现接口，或者也可以通过实现 {@link StreamMapping} 接口的方式
     *        完成这个参数。
     *
     * @param collection
     *        实现了 {@link Collection} 接口的对象实例
     *
     * @return 返回通过 {@link StreamMapping} 转换后的集合实例。
     */
    public static <T, R> List<R> listMap(Collection<T> collection, StreamMapping<T, R> builder) {
        List<R> retval = null;
        if (collection != null) {
            retval = asList();
            for (T obj : collection)
                retval.add(builder.map(obj));
        }
        return retval;
    }

    /**
     * #brief：通过 `Predicate` Lambda 函数式接口实现集合过滤<p>
     *
     * 通过 `Predicate` Lambda 函数式接口实现集合过滤，简化 `stream` 流的
     * 写法。只需要通过一个函数实现过滤。`Predicate#test`接口如果返回 {@code false}
     * 表示不过滤，如果返回 {@code true} 表示过滤。<p>
     *
     * 过滤不影响原来的集合实例，会将过滤后的新数据移动到一个新的集合中。
     *
     * @param predicate     predicate 函数式接口
     * @param a             实现了 `Collection` 接口的对象实例
     *
     * @return 一个过滤后的新集合对象实例，不影响原有的数据。
     */
    public static <E> List<E> listFilter(Collection<E> a, Predicate<? super E> predicate) {
        return a.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * #brief：计算两个集合之间的交集部分
     *
     * <p>计算两个集合之间的交集部分，参数 {@code a} 和 {@code b} 是两个
     * 相同的数据类型集合。通过该函数可以取得两个集合之间的交集部分，并返回
     * 一个新的集合对象。
     *
     * @param a 集合 a
     * @param b 集合 b
     *
     * @return 计算后返回：两个集合之间的交集
     */
    public static <E> List<E> listInt(Collection<E> a, Collection<E> b) {
        List<E> lcopy = listCopy(a);
        lcopy.retainAll(b);
        return lcopy;
    }

    /**
     * #brief：计算两个集合之间的交集部分，支持集合 {@code b} 映射
     *
     * <p>计算两个集合之间的交集部分，参数 {@code a} 和 {@code b} 是两个
     * 相同的数据类型集合。通过该函数可以取得两个集合之间的交集部分，并返回
     * 一个新的集合对象。
     *
     * <p>这个函数可能比较难以理解，这里我用一段伪代码来解释这个函数的调用方式以便
     * 理解函数用法：
     * <pre>
     *     // 首先我们有一个包含 ["1", "2", "3"] 的整数集合，但它是字符串类型
     *     var numstrs = asList("1", "2", "3");
     *
     *     // 然后我们还有一个整数类型集合，[1, 2, 3, 4, 5, 6]
     *     var numints = asList(1, 2, 3, 4, 5, 6);
     *
     *     // 根据数字类型，取差集，预期结果为：[1, 2, 3]
     *     var ret = listInt(numints, numstrs, Objects::atoi); // 通过 atoi 将 string 转为 int 做比较
     * </pre>
     *
     * <p>可执行测试用例 'CollectsTest#listIntTest2'（在 test 包下）
     *
     * @param a         集合 a
     * @param b         集合 b，类型可以是任意对象
     * @param bMapper   映射操作，将集合 {@code b} 中的数据对应类型
     *                  映射为 {@code a} 数据类型。
     *
     * @return 计算后返回：两个集合之间的交集
     */
    public static <E, M> List<E> listInt(Collection<E> a, Collection<M> b, StreamMapping<M, E> bMapper) {
        return listInt(a, listMap(b, bMapper));
    }

    /**
     * #brief：计算两个集合之间的交集部分，支持集合 {@code b} & {@code b} 映射
     *
     * <p>计算两个集合之间的交集部分，参数 {@code a} 和 {@code b} 是两个
     * 相同的数据类型集合。通过该函数可以取得两个集合之间的交集部分，并返回
     * 一个新的集合对象。
     *
     * <p>这个函数可能比较难以理解，这里我用一段伪代码来解释这个函数的调用方式以便
     * 理解函数用法：
     * <pre>
     *     // 首先我们有一个包含 ["1", "2", "3"] 的整数集合，但它是字符串类型
     *     var numstrs = asList("1", "2", "3");
     *
     *     // 然后我们还有一个整数类型集合，[1, 2, 3, 4, 5, 6]
     *     var numints = asList(1, 2, 3, 4, 5, 6);
     *
     *     // 根据数字类型，取差集，预期结果为：[1, 2, 3]
     *     var ret = listInt(numints, Objects::atoi, numstrs, Objects::atoi); // 通过 atoi 将 string 转为 int 做比较
     * </pre>
     *
     * @param a           完整数据集
     * @param aMapper     映射操作，将 {@code a} 中的数据对应类型映射
     *                    为 {@code E} 泛型数据类型。
     * @param b           部分数据集，可以是任意对象
     * @param bMapper     映射操作，将 {@code b} 中的数据类型映射
     *                    为 {@code E} 泛型数据类型
     *
     * @return 计算后返回：两个集合之间的差集
     */
    public static <M1, M2, E> List<E> listInt(Collection<M1> a, StreamMapping<M1, E> aMapper,
                                              Collection<M2> b, StreamMapping<M2, E> bMapper) {
        return listInt(listMap(a, aMapper), listMap(b, bMapper));
    }

    /**
     * #brief：获取两个集合之间的差集
     *
     * <p>获取两个集合之间的差集，参数 {@code a} 为数据比较完整的集合列表，
     * 参数 {@code b} 为只有部分的数据列表。根据两个中的数据内容计算
     * 两个集合之间的差集。
     *
     * @param a 完整数据集
     * @param b 部分数据集
     *
     * @return 计算后返回：两个集合之间的差集
     */
    public static <E> List<E> listDiff(Collection<E> a, Collection<E> b) {
        List<E> lcopy = listCopy(a);
        lcopy.removeAll(b);
        return lcopy;
    }

    /**
     * #brief：获取两个集合之间的差集，支持集合映射转换
     *
     * <p>获取两个集合之间的差集，参数 {@code a} 为数据比较完整的集合列表，
     * 参数 {@code b} 为只有部分的数据列表。根据两个中的数据内容计算
     * 两个集合之间的差集。
     *
     * <p>这个函数可能比较难以理解，这里我用一段伪代码来解释这个函数的调用方式以便
     * 理解函数用法：
     * <pre>
     *     // 首先我们有一个包含 ["1", "2", "3"] 的整数集合，但它是字符串类型
     *     var numstrs = asList("1", "2", "3");
     *
     *     // 然后我们还有一个整数类型集合，[1, 2, 3, 4, 5, 6]
     *     var numints = asList(1, 2, 3, 4, 5, 6);
     *
     *     // 根据数字类型，取差集，预期结果为：[4，5，6]
     *     var ret = listDiff(numints, numstrs, Objects::atoi); // 通过 atoi 将 string 转为 int 做比较
     * </pre>
     *
     * <p>可执行测试用例 'CollectsTest#listDiffMapperTest'（在 test 包下）
     *
     * @param a         完整数据集
     * @param b         部分数据集，可以是任意对象
     * @param bMapper   映射操作，将 {@code b} 中的数据对应类型
     *                  映射为 {@code a} 数据类型。
     *
     * @return 计算后返回：两个集合之间的差集
     */
    public static <E, M> List<E> listDiff(Collection<E> a, Collection<M> b, StreamMapping<M, E> bMapper) {
        return listDiff(a, listMap(b, bMapper));
    }

    /**
     * #brief：获取两个集合之间的差集，支持集合映射转换
     *
     * <p>获取两个集合之间的差集，参数 {@code a} 为数据比较完整的集合列表，
     * 参数 {@code b} 为只有部分的数据列表。根据两个中的数据内容计算
     * 两个集合之间的差集。
     *
     * <p>这个函数可能比较难以理解，这里我用一段伪代码来解释这个函数的调用方式以便
     * 理解函数用法：
     * <pre>
     *     // 首先我们有一个包含 ["1", "2", "3"] 的整数集合，但它是字符串类型
     *     var numstrs1 = asList("1", "2", "3");
     *
     *     // 然后我们还有一个包含 ["2", "3", "4"]
     *     var numstrs2 = asList("2", "3", "4");
     *
     *     // 根据数字类型，取差集，预期结果为：["1", "4"]
     *     var ret = listDiff(numstrs1, Objects::atoi, numstrs2, Objects::atoi); // 通过 atoi 将 string 转为 int 做比较
     * </pre>
     *
     * @param a           完整数据集
     * @param aMapper     映射操作，将 {@code a} 中的数据对应类型映射
     *                    为 {@code E} 泛型数据类型。
     * @param b           部分数据集，可以是任意对象
     * @param bMapper     映射操作，将 {@code b} 中的数据类型映射
     *                    为 {@code E} 泛型数据类型
     *
     * @return 计算后返回：两个集合之间的差集
     */
    public static <M1, M2, E> List<E> listDiff(Collection<M1> a, StreamMapping<M1, E> aMapper,
                                               Collection<M2> b, StreamMapping<M2, E> bMapper) {
        return listDiff(listMap(a, aMapper), listMap(b, bMapper));
    }

    /**
     * #brief：计算两个集合之间的对称差集
     *
     * <p>计算两个集合之间的对称差集，参数 {@code a} & {@code b} 两个集合
     * 中一个集合与另一个集合相差的部分称为`差集`。两个集合共同相差的部分称作为
     * `对称差集`。
     *
     * @param a 集合 a
     * @param b 集合 b
     *
     * @return 计算后返回：两个集合之间的`对称差集`
     */
    @SuppressWarnings({"SlowAbstractSetRemoveAll"})
    public static <E> List<E> listSymmDiff(Collection<E> a, Collection<E> b) {
        Set<E> symmdiff = asSet();
        symmdiff.addAll(a);
        symmdiff.addAll(b);

        /* 计算交集 */
        List<E> inter = listInt(a, b);

        symmdiff.removeAll(inter);
        return (List<E>) asList(symmdiff);
    }

    /**
     * #brief：计算两个集合之间的对称差集
     *
     * <p>计算两个集合之间的对称差集，参数 {@code a} & {@code b} 两个集合
     * 中一个集合与另一个集合相差的部分称为`差集`。两个集合共同相差的部分称作为
     * `对称差集`。
     *
     * <p>这个函数可能比较难以理解，这里我用一段伪代码来解释这个函数的调用方式以便
     * 理解函数用法：
     * <pre>
     *     // 先创建集合 A
     *     var A = asList(2, 3, 4);
     *     // 然后创建集合 B
     *     var B = asList("1", "2", "3");
     *     // 最后取对称差集部分，预期结果：[1，4]
     *     listSymmDiff(A, B, Objects::atoi); // 通过 atoi 将集合 A 的 String 转为 int 计算对称差集
     * </pre>
     *
     * <p>可执行测试用例 'CollectsTest#listSymmDiffTest'（在 test 包下）
     *
     * @param a         完整数据集
     * @param b         部分数据集，可以是任意对象
     * @param bMapper   映射操作，将 {@code b} 中的数据对应类型
     *                  映射为 {@code a} 数据类型。
     *
     * @return 计算后返回：两个集合之间的差集
     */
    public static <E, M> List<E> listSymmDiff(Collection<E> a, Collection<M> b, StreamMapping<M, E> bMapper) {
        return listSymmDiff(a, listMap(b, bMapper));
    }

    /**
     * #brief：计算两个集合之间的对称差集
     *
     * <p>计算两个集合之间的对称差集，参数 {@code a} & {@code b} 两个集合
     * 中一个集合与另一个集合相差的部分称为`差集`。两个集合共同相差的部分称作为
     * `对称差集`。
     *
     * <p>这个函数可能比较难以理解，这里我用一段伪代码来解释这个函数的调用方式以便
     * 理解函数用法：
     * <pre>
     *     // 先创建集合 A
     *     var A = asList(2, 3, 4);
     *     // 然后创建集合 B
     *     var B = asList("1", "2", "3");
     *     // 最后取对称差集部分，预期结果：[1，4]
     *     listSymmDiff(A, B, Objects::atoi); // 通过 atoi 将集合 A 的 String 转为 int 计算对称差集
     * </pre>
     *
     * <p>可执行测试用例 'CollectsTest#listSymmDiffTest'（在 test 包下）
     *
     * @param a           完整数据集
     * @param aMapper     映射操作，将 {@code a} 中的数据对应类型映射
     *                    为 {@code E} 泛型数据类型。
     * @param b           部分数据集，可以是任意对象
     * @param bMapper     映射操作，将 {@code b} 中的数据类型映射
     *                    为 {@code E} 泛型数据类型
     *
     * @return 计算后返回：两个集合之间的差集
     */
    public static <M1, M2, E> List<E> listSymmDiff(Collection<M1> a, StreamMapping<M1, E> aMapper,
                                                   Collection<M2> b, StreamMapping<M2, E> bMapper) {
        return listSymmDiff(listMap(a, aMapper), listMap(b, bMapper));
    }

    //////////////////////////////////////////////////////////////////////////////////////
    /// Set
    //////////////////////////////////////////////////////////////////////////////////////

    /**
     * 传入一个 {@link Set} 对象，拷贝参数 {@code a} 中的数据到新的 {@link HashSet} 对象中，数据拷贝
     * 过去以后，修改原来的 {@link Set} 对象或修改新分配到对象，并不会影响两个对象之间的数据。
     *
     * @param a 需要拷贝的数据
     * @return 拷贝后的新 {@link HashSet} 对象实例
     */
    public static <E> Set<E> setCopy(Set<E> a) {
        return new HashSet<>(a);
    }

    /**
     * @return 分配一个空的 {@link HashSet} 集合对象实例。
     */
    public static <E> HashSet<E> asSet() {
        return new HashSet<>();
    }

    /**
     * 通过传入的泛型可变参数去分配一个 {@link HashSet} 集合对象实例。泛型可变参数不能为空
     * 否则会抛出 {@link NullPointerException} 异常。
     *
     * @param a
     *        泛型可变参数数组
     *
     * @return 一个新的 {@link HashSet} 对象实例
     *
     * @throws NullPointerException
     *         如果泛型参数为空值
     *
     * @see #asSet(Collection)
     */
    @SafeVarargs
    public static <E> HashSet<E> asSet(E... a) {
        return asSet(Arrays.asList(a));
    }

    /**
     * 截取数组 {@code a} 通过 {@code off} 和 {@code len} 两个参数去截取，截取出来的新数组会
     * 分配成一个可变的 {@link HashSet} 对象实例。
     *
     * @param a
     *        泛型可变参数数组
     *
     * @param off
     *        泛型数组开始的索引位置，偏移量。
     *
     * @param len
     *        要截取的长度，这个长度范围应该在 <= {@code a}.length()。如果长度超出 {@code a} 数组
     *        的大小，会抛出 {@link ArrayIndexOutOfBoundsException} 异常。
     *
     * @return 一个新的 {@link HashSet} 对象实例
     *
     * @throws NullPointerException
     *         如果泛型参数为空值。
     *
     * @throws ArrayIndexOutOfBoundsException
     *         如果 {@code len} 超出整个 {@code a} 数组大小的长度。
     *
     * @see #asSet(Collection)
     */
    @SuppressWarnings("unchecked")
    public static <E> HashSet<E> asSet(E[] a, int off, int len) {
        return (HashSet<E>) asSet(Arrays.asList(a, off, len));
    }

    /**
     * 通过传入的 {@link Collection} 实例去分配一个 {@link HashSet} 集合对象实例。{@code collection} 参数不能为空
     * 否则会抛出 {@link NullPointerException} 异常。
     *
     * @param collection
     *        实现了 {@link Collection} 接口的对象实例，如 {@link HashSet}、{@link LinkedList} 等
     *        对象实例皆实现了 {@link Collection} 接口。
     *
     * @return 一个新的 {@link HashSet} 对象实例
     *
     * @throws NullPointerException
     *         如果 {@code collection} 参数为空值
     *
     * @see HashSet#HashSet(Collection)
     */
    public static <E> HashSet<E> asSet(Collection<? extends E> collection) {
        return new HashSet<>(collection);
    }

    /**
     * 合并两个实现了 {@link Collection} 接口的对象实例，通过传入顺序合并两个对象成一个新的 {@link HashSet} 对象
     * 实例。两个 {@link Collection} 不能为空，否则会抛出 {@link NullPointerException} 异常。
     *
     * @param a
     *        实现了 {@link Collection} 接口的对象实例，如 {@link HashSet}、{@link LinkedList} 等
     *        对象实例皆实现了 {@link Collection} 接口。

     * @param b
     *        另一个实现了 {@link Collection} 接口的对象的实例
     *
     * @return 一个新的 {@link HashSet} 对象实例
     *
     * @throws NullPointerException
     *         如果 {@code a} 或者 {@code b} 参数为空值
     *
     * @see HashSet#HashSet(Collection)
     */
    @SuppressWarnings("unchecked")
    public static <E> HashSet<E> asSet(Collection<? extends E> a, Collection<? extends E> b) {
        HashSet<E> ret = asSet();
        ret.addAll(a);
        ret.addAll(b);
        return (HashSet<E>) ret;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    /// Map
    //////////////////////////////////////////////////////////////////////////////////////

    /**
     * 传入一个 {@link Map} 对象，拷贝参数 {@code a} 中的数据到新的 {@link HashMap} 对象中，数据拷贝
     * 过去以后，修改原来的 {@link Map} 对象或修改新分配到对象，并不会影响两个对象之间的数据。
     *
     * @param a 需要拷贝的数据
     * @return 拷贝后的新 {@link HashMap} 对象实例
     */
    public static <K, V> Map<K, V> mapCopy(Map<K, V> a) {
        return new HashMap<>(a);
    }

    /**
     * @return 分配一个新的容量默认为 16 个空间的 {@link Map} 实例。
     */
    public static <K, V> Map<K, V> asMap() {
        return new HashMap<>();
    }

    /**
     * 合并两个实现了 {@link Map} 接口实例，合并的 {@link Map} 对象可以是任何实现类。但是有一个地方
     * 需要注意，也就是如果想要的合并结果是有序的。比如你想要合并两个 {@link LinkedHashMap} 成一个单独
     * 的 {@link Map} 对象并且要保证数据的有序。那么这个函数并不适合这样的操作。
     *
     * <p>因为这个函数的返回结果是 {@link HashMap}，而 {@link HashMap} 是一个无序的
     * {@link Map} 实现类。
     *
     * @param a
     *          一个要合并的 {@link Map} 实现
     *
     * @param b
     *          另一个要合并的 {@link Map} 实现
     *
     * @return 合并后的 {@link HashMap} 对象。
     */
    public static <K, V> Map<K, V> asMap(Map<K, V> a, Map<K, V> b) {
        Map<K, V> retmap = asMap();
        retmap.putAll(a);
        retmap.putAll(b);
        return retmap;
    }

    /**
     * 通过参数传入，key 和 value 创建一个 {@link Map} 对象实例。这个函数必须传入
     * {@code key} 和 {@code value} 并且返回的 Map 对象实例中包含传入的 {@code key}
     * 和 {@code value}
     *
     * 这个函数支持传入一个键值对。
     *
     * @param k1
     *          key
     *
     * @param v1
     *          value
     *
     * @return 返回一个包含传入的 key 和 value 的 {@link Map} 对象实例。
     */
    public static <K, V> Map<K, V> asMap(K k1, V v1) {
        Map<K, V> retmap = asMap();
        retmap.put(k1, v1);
        return retmap;
    }

    /**
     * 通过参数传入，key 和 value 创建一个 {@link Map} 对象实例。这个函数必须传入
     * {@code key} 和 {@code value} 并且返回的 Map 对象实例中包含传入的 {@code key}
     * 和 {@code value}
     *
     * 这个函数支持传入两个键值对。
     *
     * @return 返回一个包含传入的 key 和 value 的 {@link Map} 对象实例。
     */
    public static <K, V> Map<K, V> asMap(K k1, V v1, K k2, V v2) {
        Map<K, V> retmap = asMap();
        retmap.put(k1, v1);
        retmap.put(k2, v2);
        return retmap;
    }

    /**
     * 通过参数传入，key 和 value 创建一个 {@link Map} 对象实例。这个函数必须传入
     * {@code key} 和 {@code value} 并且返回的 Map 对象实例中包含传入的 {@code key}
     * 和 {@code value}
     *
     * 这个函数支持传入三个键值对。
     *
     * @return 返回一个包含传入的 key 和 value 的 {@link Map} 对象实例。
     */
    public static <K, V> Map<K, V> asMap(K k1, V v1, K k2, V v2, K k3, V v3) {
        Map<K, V> retmap = asMap();
        retmap.put(k1, v1);
        retmap.put(k2, v2);
        retmap.put(k3, v3);
        return retmap;
    }

    /**
     * 通过参数传入，key 和 value 创建一个 {@link Map} 对象实例。这个函数必须传入
     * {@code key} 和 {@code value} 并且返回的 Map 对象实例中包含传入的 {@code key}
     * 和 {@code value}
     *
     * 这个函数支持传入四个键值对。
     *
     * @return 返回一个包含传入的 key 和 value 的 {@link Map} 对象实例。
     */
    public static <K, V> Map<K, V> asMap(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        Map<K, V> retmap = asMap();
        retmap.put(k1, v1);
        retmap.put(k2, v2);
        retmap.put(k3, v3);
        retmap.put(k4, v4);
        return retmap;
    }

    /**
     * 通过参数传入，key 和 value 创建一个 {@link Map} 对象实例。这个函数必须传入
     * {@code key} 和 {@code value} 并且返回的 Map 对象实例中包含传入的 {@code key}
     * 和 {@code value}
     *
     * 这个函数支持传入五个键值对。
     *
     * @return 返回一个包含传入的 key 和 value 的 {@link Map} 对象实例。
     */
    public static <K, V> Map<K, V> asMap(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Map<K, V> retmap = asMap();
        retmap.put(k1, v1);
        retmap.put(k2, v2);
        retmap.put(k3, v3);
        retmap.put(k4, v4);
        retmap.put(k5, v5);
        return retmap;
    }

    /**
     * 通过参数传入，key 和 value 创建一个 {@link Map} 对象实例。这个函数必须传入
     * {@code key} 和 {@code value} 并且返回的 Map 对象实例中包含传入的 {@code key}
     * 和 {@code value}
     *
     * 这个函数支持传入六个键值对。
     *
     * @return 返回一个包含传入的 key 和 value 的 {@link Map} 对象实例。
     */
    public static <K, V> Map<K, V> asMap(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        Map<K, V> retmap = asMap();
        retmap.put(k1, v1);
        retmap.put(k2, v2);
        retmap.put(k3, v3);
        retmap.put(k4, v4);
        retmap.put(k5, v5);
        retmap.put(k6, v6);
        return retmap;
    }

}
