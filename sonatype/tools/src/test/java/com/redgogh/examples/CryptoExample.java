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
