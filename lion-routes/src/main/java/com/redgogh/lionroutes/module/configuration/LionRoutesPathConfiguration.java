package com.redgogh.lionroutes.module.configuration;

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
