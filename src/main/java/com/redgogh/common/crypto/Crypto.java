package com.redgogh.common.crypto;

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

import com.redgogh.common.crypto.codec.Base64Codec;
import com.redgogh.common.crypto.codec.MD5Codec;
import com.redgogh.common.crypto.codec.SHA256Codec;
import com.redgogh.common.crypto.codec.URLCodec;

import java.util.UUID;

import static com.redgogh.common.BasicConverter.atos;
import static com.redgogh.common.StringUtils.strcut;
import static com.redgogh.common.StringUtils.strlen;

/**
 * 各种变量值生成器，用常用于生成 UUID、唯一标识符、MD5 等常用内容
 * 生成器。
 *
 * @author venorze
 */
public final class Crypto {

    public static final Base64 BASE64 = new Base64Codec(); // Base64
    public static final MD5    MD5    = new MD5Codec();    // MD5
    public static final SHA256 SHA256 = new SHA256Codec(); // sha256
    public static final URL    URL    = new URLCodec();    // url

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

}
