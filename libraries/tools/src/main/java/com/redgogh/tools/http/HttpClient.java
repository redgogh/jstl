package com.redgogh.tools.http;

import com.alibaba.fastjson.JSON;
import com.redgogh.tools.collection.Maps;
import com.redgogh.tools.exception.HttpRequestException;
import com.redgogh.tools.io.File;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.redgogh.tools.AnyObjects.atos;
import static com.redgogh.tools.Assert.*;
import static com.redgogh.tools.StringUtils.strhas;
import static com.redgogh.tools.StringUtils.strupper;

/**
 * `HttpClient` 是一个用于发送 HTTP 请求的客户端工具类。该类支持多种 HTTP 请求方法，
 * 并提供了设置请求头、查询参数构建器、请求体等功能。使用该类可以方便地构建和发送 HTTP 请求，
 * 并接收响应。
 *
 * <p>该类包括以下功能：
 * <ul>
 *     <li>通过指定的请求方法和 URL 初始化 HTTP 客户端。</li>
 *     <li>设置请求头、查询参数构建器、请求体等。</li>
 *     <li>发送 HTTP 请求并返回响应对象。</li>
 * </ul>
 *
 * <h2>枚举类</h2>
 * <ul>
 *     <li>{@link HttpMethod}: 定义了支持的 HTTP 请求方法，如 GET、POST 等。</li>
 * </ul>
 *
 * @author RedGogh
 * @since 1.0
 */
@SuppressWarnings("all")
public class HttpClient {

    /** HTTP 请求方法，如 GET、POST 等。 */
    private final HttpMethod method;

    /** 请求的 URL 地址。 */
    private String url;

    /** 查询参数构建器，用于构建请求的查询参数。 */
    private QueryBuilder queryBuilder;

    /** 请求体，可以是任意对象类型。 */
    private Object object;

    /** 禁用 SSL 证书验证 */
    private boolean sslVerficationDisable = false;

    /** HTTP 请求头的集合。 */
    private final Map<String, String> headers = Maps.of();

    /**
     * `HttpMethod` 枚举定义了支持的 HTTP 请求方法。
     */
    enum HttpMethod {
        GET, POST, PUT, PATCH, DELETE, HEAD
    }

    /**
     * #brief: 构造一个 `HttpClient` 实例
     *
     * <p>根据请求方法和 URL 初始化 `HttpClient` 对象。请求方法会被转换为大写，
     * 并与 `HttpMethod` 枚举匹配，不支持的请求方法会抛出异常。
     *
     * @param method HTTP 请求方法（如 GET、POST 等）
     * @param url 请求的 URL
     * @throws NullPointerException 如果请求方法不受支持
     */
    private HttpClient(String method, String url) {
        String upperMethod = strupper(method);
        this.method = ignore(() -> HttpMethod.valueOf(upperMethod));
        throwIfNull(this.method, "不支持的请求方式 - %s", this.method.name());
        this.url = url;
    }

    /**
     * #brief: 创建一个新的 `HttpClient` 实例
     *
     * <p>该方法是一个静态工厂方法，通过指定的请求方法和 URL 创建一个新的 `HttpClient` 实例。
     *
     * @param method HTTP 请求方法
     * @param url 请求的 URL
     * @return 新的 `HttpClient` 实例
     */
    public static HttpClient open(String method, String url) {
        return new HttpClient(method, url);
    }

    /**
     * #brief: 添加请求头
     *
     * <p>该方法用于向请求中添加一个新的请求头。
     *
     * @param name 请求头的名称
     * @param value 请求头的值
     * @return 当前 `HttpClient` 实例，以支持链式调用
     */
    public HttpClient addHeader(String name, String value) {
        headers.put(name, value);
        return this;
    }

    /**
     * #brief: 设置查询参数构建器
     *
     * <p>该方法用于设置查询参数构建器，用于构建带有查询参数的请求 URL。
     *
     * @param queryBuilder 用于构建查询参数的 `QueryBuilder` 对象
     * @return 当前 `HttpClient` 实例，以支持链式调用
     */
    public HttpClient setQueryBuilder(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
        return this;
    }

    /**
     * #brief: 设置请求体
     *
     * <p>该方法用于设置请求体，可以为任意对象，实际请求时会将其转换为 JSON 格式。
     *
     * @param object 请求体对象
     * @return 当前 `HttpClient` 实例，以支持链式调用
     */
    public HttpClient setRequestBody(Object object) {
        if (strhas(method, HttpMethod.GET, HttpMethod.HEAD))
            throw new HttpRequestException("GET 或 HEAD 方法不支持请求主体。");

        this.object = object;
        return this;
    }

