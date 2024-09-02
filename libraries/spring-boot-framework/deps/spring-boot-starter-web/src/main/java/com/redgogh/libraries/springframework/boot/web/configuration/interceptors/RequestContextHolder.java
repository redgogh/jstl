package com.redgogh.libraries.springframework.boot.web.configuration.interceptors;

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

/* Create on 2023/8/24 */

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.AntPathMatcher;

import static com.redgogh.vortextools.AnyObjects.atos;

/**
 * 请求上下文，在当前线程范围内是一个全局共享对象，只要在线程范围内这个
 * 对象都是独一无二的。使用 {@link #getContext()} ()} 函数获取线
 * 程本地 RequestContextHolder 对象。
 *
 * @author RedGogh
 */
public class RequestContextHolder {

    public static ThreadLocal<RequestContextHolder> requestContextHolderThreadLocal
            = new ThreadLocal<>();

    /** ant 路径匹配器 */
    private static final AntPathMatcher antPathMatcher =
            new AntPathMatcher();

    private static final String REQUEST_USER_OBJECT_KEY = "__request_user_object__";

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final Object handler;

    public RequestContextHolder(HttpServletRequest request, HttpServletResponse response, Object handler) {
        this.request = request;
        this.response = response;
        this.handler = handler;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /// static
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 创建本地线程的请求上下文对象
     */
    static void newRequestContextHolder(HttpServletRequest request, HttpServletResponse response, Object handler) {
        requestContextHolderThreadLocal.set(new RequestContextHolder(request, response, handler));
    }

    /**
     * 获取本地线程请求上下文对象
     */
    public static RequestContextHolder getContext() {
        return requestContextHolderThreadLocal.get();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /// non-static
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public HttpServletRequest getHttpServletRequest() {
        return request;
    }

    public HttpServletResponse getHttpServletResponse() {
        return response;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /// non-static for request
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @return 返回请求 cookie 列表
     */
    public Cookie[] getRequestCookies() {
        return getHttpServletRequest().getCookies();
    }

    /**
     * @return 获取请求头信息
     */
    public String getRequestHeader(String name) {
        return getHttpServletRequest().getHeader(name);
    }

    /**
     * @return 获取请求方法，例如：GET/POST
     */
    public String getRequestMethod() {
        return getHttpServletRequest().getMethod();
    }

    /**
     * @return 获取请求 URI
     */
    public String getRequestURI() {
        return atos(getHttpServletRequest().getRequestURI());
    }

    /**
     * @return 如果请求 URI 满足 ant 规则，则返回 true。匹配
     *         示例：doRequestPathMatch("/users/**")
     */
    public boolean doRequestPathMatch(String pattern) {
        return antPathMatcher.match(pattern, getRequestURI());
    }

    /**
     * 设置请求属性
     *
     * @param key 属性名
     * @param value 属性对象
     */
    public void setRequestAttribute(String key, Object value) {
        getHttpServletRequest().setAttribute(key, value);
    }

    /**
     * 设置请求用户对象
     */
    public void setRequestContextUser(Object obj) {
        setRequestAttribute(REQUEST_USER_OBJECT_KEY, obj);
    }

    /**
     * @return 获取请求用户对象
     */
    @SuppressWarnings("unchecked")
    public <T> T getRequestContextUser() {
        return (T) getRequestAttribute(REQUEST_USER_OBJECT_KEY);
    }

    /**
     * @return 返回使用 setRequestAttribute 函数设置的属性值
     */
    public Object getRequestAttribute(String key) {
        return getHttpServletRequest().getAttribute(key);
    }

    /**
     * @return 返回当前请求 id
     */
    public String getRequestId() {
        return getHttpServletRequest().getRequestId();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /// non-static for response
    ////////////////////////////////////////////////////////////////////////////////////////////////

}
