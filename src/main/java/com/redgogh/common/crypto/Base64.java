package com.redgogh.common.crypto;

import com.redgogh.common.crypto.codec.Base64Codec;

/**
 * `Base64` 接口定义了用于进行 Base64 编码和解码的方法。
 *
 * <p>该接口的实现类需要提供将字符串或字节数组进行 Base64 编码，
 * 以及将 Base64 字符串解码为原始内容的功能。
 *
 * @author RedGogh
 * @since 1.0
 * @see Base64Codec 实现类示例
 */
public interface Base64 {

    /**
     * #brief: 对字符串进行 Base64 编码
     *
     * @param source 要进行 Base64 编码的字符串
     * @return 编码后的 Base64 字符串
     */
    String encode(String source);

    /**
     * #brief: 对字节数组进行 Base64 编码
     *
     * @param b 要进行 Base64 编码的字节数组
     * @return 编码后的 Base64 字符串
     */
    String encode(byte[] b);

    /**
     * #brief: 对 Base64 字符串进行解码
     *
     * @param src 要解码的 Base64 字符串
     * @return 解码后的原始字符串
     */
    String decode(String src);

}

