package com.redgogh.lionroutes.framework;

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

/* Creates on 2023/7/4. */

import com.redgogh.lionroutes.framework.mono.FineMono;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * #brief：过滤器上下文<p>
 *
 * 抽象出过滤器中的对象，形成上下文数据链。方便子类可以在任何地方使用当前
 * 过滤器上下文中的对象实例。
 *
 * @author RedGogh
 */
public abstract class LionRoutesFilter implements GlobalFilter {

    /**
     * 这个 Mono 对象实例表示执行正常，未出现任何错误
     */
    protected static final Mono<Void> FINEMONO
            = new FineMono<>();

    private final ThreadLocal<LionRoutesFilterContext> localContextHolder =
            new ThreadLocal<>();

    /**
     * #brief: 过滤器处理实现<p>
     *
     * 这个函数是子类必须实现的，用来执行过滤器请求。父类为它们抽象了 exchange、
     * 以及 chain 参数到公共对象中。
     */
    public abstract Mono<Void> filterable(LionRoutesFilterContext context);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        localContextHolder.set(
                LionRoutesFilterContext.of(exchange, chain));
        return filterable(localContextHolder.get());
    }

}
