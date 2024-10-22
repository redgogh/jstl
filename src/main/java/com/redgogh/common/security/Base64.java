package com.redgogh.common.security;

/**
 * @author RedGogh
 */
public interface Base64 {
    /** 将字符串使用base64算加密 */
    String encode(String source);
    /** 将字符数组使用base64算加密 */
    String encode(byte[] b);
    /** 解析 base64 编码 */
    String decode(String src);
}
