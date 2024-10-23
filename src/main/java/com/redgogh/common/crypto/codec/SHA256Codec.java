package com.redgogh.common.crypto.codec;

import com.redgogh.common.crypto.Crypto;
import com.redgogh.common.crypto.SHA256;
import com.redgogh.common.exception.SystemRuntimeException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author RedGogh
 */
public class SHA256Codec implements SHA256 {
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
