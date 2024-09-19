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
