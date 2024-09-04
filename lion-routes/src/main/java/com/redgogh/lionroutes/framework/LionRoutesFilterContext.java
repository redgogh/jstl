package com.redgogh.lionroutes.framework;

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

/* Create on 2023/8/11 */

import com.redgogh.tools.collection.Collects;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.redgogh.tools.collection.Collects.collectionBeg;

/**
 * @author RedGogh
 */
public class LionRoutesFilterContext {

    /**
     * #brief：ServerWebExchange 代表一个 HTTP 请求和响应的上下文<p>
     *
     * ServerWebExchange 提供了访问请求和响应的方法，以及其他与请求-响应周期相关的信息。
     * 通过 ServerWebExchange，你可以获取请求的信息（如请求头、请求体、URI 等），操作
     * 响应（如设置响应头、响应体等），以及与请求-响应周期相关的上下文信息。
     */
    final ServerWebExchange exchange;

    /**
     * #brief：GatewayFilterChain 代表 Spring Cloud Gateway 中的过滤器链<p>
     *
     * GatewayFilterChain 允许每个过滤器处理请求并将处理后的请求传递给链中的下一个过滤器。<p>
     *
     * 当请求进入网关时，它会经过一系列的网关过滤器。每个过滤器都可以在请求转发到目标服务之前或之后执行特定操作，
     * 例如身份验证、请求转发、请求修改等。GatewayFilterChain 提供了一个 filter 方法，用于过滤器处理请求
     * 并将处理结果传递给下一个过滤器。<p>
     *
     * 通过实现 GatewayFilter 接口并与 GatewayFilterChain 协同工作，您可以开发自定义过滤器，对请求进行操作，
     * 同时使用 GatewayFilterChain 将处理结果传递给下一个过滤器。
     *
     * @see ServerWebExchange 表示 HTTP 请求和响应的上下文。
     */
    final GatewayFilterChain chain;

    private LionRoutesFilterContext(ServerWebExchange exchange, GatewayFilterChain chain) {
        this.exchange = exchange;
        this.chain = chain;
    }

    static LionRoutesFilterContext of(ServerWebExchange exchange, GatewayFilterChain chain) {
        return new LionRoutesFilterContext(exchange, chain);
    }

    /**
     * #brief: 从当前过滤链中进入到下一个过滤器<p>
     *
     * 从当前 Gateway 的过滤链中进入到下一个过滤器，从 {@link GlobalFilter} 接口
     * 中的 `filter` 函数返回。
     */
    public Mono<Void> nextChainFilter() {
        return chain.filter(exchange);
    }

    /** 获取当前 exchange 请求上下文中的请求体 */
    private ServerHttpRequest _request() {
        return exchange.getRequest();
    }

    /** 获取当前 exchange 请求上下文中的响应体 */
    private ServerHttpResponse _response() {
        return exchange.getResponse();
    }

    /** 从当前请求上下文中获取请求头 */
    public String getHeader(String header) {
        List<String> headerValues = _request().getHeaders().get(header);
        return headerValues != null ? collectionBeg(headerValues) : null;
    }

    /** 获取当前请求上下文数据中的请求路径 */
    public String getRequestPath() {
        return _request().getURI().getPath();
    }

    /** 配置请求异常响应体 */
    public Mono<Void> configureErrorResponse(HttpStatus status) {
        ServerHttpResponse response = _response();
        response.setStatusCode(status);
        return response.setComplete();
    }

}
