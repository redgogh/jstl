package com.redgogh.tools.http;

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

import com.alibaba.fastjson.JSONObject;
import com.redgogh.tools.Optional;
import com.redgogh.tools.StringUtils;
import com.redgogh.tools.io.ByteBuffer;
import com.redgogh.tools.io.IOUtils;
import lombok.Getter;
import okhttp3.Headers;
import okhttp3.ResponseBody;

import java.io.InputStream;
import java.util.Map;

import static com.redgogh.tools.BasicConverts.anyeq;
import static com.redgogh.tools.BasicConverts.atos;
import static com.redgogh.tools.StringUtils.streq;
import static com.redgogh.tools.StringUtils.strmatch;
import static com.redgogh.tools.io.ByteBuffer.SEEK_SET;

/**
 * `Response` 是一个继承自 `JSONObject` 的类，用于表示一个包含状态码和数据的响应对象。
 * 该类提供了对响应状态码的访问和比较功能，并且可以通过不同的构造方法来初始化响应内容。
 *
 * <p>该类包括以下功能：
 * <ul>
 *     <li>通过状态码和 JSON 字符串或 `Map` 对象初始化响应内容。</li>
 *     <li>提供获取状态码的方法。</li>
 *     <li>提供状态码比较的方法，判断是否等于特定值。</li>
 *     <li>判断响应是否表示成功（状态码为 200）。</li>
 * </ul>
 *
 * <h2>构造方法</h2>
 * <ul>
 *     <li>{@link #Response}: 使用状态码和 JSON 字符串初始化响应对象。</li>
 * </ul>
 *
 * <h2>主要方法</h2>
 * <ul>
 *     <li>{@link #code()}: 获取响应的状态码。</li>
 *     <li>{@link #codeEquals(int)}: 判断响应的状态码是否与指定的码相等。</li>
 *     <li>{@link #isSuccess()}: 判断响应是否表示成功（状态码为 200）。</li>
 * </ul>
 *
 * <h2>使用示例</h2>
 * <pre>
 *     // 使用状态码和 JSON 字符串初始化响应对象
 *     Response response = new Response(200, "{\"key\": \"value\"}");
 *
 *     // 获取状态码
 *     int statusCode = response.code();
 *
 *     // 判断状态码是否为 200
 *     boolean isSuccess = response.isSuccess();
 * </pre>
 *
 * @author RedGogh
 *
 * @see JSONObject
 * @since 1.0
 */
public class Response extends JSONObject {

    /**
     * Http 请求状态码
     */
    @Getter
    private final int code;

    /**
     * 如果接口没有正常的 JSON 返回对象等结构的话，那么 message 就是
     * 接口返回信息。有可能是 `Not Found` 等文本。
     */
    @Getter
    private String message;

    /**
     * 响应头
     */
    private final Headers headers;

    /**
     * 文件或二进制资源
     */
    private transient final ByteBuffer byteBuffer =
            ByteBuffer.allocate(16 * IOUtils.KB);

    /**
     * #brief: 使用状态码和 JSON 字符串初始化响应对象
     *
     * <p>该构造方法通过状态码和 JSON 字符串来创建一个 `Response` 对象。JSON 字符串会被解析为
     * `JSONObject`，并存储在响应对象中。
     *
     * @param code 响应的状态码
     * @param responseBody 响应内容
     */
    @SuppressWarnings("unchecked")
    public Response(int code, Headers headers, ResponseBody responseBody) {
        this.code = code;
        this.headers = headers;

        /* 处理响应 */
        String content = Optional.ifError(responseBody::string, "{}");
        Object object = Optional.ifError(() -> JSONObject.parseObject(content), content);

        if (object instanceof String)
            this.message = atos(object, StringUtils::strip);

        if (object instanceof Map)
            putAll((Map<String, Object>) object);
    }

    /**
     * #brief: 获取响应的状态码
     *
     * <p>该方法返回响应对象的状态码，通常用于判断响应的状态。
     *
     * @return 响应的状态码
     */
    public int code() {
        return code;
    }

    /**
     * #brief: 判断响应的状态码是否与指定的码相等
     *
     * <p>该方法用于比较响应的状态码是否与传入的状态码相等。
     *
     * @param cmpCode 要比较的状态码
     * @return 如果响应的状态码等于指定的状态码，则返回 `true`；否则返回 `false`
     */
    public boolean codeEquals(int cmpCode) {
        return streq(code, cmpCode);
    }

    /**
     * #brief: 判断响应是否表示成功
     *
     * <p>该方法用于判断响应的状态码是否为 200（表示成功）。
     *
     * @return 如果响应的状态码为 200，则返回 `true`；否则返回 `false`
     */
    public boolean isSuccess() {
        return codeEquals(200);
    }

    /**
     * 比较给定的键对应的值与传入的 {@code value} 是否相等。
     * 通过调用 {@code get(name)} 获取对应的值，并使用
     * {@code anyeq} 方法判断是否相等。
     *
     * <p>此方法用于检查存储的值是否与给定值匹配。
     *
     * @param name 键的名称，用于获取对应的值
     * @param value 需要比较的值
     *
     * @return 如果两个值相等，则返回 {@code true}，否则返回 {@code false}
     */
    public boolean valueEquals(String name, Object value) {
        return anyeq(get(name), value);
    }

    /**
     * 获取指定名称的头部信息。
     *
     * <p>通过提供的 {@code name} 从头部集合 {@code headers}
     * 中检索对应的值。返回值为 {@code String} 类型，如果
     * 该名称的头部信息不存在，则返回 {@code null}。
     *
     * @param name 头部的名称
     * @return 对应名称的头部信息，如果不存在则返回 {@code null}
     */
    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public String toString() {
        /* success */
        if (!isEmpty())
            return super.toString();

        /* failed */
        JSONObject retval = new JSONObject();
        retval.put("code", code);
        retval.put("message", message);
        return retval.toString();
    }

}
