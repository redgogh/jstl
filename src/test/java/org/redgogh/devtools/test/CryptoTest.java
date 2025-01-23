package org.redgogh.devtools.test;

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

import org.redgogh.devtools.crypto.Crypto;
import org.junit.Test;

import java.io.File;

@SuppressWarnings("ALL")
public class CryptoTest {

    /**
     * Base64 编码、解码测试
     */
    @Test
    public void base64EncodAndDecodeTest() {
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
    public void md5EncodTest() {
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
    public void sha256EncodTest() {
        String text = "Hello World";
        System.out.println("sha256: " + Crypto.SHA256.encode(text));
    }

    /**
     * sha256文件编码测试
     */
    @Test
    public void sha256FileEncodTest() {
        System.out.println(Crypto.SHA256.encode(new File("/home/1g.7z")));
    }

    /**
     * 版本生成测试
     */
    @Test
    public void versionGenerateTest() {
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
    public void aesTest() {
        String text = "牛子一掰，全场笑歪！";
        String secret = Crypto.nextSecretKey();
        System.out.println("AES secret key: " + secret);

        // Encoder
        String key = Crypto.AES.encrypt(text, secret);;
        System.out.println("AES text encrypt: " + key);

        // Decoder
        System.out.println("AES text decrypt: " + Crypto.AES.decrypt(key, secret));

    }

    @Test
    public void urlTest() {
        // System.out.println(Crypto.URL.encode("/audit-web/#/audit-web/settlementAudit/taskAllocationApproveEdit?taskId=19881F7C-94FE-11EF-9288-0242C0A8440E&instanceId=58AC7AA4-94FD-11EF-9288-0242C0A8440E&id=825DED38-911C-11EF-923B-0242C0A8441B"));
        System.out.println(Crypto.URL.decode("%2Faudit-web%2F%23%2Faudit-web%2FAuditReform%2FAuditAccountabilityResult%2Fedit%2FRejectIndex%3F"));
    }

}
