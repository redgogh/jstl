package org.redgogh.cleantools.crypto.codec;

import org.redgogh.cleantools.base.BasicConverter;
import org.redgogh.cleantools.base.Capturer;
import org.redgogh.cleantools.crypto.AES;
import org.redgogh.cleantools.crypto.Crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import static org.redgogh.cleantools.base.BasicConverter.atos;

/**
 * @author RedGogh
 */
public class AESCodec implements AES {

    @Override
    public String encrypt(String data, String secret) {
        return encrypt(data.getBytes(), secret);
    }

    @Override
    public String encrypt(byte[] bytes, String secret) {
        return Capturer.call(() -> {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return Crypto.BASE64.encode(cipher.doFinal(bytes));
        });
    }

    @Override
    public String decrypt(String data, String secret) {
        return BasicConverter.atos(decrypt(Crypto.BASE64.decodeBytes(data), secret));
    }

    @Override
    public byte[] decrypt(byte[] bytes, String secret) {
        return Capturer.call(() -> {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return cipher.doFinal(bytes);
        });
    }

}
