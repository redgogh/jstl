package com.redgogh.common.crypto;

import com.redgogh.common.crypto.codec.SHA256Codec;

/**
 * `Sha256` 接口定义了用于进行 SHA-256 编码的方法。
 *
 * <p>该接口的实现类需要提供将字符串使用 SHA-256 算法进行编码的功能。
 *
 * @author RedGogh
 * @since 1.0
 * @see SHA256Codec
 */
public interface SHA256 {

    /**
     * #brief: 对字符串进行 SHA-256 编码
     *
     * @param source 要进行 SHA-256 编码的字符串
     * @return 编码后的 SHA-256 字符串
     */
    String encode(String source);

}

