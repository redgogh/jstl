@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package com.redgogh.vortextools

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

import com.redgogh.vortextools.Optional.optionalIfError
import com.redgogh.vortextools.RedGogh.atos
import java.util.*
import java.util.regex.Pattern

/* java.lang.Double */
private typealias DWdouble = java.lang.Double

/** locale */
private val ZH_CN: Locale = Locale.getDefault()

/**
 * 字符串工具类
 */
object StringUtils {

    /**
     * 正则表达式对象缓存
     */
    private val patternCompiled: WeakHashMap<String, Pattern> = WeakHashMap()

    /**
     * 将字符串全部转为大写格式。
     *
     * @param wstr 字符串对象引用
     * @return 转换成大写格式后的字符串对象
     */
    @JvmStatic
    fun strupper(wstr: Any?) = atos(wstr).uppercase(ZH_CN)

    /**
     * 将字符串全部转为小写格式。
     *
     * @param wstr 字符串对象引用
     * @return 转换成小写格式后的字符串对象
     */
    @JvmStatic
    fun strlower(wstr: Any?) = atos(wstr).lowercase(ZH_CN)

    /**
     * 校验一个字符串是为空，如果字符串为 {@code null} 或字符串内部是
     * 空字符则返回 {@code true} 表示当前字符串是一个空字符串。反之返回
     * {@code false}.
     *
     * @param wstr
     *        校验的字符串参数
     *
     * @return {@code true} 表示字符串为空，反之 {@code false}
     */
    @JvmStatic
    fun stremp(wstr: Any?): Boolean = atos(wstr).isEmpty()

    /**
     * 校验一个字符串是否不为空，如果字符串为 {@code null} 或字符串内部是
     * 空字符则返回 {@code false} 表示当前字符串是一个空字符串。反之返回
     * {@code false}.
     *
     * @param wstr
     *        校验的字符串参数
     *
     * @return {@code true} 表示字符串不为空，反之 {@code false}
     */
    @JvmStatic
    fun strnemp(wstr: Any?): Boolean = !stremp(wstr)

    /**
     * 比较两个字符串的内容或地址是否相同，如果相同则返回 `true` 反之
     * 返回 `false`。
     *
     * @param a 字符串对象引用
     * @param b 字符串对象引用
     * @return 如果 `a == b` 或 `a.equals(b)`。
     */
    @JvmStatic
    fun streq(a: Any?, b: Any?): Boolean = a != null && atos(a) == atos(b)

    /**
     * 校验一个字符串是否不为空，如果字符串为 {@code null} 或字符串内部是
     * 空字符则返回 {@code false} 表示当前字符串是一个空字符串。反之返回
     * {@code false}.
     *
     * @param wstr
     *        校验的字符串参数
     *
     * @return {@code true} 表示字符串不为空，反之 {@code false}
     */
    @JvmStatic
    fun strne(a: Any?, b: Any?): Boolean = !streq(a, b)

    /**
     * 忽略大小写比较两个字符串的内容或地址是否相同，如果相同则返回 `true` 反之
     * 返回 `false`。
     *
     * @param a 字符串对象引用
     * @param b 字符串对象引用
     * @return 忽略大小写，如果 `a == b` 或 `a.equals(b)`。
     */
    @JvmStatic

    fun strieq(a: Any, b: Any): Boolean = streq(strupper(a), (b))

    /**
     * 返回一个格式化后的字符串类型，`vfmt` 为原字符串，在这个字符串中如果要指定某个
     * 地方需要被格式化，则添加上 %s 占位符。
     *
     * 伪代码示例（格式化 Hello World）：
     *
     *   var text = "Hello %s";
     *   println(strwfmt(text, "World"));
     *
     * @param vfmt 未被格式化的原字符串。字符串中需要携带占位符 %s，如果没有这个符号
     * 那么函数将不会格式化任何内容。
     * @param args 格式化参数，对应 %s 占位符的个数
     * @return 返回被格式化后的字符串
     */
    @JvmStatic
    fun strwfmt(vfmt: Any, vararg args: Any?): String = String.format(atos(vfmt), *args)

    /**
     * 判断一个字符串是否被包含在另一个字符串列表中，如果字符串在另一个
     * 列表中则返回 `true` 否则返回 `false`
     *
     * @param wstr 字符串对象引用
     * @param list 字符串列表
     * @return 如果 `wstr` 在字符串列表中返回 `true`
     */
    @JvmStatic
    fun strhas(wstr: Any?, vararg list: String): Boolean {
        for (item in list) {
            if (streq(atos(wstr), item))
                return true
        }
        return false
    }

    /**
     * 忽略大小写：判断一个字符串是否被包含在另一个字符串列表中，如果字符串
     * 在另一个列表中则返回 `true` 否则返回 `false`
     *
     * @param wstr 字符串对象引用
     * @param list 字符串列表
     * @return 如果 `wstr` 在字符串列表中返回 `true`
     */
    @JvmStatic
    fun strihas(wstr: Any?, vararg list: String): Boolean {
        for (item in list) {
            if (strieq(atos(wstr), item))
                return true
        }
        return false
    }

