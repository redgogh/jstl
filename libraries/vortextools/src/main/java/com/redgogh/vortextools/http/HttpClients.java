package com.redgogh.vortextools.http;

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

/* Creates on 2022/8/8. */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.redgogh.vortextools.Upgrade;
import com.redgogh.vortextools.exception.HttpRequestException;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.redgogh.vortextools.Assert.xassert;

/**
 * http 请求工具类
 *
 * @author RedGogh
 */
public class HttpClients {

    private static final int READ_TIMEOUT = 5; // seconds
    private static final int CONNECTION_TIMEOUT = 5; // seconds

    private static final QueryBuilder EMPTY_REQUEST_ARGUMENTS = new QueryBuilder();

    enum ResponseType {
        BYTE,
        STRING,
        BYTE_STRING,
        BYTE_STREAM,
        CHAR_STREAM,
    }

    /**
     * 指定 URL 参数发起一次 GET 请求。返回接收到的字符串。
     *
     * @param url
     *        请求 url
     *
     * @return 请求结果：字符串
     */
    public static String get(String url) {
        return get(url, null, (QueryBuilder) null);
    }

    /**
     * 指定 URL 参数发起一次 GET 请求。将返回结果序列化为指定
     * 的对象实例。
     *
     * @param url
     *        请求 url
     *
     * @param seri
     *        请求结果序列化类
     *
     * @return 请求结果：字符串
     */
    public static <T> T get(String url, Class<T> seri) {
        return get(url, null, null, seri);
    }

    /**
     * 指定 URL 参数发起一次 GET 请求。返回接收到的字符串。这个函数只需要
     * 传入请求头即可，无需传入请求参数。
     *
     * @param url
     *        请求 url
     *
     * @param headers
     *        请求头，如果没有可以传空或者 {@code null}
     *
     * @return 请求结果：字符串
     */
    public static String get(String url, Map<String, String> headers) {
        return get(url, headers, (QueryBuilder) null);
    }

    /**
     * 指定 URL 参数发起一次 GET 请求。返回接收到的字符串。这个函数只需要
     * 传入请求头即可，无需传入请求参数。并将返回结果序列化为指定的对象实例。
     *
     * @param url
     *        请求 url
     *
     * @param headers
     *        请求头，如果没有可以传空或者 {@code null}
     *
     * @return 请求结果：字符串
     */
    public static <T> T get(String url, Map<String, String> headers, Class<T> seri) {
        return get(url, headers, null, seri);
    }

    /**
     * 指定 URL 参数发起一次 GET 请求。返回接收到的字符串。这个函数只需要
     * 传入请求参数即可，无需传入请求头。并将返回结果序列化为指定的对象实例。
     *
     * @param url
     *        请求 url
     *
     * @param arguments
     *        请求参数，如果没有可以传空或者 {@code null}
     *
     * @return 请求结果：字符串
     */
    public static String get(String url, QueryBuilder arguments) {
        return get(url, null, arguments);
    }

    /**
     * 指定 URL 参数发起一次 GET 请求。返回接收到的字符串。这个函数只需要
     * 传入请求参数即可，无需传入请求头。
     *
     * @param url
     *        请求 url
     *
     * @param arguments
     *        请求参数，如果没有可以传空或者 {@code null}
     *
     * @param seri
     *        指定序列化类
     *
     * @return 请求结果：字符串
     */
    public static <T> T get(String url, QueryBuilder arguments, Class<T> seri) {
        return get(url, null, arguments, seri);
    }

    /**
     * 指定 URL 参数发起一次 GET 请求。返回接收到的字符串。这个函数需要
     * 指定所有信息。包含：请求头、请求参数。并将返回结果序列化为指定的对象
     * 实例。
     *
     * @param url
     *        请求 url
     *
     * @param headers
     *        请求头，如果没有可以传空或者 {@code null}
     *
     * @param arguments
     *        请求参数，如果没有可以传空或者 {@code null}
     *
     * @param seri
     *        指定序列化类
     *
     * @return 请求结果：字符串
     */
    public static <T> T get(String url, Map<String, String> headers, QueryBuilder arguments, Class<T> seri) {
        return JSONObject.parseObject(get(url, headers, arguments), seri);
    }

    /**
     * 指定 URL 参数发起一次 GET 请求。返回接收到的字符串。这个函数需要
     * 指定所有信息。包含：请求头、请求参数。
     *
     * @param url
     *        请求 url
     *
     * @param headers
     *        请求头，如果没有可以传空或者 {@code null}
     *
     * @param arguments
     *        请求参数，如果没有可以传空或者 {@code null}
     *
     * @return 请求结果：字符串
     */
    public static String get(String url, Map<String, String> headers, QueryBuilder arguments) {
        if (arguments == null)
            arguments = EMPTY_REQUEST_ARGUMENTS;
        return (String) get0(url, headers, arguments, ResponseType.STRING);
    }

