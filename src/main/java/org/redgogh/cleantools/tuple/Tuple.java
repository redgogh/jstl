package org.redgogh.cleantools.tuple;

import static org.redgogh.cleantools.base.BasicConverter.anyeq;

/**
 * `Tuple` 是一个通用的二元元组类，用于存储一对相关联的值。
 *
 * <p>该类的设计旨在提供一种简洁的方式存储两个相关联的数据对象，而无需创建专门的类。
 * 元组对象是不可变的（如果存储的对象本身不可变），这有助于提高数据的安全性。
 *
 * <p>主要特点：
 * <ul>
 *     <li>支持存储两个任意类型的数据。</li>
 *     <li>提供了便捷的构造方法和访问方法。</li>
 *     <li>实现了 {@link Object#equals(Object)} 和 {@link Object#hashCode()}，
 *         可以安全地用于集合操作。</li>
 * </ul>
 *
 * <h2>使用示例</h2>
 * <pre>
 *     // 创建一个 Tuple
 *     Tuple<String, Integer> tuple = new Tuple<>("Age", 30);
 *
 *     // 获取左值和右值
 *     String key = tuple.getLeft();
 *     Integer value = tuple.getRight();
 * </pre>
 *
 * <h2>注意事项</h2>
 * <ul>
 *     <li>元组的字段本身是不可变的，但存储的对象是否可变取决于对象本身。</li>
 *     <li>建议用于简单的数据存储场景。如果有更复杂的逻辑，建议创建专门的类。</li>
 * </ul>
 *
 * @param <L> 左值（第一个元素）的类型
 * @param <R> 右值（第二个元素）的类型
 * @author RedGogh
 * @since 1.0
 */
public class Tuple<L, R> {

    /**
     * 元组的左值（第一个元素），为不可变字段。
     *
     * <p>该字段用于存储元组的左侧值，在元组对象创建后无法修改。
     */
    private final L left;

    /**
     * 元组的右值（第二个元素），为不可变字段。
     *
     * <p>该字段用于存储元组的右侧值，在元组对象创建后无法修改。
     */
    private final R right;

    /**
     * 构造一个包含指定左值和右值的元组对象。
     *
     * <p>该构造方法初始化元组的两个元素，分别为左值和右值。适用于快速创建一个包含两个相关联
     * 数据的元组对象。
     *
     * @param left 左值（第一个元素）
     * @param right 右值（第二个元素）
     */
    public Tuple(L left, R right) {
        this.left = left;
        this.right = right;
    }

    /**
     * 获取元组的左值（第一个元素）。
     *
     * @return 元组的左值
     */
    public L getLeft() {
        return left;
    }

    /**
     * 获取元组的右值（第二个元素）。
     *
     * @return 元组的右值
     */
    public R getRight() {
        return right;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public boolean equals(Tuple<L, R> tuple) {
        return anyeq(left, tuple.left) && anyeq(right, tuple.right);
    }

}
