package com.redgogh.common.security;

/**
 * @author RedGogh
 */
public interface Sha256 {
    /** sha256 加密（小写） */
    String encode(String source);
}
