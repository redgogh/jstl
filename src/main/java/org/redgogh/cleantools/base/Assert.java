package org.redgogh.cleantools.base;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2019-2024 RedGogh All rights reserved.                          *|
|*                                                                                  *|
|*    Licensed under the Apache License, Version 2.0 (the "License");               *|
|*    you may not use this file except in compliance with the License.              *|
|*    You may obtain a copy of the License at                                       *|
|*                                                                                  *|
|*        http://www.apache.org/licenses/LICENSE-2.0                                *|
|*                                                                                  *|
|*    Unless required by applicable law or agreed to in writing, software           *|
|*    distributed under the License is distributed on an "AS IS" BASIS,             *|
|*    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.      *|
|*    See the License for the specific language governing permissions and           *|
|*    limitations under the License.                                                *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import org.redgogh.cleantools.collection.Lists;
import org.redgogh.cleantools.collection.Maps;
import org.redgogh.cleantools.except.AssertException;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * 运行时断言工具类，这个类断言失败后并不会直接停止整个程序，而是会将所有失败的断言
 * 信息作为 Runtime 异常抛出。
 *
 * @author RedGogh
 */
public class Assert {

    /**
     * 如果条件为 true，则抛出断言异常。
     *
     * <p>此方法用于验证某个条件是否为 true，如果条件为 true，将抛出 {@link AssertException}。
     *
     * @param condition 要检查的条件
     * @throws AssertException 如果条件为 true
     */
    public static void isTrue(boolean condition) {
        isTrue(condition, "assert condition == true");
    }

    /**
     * 当条件为 true 时，抛出自定义的 {@link AssertException}。
     *
     * <p>此方法用于在条件不满足时触发异常，便于进行断言检查，确保程序的状态符合预期。
     *
     * @param condition 需要检查的条件
     * @param fmt 自定义异常信息的格式化字符串
     * @param args 格式化字符串的参数
     *
     * @throws AssertException 如果 {@code condition} 为 true，将抛出异常，并使用 {@code fmt} 和 {@code args}
     *                         格式化异常信息。
     */
    public static void isTrue(boolean condition, String fmt, Object... args) {
        if (condition)
            throw new AssertException(fmt, args);
    }

    /**
     * 如果条件为 false，则抛出断言异常。
     *
     * <p>此方法用于验证某个条件是否为 false，如果条件为 false，将抛出 {@link AssertException}。
     *
     * @param condition 要检查的条件
     * @throws AssertException 如果条件为 false
     */
    public static void isFalse(boolean condition) {
        isFalse(condition, "assert condition == false");
    }

    /**
     * 当条件为 false 时，抛出自定义的 {@link AssertException}。
     *
     * <p>此方法用于在条件不满足时触发异常，便于进行断言检查，确保程序的状态符合预期。
     *
     * @param condition 需要检查的条件
     * @param fmt 自定义异常信息的格式化字符串
     * @param args 格式化字符串的参数
     *
     * @throws AssertException 如果 {@code condition} 为 false，将抛出异常，并使用 {@code fmt} 和 {@code args}
     *                         格式化异常信息。
     */
    public static void isFalse(boolean condition, String fmt, Object... args) {
        if (!condition)
            throw new AssertException(fmt, args);
    }

    /**
     * 如果集合为空，则抛出断言异常。
     *
     * <p>此方法用于检查集合的状态，如果集合为空，将抛出 {@link AssertException}。
     *
     * @param collection 要检查的集合
     * @throws AssertException 如果集合为空
     */
    public static <E> void isEmpty(Collection<E> collection) {
        if (Lists.isEmpty(collection))
            throw new AssertException("collection is empty!");
    }

    /**
     * 如果 Map 为空，则抛出断言异常。
     *
     * <p>此方法用于检查 Map 的状态，如果 Map 为空，将抛出 {@link AssertException}。
     *
     * @param map 要检查的 Map
     * @throws AssertException 如果 Map 为空
     */
    public static <K, V> void isEmpty(Map<K, V> map) {
        if (Maps.isEmpty(map))
            throw new AssertException("map is empty!");
    }

    /**
     * 检查给定条件是否为 null，如果是，则抛出断言异常。
     *
     * <p>此方法用于验证一个对象是否为 null，如果为 null，将抛出 {@link AssertException}。
     *
     * @param condition 要检查的对象
     * @throws AssertException 如果条件为 null
     */
    public static void isNull(Object condition) {
        isNull(condition, "null");
    }

    /**
     * 检查给定条件是否为 null，如果是，则抛出断言异常，并使用自定义的异常信息。
     *
     * <p>此方法用于验证一个对象是否为 null，如果为 null，将抛出 {@link AssertException}，同时提供格式化支持。
     *
     * @param condition 要检查的对象
     * @param fmt 自定义异常信息的格式
     * @param args 格式化参数
     * @throws AssertException 如果条件为 null
     */
    public static void isNull(Object condition, String fmt, Object... args) {
        if (condition == null)
            throw new AssertException(fmt, args);
    }

    /**
     * #brief: 断言两个对象不相等
     *
     * <p>该方法用于判断两个对象是否不相等。如果两个
     * 对象相等，则抛出 `AssertException`，提示断言
     * 失败；如果不相等，则正常结束。
     *
     * <p>该方法可用于单元测试或需要验证对象不相等的场景。
     *
     * @param actual 实际值
     * @param expected 期望值
     * @throws AssertException 当 `actual` 和 `expected` 相等时抛出异常
     */
    public static void notEquals(Object actual, Object expected) {
        notEquals(actual, expected, "assert actual == expected");
    }

    /**
     * #brief: 断言两个对象不相等
     *
     * <p>该方法用于判断两个对象是否不相等。如果两个
     * 对象相等，则抛出 `AssertException`，提示断言
     * 失败；如果不相等，则正常结束。
     *
     * <p>该方法可用于单元测试或需要验证对象不相等的场景。
     *
     * @param actual 实际值
     * @param expected 期望值
     * @param message 自定义异常信息
     * @param args 格式化参数
     * @throws AssertException 当 `actual` 和 `expected` 相等时抛出异常
     */
    public static void notEquals(Object actual, Object expected, String message, Object... args) {
        if (!Objects.equals(actual, expected))
            throw new AssertException(message, args);
    }

}
