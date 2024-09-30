package com.redgogh.lionroutes.module.configuration;

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

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.List;

/**
 * 网关配置项
 *
 * @author RedGogh
 */
@Data
@Component
@ConfigurationProperties(prefix = "lion-routes.path")
public class LionRoutesPathConfiguration {

    /**
     * 路由黑名单
     */
    private List<String> black;

    /**
     * 路由白名单
     */
    private List<String> white;

    private static final AntPathMatcher antPathMatcher =
            new AntPathMatcher();

    /** 判断某个路径是否被包含在黑名单下 */
    public boolean matchBlackPathPattern(String path) {
        for (String pattern : black) {
            if (antPathMatcher.match(pattern, path))
                return true;
        }
        return false;
    }

    /** 判断某个路径是否被包含在白名单下 */
    public boolean matchWhitePathPattern(String path) {
        for (String pattern : white) {
            if (antPathMatcher.match(pattern, path))
                return true;
        }
        return false;
    }

}
