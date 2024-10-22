package com.redgogh.common.security;

/**
 * @author RedGogh
 */
public interface URL {
    /** URL编码 */
    String encode(String source);
    /** URL编码 */
    String encode(String source, String enc);
    /** URL解码 */
    String decode(String source);
    /** URL解码 */
    String decode(String source, String enc);
}
