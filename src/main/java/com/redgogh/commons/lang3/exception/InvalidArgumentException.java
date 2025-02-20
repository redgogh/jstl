package com.redgogh.commons.lang3.exception;

import org.jetbrains.annotations.NotNull;

/**
 * `InvalidArgumentException` 是一个自定义的非法参数异常类，继承自 `CentralRuntimeException`。
 * 用于在方法或函数调用中，当传入的参数不合法或不符合预期时抛出。
 *
 * <h2>使用场景</h2>
 * <p>常用于参数验证、数据校验等场景。当参数值不符合预期或条件时，可以抛出此异常，
 * 从而在程序的早期阶段捕获错误，防止错误传播。</p>
 *
 * <h2>构造函数</h2>
 * <ul>
 *     <li>{@link #InvalidArgumentException()} - 默认构造函数，不带任何参数。</li>
 *     <li>{@link #InvalidArgumentException(Throwable e)} - 通过已有的异常创建一个新的非法参数异常。</li>
 *     <li>{@link #InvalidArgumentException(String vfmt, Object... args)} - 根据格式化字符串和参数创建异常信息。</li>
 *     <li>{@link #InvalidArgumentException(String vfmt, Throwable e, Object... args)} - 根据格式化字符串、已有异常和参数创建异常信息。</li>
 * </ul>
 *
 * <h2>使用示例</h2>
 * <pre>
 *     // 抛出一个简单的非法参数异常
 *     throw new InvalidArgumentException("非法参数：参数值不能为 null");
 *
 *     // 抛出一个包含其他异常的非法参数异常
 *     try {
 *         // 代码逻辑
 *     } catch (NullPointerException e) {
 *         throw new InvalidArgumentException(e);
 *     }
 *
 *     // 抛出一个格式化消息的非法参数异常
 *     throw new InvalidArgumentException("非法参数：期望值在范围 %d 到 %d 之间，但实际值为 %d", minValue, maxValue, actualValue);
 * </pre>
 *
 * @author Red Gogh
 * @since 1.0
 */
public class InvalidArgumentException extends CentralRuntimeException {

    /**
     * 默认构造函数。
     */
    public InvalidArgumentException() {
    }

    /**
     * 通过已有的异常创建一个新的非法参数异常。
     *
     * @param e 原始异常，不能为 null。
     */
    public InvalidArgumentException(@NotNull Throwable e) {
        super(e);
    }

    /**
     * 根据格式化字符串和参数创建异常信息。
     *
     * @param vfmt 格式化字符串，不能为 null。
     * @param args 格式化参数。
     */
    public InvalidArgumentException(@NotNull String vfmt, @NotNull Object... args) {
        super(vfmt, args);
    }

    /**
     * 根据格式化字符串、已有异常和参数创建异常信息。
     *
     * @param vfmt 格式化字符串，不能为 null。
     * @param e 原始异常，不能为 null。
     * @param args 格式化参数。
     */
    public InvalidArgumentException(@NotNull String vfmt, @NotNull Throwable e, @NotNull Object... args) {
        super(vfmt, e, args);
    }
}
