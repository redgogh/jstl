package com.redgogh.tools;

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

import com.redgogh.tools.collection.Lists;
import com.redgogh.tools.exception.AssertException;

import java.util.Collection;

/**
 * 运行时断言工具类，这个类断言失败后并不会直接停止整个程序，而是会将所有失败的断言
 * 信息作为 Runtime 异常抛出。
 *
 * @author RedGogh
 */
public class Assert {

    /**
     * 执行指定的无返回值函数，如果发生任何异常则忽略并不处理。
     *
     * <p>该方法可用于执行可能会抛出异常的操作，但不需要关心异常的情况。
     *
     * @param function 要执行的函数
     */
    public static void ignore(VoidFunction function) {
        try {
            function.call();
        } catch (Throwable e) {
            /* do nothing... */
        }
    }

    /**
     * 执行指定的有返回值函数，如果发生异常则返回 null。
     *
     * <p>该方法可用于执行可能会抛出异常的操作，并在异常发生时提供一个默认值（null）。
     *
     * @param function 要执行的函数
     * @param <T> 返回值的类型
     * @return 函数返回的值；如果发生异常，则返回 null
     */
    public static <T> T ignore(RetFunction<T> function) {
        try {
            return function.call();
        } catch (Throwable e) {
            return null;
        }
    }

    /**
     * 如果条件为 true，则抛出断言异常。
     *
     * <p>此方法用于验证某个条件是否为 true，如果条件为 true，将抛出 {@link AssertException}。
     *
     * @param condition 要检查的条件
     * @throws AssertException 如果条件为 true
     */
    public static void throwIfTrue(boolean condition) {
        throwIfTrue(condition, "assert condition == true");
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
    public static void throwIfTrue(boolean condition, String fmt, Object... args) {
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
    public static void throwIfFalse(boolean condition) {
        throwIfFalse(condition, "assert condition == false");
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
    public static void throwIfFalse(boolean condition, String fmt, Object... args) {
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
    public static <E> void throwIfEmpty(Collection<E> collection) {
        if (Lists.isEmpty(collection))
            throw new AssertException("collection is empty!");
    }

    /**
     * 执行指定的无返回值函数，如果发生异常则抛出断言异常。
     *
     * <p>此方法用于执行可能抛出异常的操作，并在发生异常时提供详细的异常信息。
     *
     * @param function 要执行的函数
     * @throws AssertException 如果函数执行时发生异常
     */
    public static void throwIfError(VoidFunction function) {
        try {
            function.call();
        } catch (Throwable e) {
            throw new AssertException(e.getMessage());
        }
    }

    /**
     * 执行指定的无返回值函数，如果发生异常则抛出断言异常，并使用自定义的异常信息。
     *
     * <p>此方法用于执行可能抛出异常的操作，并在发生异常时提供详细的异常信息和格式化支持。
     *
     * @param function 要执行的函数
     * @param fmt 自定义异常信息的格式
     * @param args 格式化参数
     * @throws AssertException 如果函数执行时发生异常
     */
    public static void throwIfError(VoidFunction function, String fmt, Object... args) {
        try {
            function.call();
        } catch (Throwable e) {
            throw new AssertException(fmt, args);
        }
    }

    /**
     * 执行指定的有返回值函数，如果发生异常则抛出断言异常。
     *
     * <p>此方法用于执行可能抛出异常的操作，并在发生异常时提供详细的异常信息。
     *
     * @param function 要执行的函数
     * @param <T> 返回值的类型
     * @return 函数返回的值；如果发生异常，则抛出 {@link AssertException}
     * @throws AssertException 如果函数执行时发生异常
     */
    public static <T> T throwIfError(RetFunction<T> function) {
        try {
            return function.call();
        } catch (Throwable e) {
            throw new AssertException(e.getMessage());
        }
    }

    /**
     * 执行指定的有返回值函数，如果发生异常则抛出断言异常，并使用自定义的异常信息。
     *
     * <p>此方法用于执行可能抛出异常的操作，并在发生异常时提供详细的异常信息和格式化支持。
     *
     * @param function 要执行的函数
     * @param fmt 自定义异常信息的格式
     * @param args 格式化参数
     * @param <T> 返回值的类型
     * @return 函数返回的值；如果发生异常，则抛出 {@link AssertException}
     * @throws AssertException 如果函数执行时发生异常
     */
    public static <T> T throwIfError(RetFunction<T> function, String fmt, Object... args) {
        try {
            return function.call();
        } catch (Throwable e) {
            throw new AssertException(fmt, args);
        }
    }

    /**
     * 检查给定条件是否为 null，如果是，则抛出断言异常。
     *
     * <p>此方法用于验证一个对象是否为 null，如果为 null，将抛出 {@link AssertException}。
     *
     * @param condition 要检查的对象
     * @param <T> 对象的类型
     * @return 如果条件不为 null，返回该对象
     * @throws AssertException 如果条件为 null
     */
    public static <T> T throwIfNull(T condition) {
        return throwIfNull(condition, "null");
    }

    /**
     * 检查给定条件是否为 null，如果是，则抛出断言异常，并使用自定义的异常信息。
     *
     * <p>此方法用于验证一个对象是否为 null，如果为 null，将抛出 {@link AssertException}，同时提供格式化支持。
     *
     * @param condition 要检查的对象
     * @param fmt 自定义异常信息的格式
     * @param args 格式化参数
     * @param <T> 对象的类型
     * @return 如果条件不为 null，返回该对象
     * @throws AssertException 如果条件为 null
     */
    public static <T> T throwIfNull(T condition, String fmt, Object... args) {
        if (condition == null)
            throw new AssertException(fmt, args);
        return condition;
    }

}
