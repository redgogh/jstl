package com.redgogh.examples;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2019-2024 RedGogh All rights reserved.                          *|
|*                                                                                  *|
|*    Licensed under the Apache License, Version 2.0 (the "License");               *|
|*    you may not use this file except in compliance with the License.              *|
|*    You may obtain a copy of the License at                                       *|
|*                                                                                  *|
|*        http://www.apache.org/licenses/LICENSE-2.0                                *|
|*                                                                                  *|
|*    Unless required by applicable law or agreed to in writing, software           *|
|*    distributed under the License is distributed on an "AS IS" BASIS,             *|
|*    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.      *|
|*    See the License for the specific language governing permissions and           *|
|*    limitations under the License.                                                *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import com.redgogh.common.crypto.Crypto;
import org.junit.Test;

@SuppressWarnings("ALL")
public class CryptoTest {

    /**
     * Base64 编码、解码测试
     */
    @Test
    public void base64EncodAndDecodeExample() {
        String text = "牛子一掰，全场笑歪！";

        // Encoder
        String base64Str = Crypto.BASE64.encode(text);
        System.out.println("Base64 text encode: " + base64Str);

        // Decoder
        String originText = Crypto.BASE64.decode(base64Str);
        System.out.println("Base64 text decode: " + originText);

    }

    /**
     * md5 编码测试
     */
    @Test
    public void md5EncodExample() {
        String text = "Hello World";
        // lower16
        System.out.println("lower16: " + Crypto.MD5.lower16(text));
        // lower32
        System.out.println("lower32: " + Crypto.MD5.lower32(text));
        // upper16
        System.out.println("upper16: " + Crypto.MD5.upper16(text));
        // upper32
        System.out.println("upper32: " + Crypto.MD5.upper32(text));
    }

    /**
     * sha256编码测试
     */
    @Test
    public void sha256EncodExample() {
        String text = "Hello World";
        System.out.println("sha256: " + Crypto.SHA256.encode(text));
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

    /**
     * AES 编码、解码测试
     */
    @Test
    public void aesExample() {
        String text = "牛子一掰，全场笑歪！";
        String secret = "1234567890123456";

        // Encoder
        String key = Crypto.AES.encrypt(text, secret);;
        System.out.println("AES text encrypt: " + key);

        // Decoder
        System.out.println("AES text decrypt: " + Crypto.AES.decrypt(key, secret));

    }

}
