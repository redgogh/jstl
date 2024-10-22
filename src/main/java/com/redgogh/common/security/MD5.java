package com.redgogh.common.security;

/**
 * @author RedGogh
 */
public interface MD5 {
    /** 将字符串加密成MD5（32位小） */
    String lower32(String source);
    /** 将字节数组加密成MD5（32位小） */
    String lower32(byte[] b);
    /** 将字符串加密成MD5（16位小） */
    String lower16(String source);
    /** 将字节数组加密成MD5（16位小） */
    String lower16(byte[] b);
    /** 将字符串加密成MD5（32位大） */
    String upper32(String source);
    /** 将字节数组加密成MD5（32位大） */
    String upper32(byte[] b);
    /** 将字符串加密成MD5（16位大） */
    String upper16(String source);
    /** 将字节数组加密成MD5（16位大） */
    String upper16(byte[] b);
}
