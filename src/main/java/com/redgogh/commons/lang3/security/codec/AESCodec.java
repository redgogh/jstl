package com.redgogh.commons.lang3.security.codec;

import com.redgogh.commons.lang3.utils.BasicConverter;
import com.redgogh.commons.lang3.utils.Capturer;
import com.redgogh.commons.lang3.security.AES;
import com.redgogh.commons.lang3.security.Crypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import static com.redgogh.commons.lang3.utils.BasicConverter.atos;

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
            return Crypt.BASE64.encode(cipher.doFinal(bytes));
        });
    }

    @Override
    public String decrypt(String data, String secret) {
        return BasicConverter.atos(decrypt(Crypt.BASE64.decodeBytes(data), secret));
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