    /**
     * #brief: 禁用 SSL 验证
     *
     * <p>该方法用于禁用 SSL 证书验证。调用此方法后，`HttpClient` 将在发送 HTTP 请求时
     * 不对 SSL 证书进行验证。这通常用于开发或测试环境中，以避免证书验证错误影响测试进度。
     *
     * <p>注意：在生产环境中禁用 SSL 验证可能会导致安全风险，建议谨慎使用。
     *
     * @return 当前的 `HttpClient` 实例，允许链式调用
     */
    public HttpClient sslVerifierDisable() {
        this.sslVerficationDisable = true;
        return this;
    }

    /**
     * #brief: 发送 HTTP 请求
     *
     * <p>该方法使用默认的读超时和连接超时发送 HTTP 请求，并返回响应对象。
     * 默认读取和链接超时时间 16 秒。
     *
     * @return 响应对象 `Response`
     */
    public Response newCall() {
        return newCall(16, 16);
    }

    /**
     * #brief: 发送 HTTP 请求
     *
     * <p>该方法使用指定的读超时和连接超时发送 HTTP 请求，并返回响应对象。支持设置请求体、
     * 查询参数和请求头。请求体默认为 `application/json` 格式，支持多部分请求体（MultipartBody）。
     *
     * @param readTimeout 读取响应的超时时间（秒）
     * @param connectTimeout 连接请求的超时时间（秒）
     * @return 响应对象 `Response`
     * @throws HttpRequestException 如果请求发送失败
     */
    public Response newCall(Integer readTimeout, Integer connectTimeout) {
        /* init url. */
        if (queryBuilder != null)
            url = queryBuilder.argConcatBuild(url);

        OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder();
        clientBuilder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
        clientBuilder.readTimeout(readTimeout, TimeUnit.SECONDS);

        if (sslVerficationDisable) {
            clientBuilder.sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.getX509TrustManager());
            clientBuilder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
        }

        OkHttpClient client = clientBuilder.build();

        /* response */
        Response retval;

        try (okhttp3.Response response = client.newCall(request()).execute()) {
            ResponseBody body = response.body();
            retval = new Response(response.code(), body == null ? "{}" : body.string());

            xassert(response.isSuccessful(), "HTTP请求出错（%s）\n    - URL：%s \n    - Request Body：%s \n    - Message: %s",
                    response.code(), url, JSON.toJSONString(object), retval);

            return retval;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    /**
     * @return 根据请求体类型构建正确的请求主体。
     */
    private RequestBody getRequestBody() {
        /* request body */
        RequestBody requestBody = null;

        /* is MultipartBody. */
        if (object instanceof MultipartBody) {
            MultipartBody multipartBody = (MultipartBody) object;

            /* okhttp MultipartBody */
            okhttp3.MultipartBody.Builder builder = new okhttp3.MultipartBody.Builder()
                    .setType(okhttp3.MultipartBody.FORM);

            for (Map.Entry<String, Object> entry : multipartBody.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof File) {
                    File file = (File) value;
                    builder.addFormDataPart(entry.getKey(), file.getName(),
                            RequestBody.create(file, MediaType.parse("text/plain")));
                } else {
                    builder.addFormDataPart(entry.getKey(), atos(value));
                }
            }

            requestBody = builder.build();
        }

        /* default application/json raw body. */
        if (requestBody == null) {
            requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                    object instanceof String ? (String) object : JSON.toJSONString(object));
        }

        return requestBody;
    }

    /**
     * @return 构建请求对象
     */
    private Request request() {
        /* create request builder. */
        Request.Builder requestBuilder = new Request.Builder()
                .url(url);

        /* pick method */
        switch (method) {
            case GET:
                requestBuilder.get();
                break;
            case POST:
                requestBuilder.post(getRequestBody());
                break;
            case PUT:
                requestBuilder.put(getRequestBody());
                break;
            case PATCH:
                requestBuilder.patch(getRequestBody());
                break;
            case DELETE:
                requestBuilder.delete(getRequestBody());
                break;
            case HEAD:
                requestBuilder.head();
                break;
        }

        /* add headers. */
        if (!Maps.isEmpty(headers))
            headers.forEach(requestBuilder::addHeader);

        /* final execute request. */
        return requestBuilder.build();
    }

}