    /**
     * 返回查找并替换后的字符串对象，使用正则表达式查找匹配字符并将所有匹配的字符串
     * 替换为 [newValue] 字符串引用值。
     *
     * @param wstr     源字符串
     * @param regexp   正则表达式
     * @param newValue 代替匹配项的字符串
     * @return 返回替换后的值
     */
    @JvmStatic
    fun strrepall(wstr: Any?, regexp: String, newValue: String): String =
            atos(wstr).replace(Regex(regexp), newValue)

    /**
     * 返回分割后的字符串 token 列表， 根据 [regexp] 正则表达式对字符串进行一个
     * 分割，返回分割后的 token 列表。
     *
     * @param wstr    源字符串对象引用
     * @param regexp 正则表达式，用于匹配并分割字符串
     * @return 返回分割后的 token 列表。
     */
    @JvmStatic
    fun strtok(wstr: Any?, regexp: String): Array<String> =
        atos(wstr).split(Regex(regexp)).map { it.trim() }.toTypedArray()

    /**
     * 字符串裁剪，它和 [String.substring] 的功能是一样的，因为这个
     * 函数只是做了个一个 `substring` 的调用，它的出现是为了让代码更简洁。
     *
     * @param obj 一个 [Object] 对象，通过 `toString()` 转换成 `String`
     *            类型。
     * @param off 开始索引
     * @param len 结束索引
     * @return 返回截取好的字符串
     */
    @JvmStatic
    fun strcut(obj: Any?, off: Int, len: Int): String {
        return atos(obj, off, len)
    }

    /**
     * 解析一个字符串，判断字符串是不是数字类型，如果是的话则返回 `true` 否则返回
     * `false`.
     *
     * @param wstr 源字符串对象引用
     * @return 如果 [wstr] 字符串是数字类型返回 `true`。
     */
    @JvmStatic
    fun strdig(wstr: Any?): Boolean = optionalIfError({ DWdouble.parseDouble(atos(wstr)) }, orSuccess = true, orError = false)

    private fun patternCacheComputeIfAbsent(regexp: String): Pattern {
        /* 如果正则表达式编译规则不存在，则编译正则并保存。存在则直接
           获取数据返回。*/
        return patternCompiled.computeIfAbsent(regexp) { Pattern.compile(regexp) }
    }

    /**
     * #brief: 使用正则表达式匹配字符串中是否包含 `regexp` 参数
     *
     *
     *
     * 使用正则表达式匹配字符串中是否包含 `regexp` 参数。如果参数 `wstr` 中
     * 包含 `regexp` 则返回 `true`，反之返回 `false`。
     *
     * @param wstr
     * 字符串对象
     *
     * @param regexp
     * 正则表达式规则
     *
     * @param enablePatternCache
     * 是否开启 Pattern 对象缓存，如果开启了缓存则会从缓存中查找已经
     * 编译好的 Pattern 对象。
     *
     * @return 如果 `wstr` 中满足正则表达式 `regexp` 匹配条件则
     * 返回 `true`，反之返回 `false`。
     */
    private fun strxmatch(wstr: Any?,
                          regexp: String,
                          enablePatternCache: Boolean): Boolean {
        val pattern = (if (enablePatternCache) patternCacheComputeIfAbsent(regexp) else Pattern.compile(regexp))
        return pattern.matcher(atos(wstr)).find()
    }

    /**
     * #brief: 使用正则表达式匹配字符串中是否包含 `regexp` 参数
     *
     *
     *
     * 使用正则表达式匹配字符串中是否包含 `regexp` 参数。如果参数 `wstr` 中
     * 包含 `regexp` 则返回 `true`，反之返回 `false`。
     *
     *
     *
     * 这个函数默认不启用 `Pattern` 正则表达还匹配模式对象缓存。如需使用缓存
     * 请调用 [.strxmatch]
     *
     * @param wstr
     * 字符串对象
     *
     * @param regexp
     * 正则表达式规则
     *
     * @return 如果 `wstr` 中满足正则表达式 `regexp` 匹配条件则
     * 返回 `true`，反之返回 `false`。
     */
    fun strmatch(wstr: Any?, regexp: String): Boolean = strxmatch(wstr, regexp, false)

    /**
     * #brief: 使用正则表达式匹配字符串中是否包含 `regexp` 参数
     *
     *
     *
     * 使用正则表达式匹配字符串中是否包含 `regexp` 参数。如果参数 `wstr` 中
     * 包含 `regexp` 则返回 `true`，反之返回 `false`。
     *
     *
     *
     * X: 这个函数默认启用 `Pattern` 正则表达还匹配模式对象缓存。性能
     * 更高！
     *
     * @param wstr
     * 字符串对象
     *
     * @param regexp
     * 正则表达式规则
     *
     * @return 如果 `wstr` 中满足正则表达式 `regexp` 匹配条件则
     * 返回 `true`，反之返回 `false`。
     */
    fun strxmatch(wstr: Any?, regexp: String): Boolean = strxmatch(wstr, regexp, true)

}