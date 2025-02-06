package org.redgogh.common.crypto.codec;

import org.redgogh.common.base.Capturer;
import org.redgogh.common.crypto.Crypto;
import org.redgogh.common.crypto.SHA256;
import org.redgogh.common.exception.CentralRuntimeException;
import org.redgogh.common.io.IOUtils;
import org.redgogh.common.io.MutableFile;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author RedGogh
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
            return Crypto.toByteHex(messageDigest.digest());
        });
    }

    @Override
    public String encode(byte[] source) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(source);
            return Crypto.toByteHex(messageDigest.digest());
        } catch (Exception e) {
            throw new CentralRuntimeException(e);
        }
    }

}
