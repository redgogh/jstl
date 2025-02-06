package org.redgogh.common.exception;

import org.jetbrains.annotations.NotNull;

/**
 * `IOWriteException` 是一个自定义的 I/O 写入异常类，继承自 `CentralRuntimeException`。
 * 当在进行 I/O 写入操作时发生错误或异常情况时，可以抛出此异常以便更好地处理和定位问题。
 *
 * <h2>使用场景</h2>
 * <p>适用于文件写入、流写入、网络数据发送等 I/O 操作的场景。当写入过程中出现错误或异常时，
 * 可以使用此异常进行捕获和处理，提供更加明确的异常信息。</p>
 *
 * <h2>构造函数</h2>
 * <ul>
 *     <li>{@link #IOWriteException()} - 默认构造函数，不带任何参数。</li>
 *     <li>{@link #IOWriteException(Throwable e)} - 通过已有的异常创建一个新的 I/O 写入异常。</li>
 *     <li>{@link #IOWriteException(String vfmt, Object... args)} - 根据格式化字符串和参数创建异常信息。</li>
 *     <li>{@link #IOWriteException(String vfmt, Throwable e, Object... args)} - 根据格式化字符串、已有异常和参数创建异常信息。</li>
 * </ul>
 *
 * <h2>使用示例</h2>
 * <pre>
 *     // 抛出一个简单的 I/O 写入异常
 *     throw new IOWriteException("文件写入失败：磁盘空间不足");
 *
 *     // 抛出一个包含其他异常的 I/O 写入异常
 *     try {
 *         // 写入文件操作
 *     } catch (IOException e) {
 *         throw new IOWriteException(e);
 *     }
 *
 *     // 抛出一个格式化消息的 I/O 写入异常
 *     throw new IOWriteException("写入文件 %s 时发生错误：%s", filePath, errorMessage);
 * </pre>
 *
 * @author RedGogh
 * @since 1.0
 */
public class IOWriteException extends CentralRuntimeException {

    /**
     * 默认构造函数。
     */
    public IOWriteException() {
    }

    /**
     * 通过已有的异常创建一个新的 I/O 写入异常。
     *
     * @param e 原始异常，不能为 null。
     */
    public IOWriteException(@NotNull Throwable e) {
        super(e);
    }

    /**
     * 根据格式化字符串和参数创建异常信息。
     *
     * @param vfmt 格式化字符串，不能为 null。
     * @param args 格式化参数。
     */
    public IOWriteException(@NotNull String vfmt, @NotNull Object... args) {
        super(vfmt, args);
    }

    /**
     * 根据格式化字符串、已有异常和参数创建异常信息。
     *
     * @param vfmt 格式化字符串，不能为 null。
     * @param e 原始异常，不能为 null。
     * @param args 格式化参数。
     */
    public IOWriteException(@NotNull String vfmt, @NotNull Throwable e, @NotNull Object... args) {
        super(vfmt, e, args);
    }

}

