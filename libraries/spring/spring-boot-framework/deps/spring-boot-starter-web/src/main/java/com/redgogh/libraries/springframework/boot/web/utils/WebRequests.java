package com.redgogh.libraries.springframework.boot.web.utils;


import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

import static com.redgogh.tools.StringUtils.strnemp;
import static com.redgogh.tools.StringUtils.strtok;

/**
 * @author lts
 * Create time 2022/4/19
 */
public class WebRequests {

    /**
     * 获取HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取请求 id
     */
    public static String getRequestId() {
        return getHttpServletRequest().getRequestId();
    }

    /**
     * 获取用户真实ip
     */
    public static String getInetHostAddress() {
        HttpServletRequest request = getHttpServletRequest();
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (strnemp(ipAddresses) || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (strnemp(ipAddresses) || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (strnemp(ipAddresses) || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (strnemp(ipAddresses) || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (strnemp(ipAddresses)) {
            ip = strtok(ipAddresses, ",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (strnemp(ipAddresses) || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    /**
     * 获取请求头
     */
    public static String getHeader(String key) {
        return getHttpServletRequest().getHeader(key);
    }

    /**
     * 获取UserAgent
     */
    public static UserAgent getUserAgent() {
        return UserAgent.parseUserAgentString(getHeader("users-agent"));
    }

    /**
     * 获取token
     */
    public static String getAuthorization() {
        return getHeader("Authorization");
    }

    /**
     * 设置请求属性
     */
    public static void setAttribute(String key, Object value) {
        getHttpServletRequest().setAttribute(key, value);
    }

    /**
     * 获取请求属性
     */
    public static Object getAttribute(String key) {
        return getHttpServletRequest().getAttribute(key);
    }

    /**
     * 获取请求属性
     */
    public static String getString(String key) {
        return String.valueOf(getHttpServletRequest().getAttribute(key));
    }

}
