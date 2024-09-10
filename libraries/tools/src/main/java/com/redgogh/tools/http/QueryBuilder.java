package com.redgogh.tools.http;

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

import java.util.LinkedHashMap;
import java.util.Map;

import static com.redgogh.tools.AnyObjects.atos;
import static com.redgogh.tools.StringUtils.strwfmt;

/**
 * `QueryBuilder` 是一个继承自 `LinkedHashMap<String, String>` 的类，用于构建 HTTP 请求的查询参数。
 * 该类存储键值对形式的查询参数，并提供方法将这些参数拼接到 URL 中。
 *
 * <p>主要功能包括：
 * <ul>
 *     <li>存储和管理查询参数。</li>
 *     <li>将查询参数格式化并拼接到指定的 URL 上。</li>
 * </ul>
 *
 * <p>使用场景：
 * <ul>
 *     <li>动态构建 URL 查询参数以便发送 HTTP 请求。</li>
 *     <li>简化查询参数的拼接过程，避免手动拼接字符串。</li>
 * </ul>
 *
 * @see LinkedHashMap
 * @since 1.0
 * @author RedGogh
 */
public class QueryBuilder extends LinkedHashMap<String, String> {

    /**
     * #brief: 将查询参数拼接到 URL 上
     *
     * <p>该方法将当前 `QueryBuilder` 中的所有查询参数拼接到指定的 URL 上。
     *
     * @param url 要拼接查询参数的原始 URL
     * @return 包含查询参数的完整 URL
     */
    public String argConcatBuild(String url) {
        if (isEmpty())
            return url;

        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, String> entry : entrySet())
            builder.append(strwfmt("%s=%s&", entry.getKey(), entry.getValue()));
        String finalArguments = atos(builder, 0, -1); /* 删掉最后一个字符 ‘&’ */

        return strwfmt("%s?%s", url, finalArguments);
    }
}