    /**
     * 指定URL参数发起一次GET请求，参数不可携带到RequestBody中，
     * 参数只能在url填写。
     *
     * @param url
     *        请求url
     *
     * @param headers
     *        请求头，如果没有可以传空或者null
     *
     * @return 请求结果，返回JSON字符串
     */
    @SuppressWarnings("SameParameterValue")
    private static Object get0(String url, Map<String, String> headers, @NotNull QueryBuilder arguments,
                               ResponseType responseType) {
        Object retval;

        /* 构建参数 */
        url = arguments.argConcatBuild(url);

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .get();

        /* 添加 Header */
        if (headers != null && !headers.isEmpty())
            headers.forEach(requestBuilder::addHeader);

        Request request = requestBuilder.build();

        try (Response response = client.newCall(request).execute()) {
            /* 根据枚举类型获取响应数据 */
            retval = parseResponseObject(response, responseType);
            /* 断言请求是否成功 */
            xassert(response.isSuccessful(), "HTTP请求出错, CODE: {}, URL: {}, MESSAGE: {}",
                    response.code(), url, retval);
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }

        return retval;
    }


    /**
     * 指定URL发起一次简单POST请求，将返回结果序列化成指定Class对象
     *
     * @param url
     *        请求URL
     *
     * @param seri
     *        序列化指定Class对象
     *
     * @return 序列化后的对象
     */
    public static <T> T post(String url, Class<T> seri) {
        return post(url, (Object) null, null, seri);
    }

    /**
     * 指定URL发起一次简单POST请求，将返回结果序列化成指定Class对象
     *
     * @param url
     *        请求URL
     *
     * @param param
     *        请求参数
     *
     * @param seri
     *        序列化指定Class对象
     *
     * @return 序列化后的对象
     */
    public static <T> T post(String url, Object param, Class<T> seri) {
        return post(url, param, null, seri);
    }

    /**
     * 指定URL发起一次复杂POST请求，所有的请求数据都可作为参数传递：请求体、请求头。
     * 请求成功后将返回结果序列化成指定Class对象。
     *
     * @param url
     *        请求URL
     *
     * @param param
     *        请求参数
     *
     * @param headers
     *        请求头，如果没有可以传空或者null
     *
     * @param seri
     *        序列化指定Class对象
     *
     * @return 序列化后的对象
     */
    public static <T> T post(String url, Object param, Map<String, String> headers, Class<T> seri) {
        return JSONObject.parseObject(post(url, param, headers), seri);
    }

    /**
     * 指定URL发起一次POST请求，返回请求结果
     *
     * @param url 请求URL
     * @return 请求结果，返回JSON字符串
     */
    public static String post(String url) {
        return post(url, (Object) null, (Map<String, String>) null);
    }

    /**
     * 指定URL发起一次POST请求，返回请求结果
     *
     * @param url
     *        请求URL
     *
     * @param param
     *        请求参数
     *
     * @return 请求结果，返回JSON字符串
     */
    public static String post(String url, Object param) {
        return post(url, param, (Map<String, String>) null);
    }

    /**
     * 指定URL参数发起一次post请求，请求可携带body参数以及请求头。
     *
     * @param url
     *        请求url
     *
     * @param param
     *        请求的body参数，该参数可以是String字符串，也可以是一个对象
     *
     * @param headers
     *        请求头，如果没有可以传空或者null
     *
     * @return 请求结果，返回JSON字符串
     */
    public static String post(String url, Object param, Map<String, String> headers) {
        return (String) post0(url, param, headers, ResponseType.STRING);
    }

    /**
     * 指定URL参数发起一次post请求，请求可携带body参数以及请求头。
     *
     * @param url
     *        请求url
     *
     * @param param
     *        请求的body参数，该参数可以是String字符串，也可以是一个对象
     *
     * @param headers
     *        请求头，如果没有可以传空或者null
     *
     * @return 请求结果，返回JSON字符串
     */
    @SuppressWarnings("SameParameterValue")
    private static Object post0(String url, Object param, Map<String, String> headers,
                                ResponseType responseType) {
        Object retval;

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                param instanceof String ? (String) param : JSON.toJSONString(param));

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(requestBody);

        /* 添加 Header */
        if (headers != null && !headers.isEmpty())
            headers.forEach(requestBuilder::addHeader);

        Request request = requestBuilder.build();

        try (Response response = client.newCall(request).execute()) {
            /* 根据枚举类型获取响应数据 */
            retval = parseResponseObject(response, responseType);
            /* 断言请求是否成功 */
            xassert(response.isSuccessful(), "HTTP请求出错, CODE: {}, URL: {}, REQUEST BODY: {}, MESSAGE: {}",
                    response.code(), url, JSON.toJSONString(param), retval);
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }

        return retval;
    }

    /**
     * 获取响应数据，通过枚举类型判断请求需要获取什么样的数据从而返回出去。
     *
     * @param response
     *        响应对象
     *
     * @param responseType
     *        响应数据类型
     */
    @Upgrade(features = "OpenJDK17/EnhancedSwitchMigration")
    @SuppressWarnings("EnhancedSwitchMigration")
    private static Object parseResponseObject(Response response, ResponseType responseType)
            throws IOException {
        ResponseBody body = response.body();
        if (body != null) {
            switch (responseType) {
                case BYTE: return body.bytes();
                case STRING: return body.string();
                case BYTE_STRING: return body.byteString();
                case BYTE_STREAM: return body.byteStream();
                case CHAR_STREAM: return body.charStream();
            };
        }
        return null;
    }

}
