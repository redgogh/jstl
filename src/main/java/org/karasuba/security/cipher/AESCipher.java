package org.karasuba.security.cipher;

import org.karasuba.security.AES;
import org.karasuba.security.Codec;
import org.karasuba.utils.BasicConverter;
import org.karasuba.utils.Captor;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Red Gogh
 */
public class AESCipher implements AES {

    @Override
    public String encrypt(String data, String secret) {
        return encrypt(data.getBytes(), secret);
    }

    @Override
    public String encrypt(byte[] bytes, String secret) {
        return Captor.call(() -> {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return Codec.BASE64.encode(cipher.doFinal(bytes));
        });
    }

    @Override
    public String decrypt(String data, String secret) {
        return BasicConverter.atos(decrypt(Codec.BASE64.decodeBytes(data), secret));
    }

    @Override
    public byte[] decrypt(byte[] bytes, String secret) {
        return Captor.call(() -> {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return cipher.doFinal(bytes);
        });
    }

}
