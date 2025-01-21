package org.redgogh.cleantools.base;

/**
 * 定义字符串操作类型的枚举类。
 *
 * <p>该枚举类用于列举常见的字符串操作类型。
 *
 * <h2>功能特点</h2>
 * <ul>
 *     <li>易于扩展，支持后续增加更多字符串操作类型。</li>
 *     <li>提供统一的枚举类型，便于管理和使用不同的字符串操作。</li>
 * </ul>
 *
 * <h2>使用示例</h2>
 * <pre>
 *     strcut(a, off, len, STRING_OPERATOR_TRIM)
 * </pre>
 *
 * @since 1.0
 */
public enum StringOperator {

    /**
     * 字符串修剪操作，去除字符串前后的空白字符
     */
    STRING_OPERATOR_TRIM

}