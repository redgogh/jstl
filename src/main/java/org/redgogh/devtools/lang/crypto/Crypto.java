package org.redgogh.devtools.lang.crypto;

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

import org.redgogh.devtools.lang.base.Capturer;
import org.redgogh.devtools.lang.crypto.codec.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.UUID;

import static org.redgogh.devtools.lang.base.BasicConverter.atos;
import static org.redgogh.devtools.lang.base.StringUtils.strcut;
import static org.redgogh.devtools.lang.base.StringUtils.strlen;

/**
 * `Crypto` 是一个工具类，提供了多种加密和解密算法的实现，支持常见的加密需求。
 *
 * <p>该类包含静态方法，可以直接调用，无需实例化对象。支持的算法包括 AES、MD5、SHA-256 等，能够
 * 满足用户对数据加密和解密的需求，确保数据的安全性。
 *
 * <p>本类的主要特点包括：
 * <ul>
 *     <li>提供多种加密算法，支持不同类型的数据处理。</li>
 *     <li>所有方法均为静态，方便直接调用，简化使用流程。</li>
 *     <li>包含基本的异常处理，确保在加密解密过程中提供适当的错误信息。</li>
 * </ul>
 *
 * <h2>使用示例</h2>
 * <pre>
 *     // 使用 AES 加密
 *     String encrypted = Crypto.AES.encrypt("Hello World", "mysecretkey");
 *
 *     // 使用 MD5 计算哈希
 *     String hash = Crypto.MD5.lower32("Hello World");
 * </pre>
 *
 * @author RedGogh
 * @since 1.0
 */
public final class Crypto {

    public static final Base64 BASE64 = new Base64Codec(); // BASE64
    public static final MD5    MD5    = new MD5Codec();    // MD5
    public static final SHA256 SHA256 = new SHA256Codec(); // SHA-256
    public static final URL    URL    = new URLCodec();    // URL
    public static final AES    AES    = new AESCodec();    // AES

    /**
     * 生成一个版本号
     *
     * @param major
     *        主版本号
     *
     * @param minor
     *        副版本号
     *
     * @param patch
     *        补丁版本
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

    /**
     * 字节码转 16 进制
     */
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

    /**
     * #brief: 生成并返回下一个随机的 AES 密钥，使用 Base64 编码格式表示
     *
     * <p>该方法利用 `KeyGenerator` 类生成一个 128 位的 AES 密钥，并将其编码为 Base64
     * 字符串以便于存储或传输。返回的密钥可用于加密或解密数据，确保数据的安全性。
     *
     * @return 生成的随机 AES 密钥的 Base64 编码字符串
     */
    public static String nextSecretKey() {
        return Capturer.call(() -> {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            SecretKey secretKey = keyGen.generateKey();
            return BASE64.encode(secretKey.getEncoded());
        });
    }

}
