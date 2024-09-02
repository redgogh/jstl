package com.redgogh.vortextools.security;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 venorze                                                    *|
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

/* Creates on 2023/5/16. */

import com.redgogh.vortextools.exception.KeepLiveRuntimeException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

import static com.redgogh.vortextools.AnyObjects.atos;
import static com.redgogh.vortextools.Assert.quietly;
import static com.redgogh.vortextools.StringUtils.*;

/**
 * 各种变量值生成器，用常用于生成 UUID、唯一标识符、MD5 等常用内容
 * 生成器。
 *
 * @author venorze
 */
public final class Cryptographic {

    private static final String CRYPT_PREFIX_HTTP = "http://";
    private static final String CRYPT_PREFIX_HTTPS = "https://";

    /**
     * 生成一个版本号
     *
     * @param major
     *        主版本号（major << 22）
     *
     * @param minor
     *        副版本号（minor << 12 & 0x3ff）
     *
     * @param patch
     *        补丁版本 （patch & 0xfff）
     */
    public static int makeVersion(int major, int minor, int patch) {
        return major << 22 | minor << 12 | patch;
    }

    /**
     * 解析 {@link #makeVersion(int, int, int)} 生成的版本号，获取其中
     * 的主版本号内容并返回。
     *
     * @param version
     *        版本号
     *
     * @return 主版本号
     */
    public static int versionMajor(int version) {
        return version >> 22;
    }

    /**
     * 解析 {@link #makeVersion(int, int, int)} 生成的版本号，获取其中
     * 的次版本号内容并返回。
     *
     * @param version
     *        版本号
     *
     * @return 次版本号
     */
    public static int versionMinor(int version) {
        return version >> 12 & 0x3ff;
    }

    /**
     * 解析 {@link #makeVersion(int, int, int)} 生成的版本号，获取其中
     * 的补丁版本内容并返回。
     *
     * @param version
     *        版本号
     *
     * @return 补丁版本
     */
    public static int versionPatch(int version) {
        return version & 0xfff;
    }

    ////////////////////////////////////////////////////////////
    /// 唯一Id生成
    ////////////////////////////////////////////////////////////

    /**
     * @return 生成不带符号的UUID
     */
    public static String uuid() {
        return atos(UUID.randomUUID())
                .replace("-", "");
    }

    /**
     * 随机从 uuid 字符串中取 {@code n} 个字符组合返回。
     *
     * @param n
     *        随机从 uuid 中取 {@code number} 位字符
     *
     * @return 返回没有任何符号的 UUID
     */
    public static String uuid(int n) {
        String uuid = uuid();
        return strcut(uuid, 0, n);
    }

    /** 字节码转 16 进制 */
    public static String toByteHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            String tmp = Integer.toHexString(b & 0xFF);
            if (strlen(tmp) == 1)
                builder.append("0");
            builder.append(tmp);
        }
        return atos(builder);
    }

    ////////////////////////////////////////////////////////////
    /// 加密
    ////////////////////////////////////////////////////////////

    /** 十六进制字符集 */
    static final char[] hexDigits = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static final CryptEncoder Encoder = new CryptEncoder() {
        @Override
        public String sha256(String source) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(source.getBytes(StandardCharsets.UTF_8));
                return toByteHex(messageDigest.digest());
            } catch (Exception e) {
                throw new KeepLiveRuntimeException(e);
            }
        }

        @Override
        public String md5lower32(String source) {
            return md5lower32(source.getBytes());
        }

        @Override
        public String md5lower32(byte[] b) {
            return md5(b, 32);
        }

        @Override
        public String md5lower16(String source) {
            return md5lower16(source.getBytes());
        }

        @Override
        public String md5lower16(byte[] b) {
            return md5(b, 16);
        }

        @Override
        public String md5upper32(String source) {
            return md5upper32(source.getBytes());
        }

        @Override
        public String md5upper32(byte[] b) {
            return md5lower32(b).toUpperCase();
        }

        @Override
        public String md5upper16(String source) {
            return md5upper16(source.getBytes());
        }

        @Override
        public String md5upper16(byte[] b) {
            return md5lower16(b).toUpperCase();
        }

        @Override
        public String base64(String source) {
            return base64(source.getBytes());
        }

        @Override
        public String base64(byte[] b) {
            return Base64.getUrlEncoder().encodeToString(b);
        }

        /** 使用 Java 原生函数生成 MD5 字符串。注意如果是获取 16 位的加密字符串，原始 MD5
         *  值会向前移动 8 位，然后截取出后 16 位返回。 */
        private String md5(byte[] b, int n) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(b);
                byte[] digests = md.digest();
                char[] chars = new char[32];
                int k = 0;
                for (int i = 0; i < 16; i++) {
                    byte byte0 = digests[i];
                    chars[k++] = hexDigits[byte0 >>> 4 & 0xf];
                    chars[k++] = hexDigits[byte0 & 0xf];
                }
                /* 判断是获取 16 位的 md5 字符串还是 16 位的 */
                int flag = n > 16 ? 0 : 8;
                return strcut(chars, flag, (n + flag));
            } catch (NoSuchAlgorithmException e) {
                throw new KeepLiveRuntimeException(e);
            }
        }

        @Override
        public String url(String source) {
            return url(source, "UTF-8");
        }

        @Override
        public String url(String source, String enc) {
            try {
                String temporary = "";
                if (source.startsWith(CRYPT_PREFIX_HTTPS)) {
                    source = strcut(source, strlen(CRYPT_PREFIX_HTTPS), 0);
                    temporary = CRYPT_PREFIX_HTTPS;
                } else if (source.startsWith(CRYPT_PREFIX_HTTP)) {
                    source = strcut(source, strlen(CRYPT_PREFIX_HTTP), 0);
                    temporary = CRYPT_PREFIX_HTTP;
                }
                return strwfmt("%s%s", temporary, URLEncoder.encode(source, enc));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    };

    public interface CryptEncoder {
        /** sha256 加密（小写） */
        String sha256(String source);
        /** 将字符串加密成MD5（32位小） */
        String md5lower32(String source);
        /** 将字节数组加密成MD5（32位小） */
        String md5lower32(byte[] b);
        /** 将字符串加密成MD5（16位小） */
        String md5lower16(String source);
        /** 将字节数组加密成MD5（16位小） */
        String md5lower16(byte[] b);
        /** 将字符串加密成MD5（32位大） */
        String md5upper32(String source);
        /** 将字节数组加密成MD5（32位大） */
        String md5upper32(byte[] b);
        /** 将字符串加密成MD5（16位大） */
        String md5upper16(String source);
        /** 将字节数组加密成MD5（16位大） */
        String md5upper16(byte[] b);
        /** 将字符串使用base64算加密 */
        String base64(String source);
        /** 将字符数组使用base64算加密 */
        String base64(byte[] b);
        /** URL编码 */
        String url(String source);
        /** URL编码 */
        String url(String source, String enc);
    }

    ////////////////////////////////////////////////////////////
    /// 解密
    ////////////////////////////////////////////////////////////

    public static final CryptDecoder Decoder = new CryptDecoder() {
        @Override
        public String base64(String src) {
            return atos(Base64.getUrlDecoder().decode(src));
        }

        @Override
        public String url(String source) {
            return url(source, "UTF-8");
        }

        @Override
        public String url(String source, String enc) {
            return quietly(() -> URLDecoder.decode(source, enc));
        }
    };

    public interface CryptDecoder {
        /** 解析 base64 编码 */
        String base64(String src);
        /** URL解码 */
        String url(String source);
        /** URL解码 */
        String url(String source, String enc);
    }

}
