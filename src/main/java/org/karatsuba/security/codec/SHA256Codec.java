package org.karatsuba.security.codec;

import org.karatsuba.exception.SystemRuntimeException;
import org.karatsuba.io.IOUtils;
import org.karatsuba.io.PhysicalFile;
import org.karatsuba.security.Codec;
import org.karatsuba.security.SHA256;
import org.karatsuba.utils.Captor;

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
    public String encode(File f0) {
        return Captor.icall(() -> {
            PhysicalFile physicalFile = new PhysicalFile(f0);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            physicalFile.tryInput(reader -> {
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
