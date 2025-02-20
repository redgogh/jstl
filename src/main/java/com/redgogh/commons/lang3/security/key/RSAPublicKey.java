package com.redgogh.commons.lang3.security.key;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 Red Gogh                                                   *|
|*                                                                                  *|
|*    This program is free software: you can redistribute it and/or modify          *|
|*    it under the terms of the GNU General Public License as published by          *|
|*    the Free Software Foundation, either version 3 of the License, or             *|
|*    (at your option) any later version.                                           *|
|*                                                                                  *|
|*    This program is distributed in the hope that it will be useful,               *|
|*    but WITHOUT ANY WARRANTY; without even the implied warranty of                *|
|*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                 *|
|*    GNU General Public License for more details.                                  *|
|*                                                                                  *|
|*    You should have received a copy of the GNU General Public License             *|
|*    along with this program.  If not, see <https://www.gnu.org/licenses/>.        *|
|*                                                                                  *|
|*    This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.    *|
|*    This is free software, and you are welcome to redistribute it                 *|
|*    under certain conditions; type `show c' for details.                          *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

/* Creates on 2025/2/20. */

import com.redgogh.commons.lang3.io.MutableFile;
import com.redgogh.commons.lang3.security.Crypt;
import com.redgogh.commons.lang3.utils.Capturer;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import static com.redgogh.commons.lang3.string.StringUtils.strcut;
import static com.redgogh.commons.lang3.string.StringUtils.strtok;
import static com.redgogh.commons.lang3.utils.BasicConverter.atos;

/**
 * @author Red Gogh
 */
public class RSAPublicKey extends AbstractKey {

    public RSAPublicKey(Key key) {
        super(key);
    }

    public RSAPublicKey(byte[] encoded) {
        super(encoded);
    }

    public PublicKey toPublicKey() {
        return Capturer.call(() -> {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        });
    }

    public static RSAPublicKey fromKeyFile(String filepath) {
        MutableFile keyfile = new MutableFile(filepath);
        return fromPEMFormat(keyfile.strread());
    }

    public static RSAPublicKey fromPEMFormat(String pem) {
        return new RSAPublicKey(decodePEMFormat(pem));
    }

    @Override
    public String toPEMFormat() {
        return toPEMFormat0("PUBLIC KEY", getEncoded());
    }

    @Override
    public String toString() {
        return toPEMFormat();
    }

}
