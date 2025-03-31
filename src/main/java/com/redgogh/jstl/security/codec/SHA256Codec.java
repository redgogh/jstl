package com.redgogh.jstl.security.codec;

import com.redgogh.jstl.exception.SystemRuntimeException;
import com.redgogh.jstl.io.IOUtils;
import com.redgogh.jstl.io.MutableFile;
import com.redgogh.jstl.security.Codec;
import com.redgogh.jstl.security.SHA256;
import com.redgogh.jstl.utils.Capturer;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author Red Gogh
 */
public class SHA256Codec implements SHA256 {

    @Override
    public String encode(String source) {
        return encode(source.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    @SuppressWarnings("resource")
    public String encode(File f0) {
        return Capturer.icall(() -> {
            MutableFile mutableFile = new MutableFile(f0);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            mutableFile.openByteReader().call(reader -> {
                int len = 0;
                byte[] buffer = new byte[IOUtils.MB];
                while ((len = reader.read(buffer)) != IOUtils.EOF)
                    messageDigest.update(buffer, 0, len);
            });
            return Codec.toByteHex(messageDigest.digest());
        });
    }

    @Override
    public String encode(byte[] source) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(source);
            return Codec.toByteHex(messageDigest.digest());
        } catch (Exception e) {
            throw new SystemRuntimeException(e);
        }
    }

}
