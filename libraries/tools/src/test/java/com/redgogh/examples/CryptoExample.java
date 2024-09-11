package com.redgogh.examples;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 RedGogh                                                    *|
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

import com.redgogh.tools.security.Crypto;
import org.junit.Test;

@SuppressWarnings("ALL")
public class CryptoExample {

    /**
     * Base64 编码、解码测试
     */
    @Test
    public void base64EncodAndDecodeExample() {
        String str = "Cryptographic: Hello World";

        // encode
        String encode = Crypto.Encoder.base64(str);
        System.out.printf("base64 url encode code: %s\n", encode);

        // decode
        String decode = Crypto.Decoder.base64(encode);
        System.out.printf("base64 url decode code: %s\n", decode);
    }

    /**
     * 版本生成测试
     */
    @Test
    public void versionGenerateExample() {
        int version = Crypto.makeVersion(1, 8, 1);
        System.out.println("make version = " + version);
        System.out.println("  - major = " + Crypto.versionMajor(version));
        System.out.println("  - minor = " + Crypto.versionMinor(version));
        System.out.println("  - patch = " + Crypto.versionPatch(version));
    }

}
