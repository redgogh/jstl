package com.redgogh.common.security.codec;

import com.redgogh.common.exception.SystemRuntimeException;
import com.redgogh.common.security.Crypto;
import com.redgogh.common.security.Sha256;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author RedGogh
 */
public class Sha256Codec implements com.redgogh.common.security.Sha256 {
    @Override
    public String encode(String source) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(source.getBytes(StandardCharsets.UTF_8));
            return Crypto.toByteHex(messageDigest.digest());
        } catch (Exception e) {
            throw new SystemRuntimeException(e);
        }
    }
}
