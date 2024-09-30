package com.redgogh.lionroutes.module.filter;

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
