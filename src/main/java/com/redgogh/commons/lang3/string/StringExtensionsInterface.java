package com.redgogh.commons.lang3.string;

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

import static com.redgogh.commons.lang3.utils.BasicConverter.atos;
import static com.redgogh.commons.lang3.string.StringUtils.*;

/**
 * 定义字符串操作类型的枚举类。
 *
 * <p>该枚举类用于列举常见的字符串操作类型。
 *
 * <h2>功能特点</h2>
 * <ul>
 *     <li>易于扩展，支持后续增加更多字符串操作类型。</li>
 *     <li>提供统一的枚举类型，便于管理和使用不同的字符串操作。</li>
 * </ul>
 *
 * <h2>使用示例</h2>
 * <pre>
 *     strcut(a, off, len, STRING_IFACE_TRIM_EXT)
 * </pre>
 *
 * @author Red Gogh
 * @since 1.0
 */
public enum StringExtensionsInterface {

    /**
     * 字符串修剪操作，去除字符串前后的空白字符
     */
    STRING_IFACE_TRIM_EXT {
        @Override
        public String apply(String input) {
            return strip(input);
        }
    },

    /**
     * 将字符串转换为大写字母
     */
    STRING_IFACE_UPPER_CASE_EXT {
        @Override
        public String apply(String input) {
            return uppercase(input);
        }
    },

    /**
     * 将字符串转换为小写字母
     */
    STRING_IFACE_LOWER_CASE_EXT {
        @Override
        public String apply(String input) {
            return lowercase(input);
        }
    },

    /**
     * 将字符串首字母大写
     */
    STRING_IFACE_CAPITALIZE_EXT {
        @Override
        public String apply(String input) {
            return strcut(input, 0, 1, STRING_IFACE_UPPER_CASE_EXT) + strcut(input,1, 0);
        }
    },

    /**
     * 字符串反转
     */
    STRING_IFACE_REVERSE_EXT {
        @Override
        public String apply(String input) {
            return atos(new StringBuilder(input).reverse());
        }
    },

    /**
     * 去除字符串中的所有空格
     */
    STRING_IFACE_REMOVE_SPACES_EXT {
        @Override
        public String apply(String input) {
            return strrexp(input, "\\s+", "");
        }
    },

    /**
     * 下划线格式转驼峰
     */
    STRING_IFACE_LINE_HMP_EXT {
        @Override
        public String apply(String input) {
            return strlinehmp(input);
        }
    }
    ;

    /** 应用字符串操作 */
    public abstract String apply(String input);

}