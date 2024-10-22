package com.redgogh.common.security.codec;

import static com.redgogh.common.BasicConverter.atos;

/**
 * @author RedGogh
 */
public class Base64Codec implements com.redgogh.common.security.Base64 {
    @Override
    public String encode(String source) {
        return encode(source.getBytes());
    }

    @Override
    public String encode(byte[] b) {
        return java.util.Base64.getUrlEncoder().encodeToString(b);
    }

    @Override
    public String decode(String src) {
        return atos(java.util.Base64.getUrlDecoder().decode(src));
    }
}
