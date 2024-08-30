package com.redgogh.lionroutes.module.filter;

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

/* Creates on 2023/6/26. */

import com.redgogh.lionroutes.framework.LionRoutesFilter;
import com.redgogh.lionroutes.framework.LionRoutesFilterContext;
import com.redgogh.lionroutes.module.configuration.LionRoutesPathConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * #brief：请求地址拦截<p>
 *
 * 根据规则拦截请求配置，如果请求的 URL 中包含配置在黑名单中
 * 的 URI，那么则当前请求不会被放行。
 *
 * @author RedGogh
 */
@Order(1)
@Component
public class RequestLionRoutesFilter extends LionRoutesFilter {

    /** 路由配置 */
    @Autowired
    private LionRoutesPathConfiguration lionRoutesPathConfiguration;

    /** 黑名单校验 */
    private Mono<Void> isBlackPath(LionRoutesFilterContext context) {
        return lionRoutesPathConfiguration.matchBlackPathPattern(context.getRequestPath()) ?
                context.configureErrorResponse(HttpStatus.FORBIDDEN) : FINEMONO;
    }

    @Override
    public Mono<Void> filterable(LionRoutesFilterContext context) {
        Mono<Void> retmono;
        /* 如果请求接口中包含黑名单中的 URI 则不放行 */
        if ((retmono = isBlackPath(context)) != FINEMONO)
            return retmono;
        return context.nextChainFilter();
    }

}
