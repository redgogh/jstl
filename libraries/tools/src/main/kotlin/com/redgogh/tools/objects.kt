package com.redgogh.tools

import com.redgogh.tools.StringUtils.strupper
import com.redgogh.tools.StringUtils.strxmatch
import com.redgogh.tools.io.ByteBuf
import com.redgogh.tools.io.ByteBuf.SEEK_SET
import com.redgogh.tools.io.IOUtils
import java.io.PrintStream

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

/**
 * 全局静态类
 */
object AnyObjects {

    /////////////////////////////////////////////////////////////////////////////////////////////
    /// int
    /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * #brief：自动识别 `obj` 类型，并转换成 Integer
     *
     *
     *
     * 自动识别 `obj` 类型，并转换成 Integer，如果 `obj` 是 String
     * 类型，则会通过 `Integer.valueOf()` 方法转换。如果是 byte 则通过字节
     * 缓冲区进行转换。
     *
     * @param obj
     * 要被转换的对象
     *
     * @return 转换后的 int 类型数据。
     */
    @JvmStatic
    fun atoi(obj: Any?): Int {
        if (obj is Int) return obj
        return if (obj is ByteArray) atoi(obj as ByteArray?) else atos(obj).toInt()
    }

    /**
     * #brief：从 `b[0]` 往后取 4 个字节转为 int 类型
     *
     *
     *
     * 从 `b[off]` 往后取 4 个字节转为 int 类型。数组长度必须保证不能
     * 小于 4 个字节。
     *
     * @param b
     * 字节数组，数组长度最低不能小于 4 个字节
     *
     * @return 转换后的 int 类型数据。
     */
    @JvmStatic
    fun atoi(b: ByteArray?): Int {
        return atoi(b, 0)
    }

    /**
     * #brief：从 `b[off]` 往后取 4 个字节转为 int 类型
     *
     *
     *
     * 从 `b[off]` 往后取 4 个字节转为 int 类型。数组长度必须保证不能
     * 小于 4 个字节。如果使用了 `off` 字段。那么必须保证 `b.length - off >= 4`。
     * 如果不满足这些条件则抛出数组越界异常。
     *
     * @param b
     * 字节数组，数组长度最低不能小于 4 个字节
     *
     * @param off
     * 偏移量，从 `b[off]` 往后取 4 个字节。
     *
     * @return 转换后的 int 类型数据。
     */
    @JvmStatic
    fun atoi(b: ByteArray?, off: Int): Int {
        return ByteBuf.wrap(b, off, Integer.BYTES)
                .seek(SEEK_SET, 0).readInt()
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /// long
    /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * #brief：自动识别 `obj` 类型，并转换成 Long
     *
     *
     *
     * 自动识别 `obj` 类型，并转换成 Long，如果 `obj` 是 String
     * 类型，则会通过 `Long.valueOf()` 方法转换。如果是 byte 则通过字节
     * 缓冲区进行转换。
     *
     * @param obj
     * 要被转换的对象
     *
     * @return 转换后的 long 类型数据。
     */
    @JvmStatic
    fun atol(obj: Any?): Long {
        if (obj is Long) return obj
        if (obj is Number) return obj.toLong()
        return if (obj is ByteArray) atol(obj as ByteArray?) else atos(obj).toLong()
    }

    /**
     * #brief：从 `b[0]` 往后取 8 个字节转为 long 类型
     *
     *
     *
     * 从 `b[off]` 往后取 8 个字节转为 long 类型。数组长度必须保证不能
     * 小于 8 个字节。
     *
     * @param b
     * 字节数组，数组长度最低不能小于 8 个字节
     *
     * @return 转换后的 long 类型数据。
     */
    @JvmStatic
    fun atol(b: ByteArray?): Long {
        return atol(b, 0)
    }

    /**
     * #brief：从 `b[off]` 往后取 8 个字节转为 long 类型
     *
     *
     *
     * 从 `b[off]` 往后取 8 个字节转为 long 类型。数组长度必须保证不能
     * 小于 8 个字节。如果使用了 `off` 字段。那么必须保证 `b.length - off >= 8`。
     * 如果不满足这些条件则抛出数组越界异常。
     *
     * @param b
     * 字节数组，数组长度最低不能小于 8 个字节
     *
     * @param off
     * 偏移量，从 `b[off]` 往后取 8 个字节。
     *
     * @return 转换后的 long 类型数据。
     */
    @JvmStatic
    fun atol(b: ByteArray?, off: Int): Long {
        return ByteBuf.wrap(b, off, java.lang.Long.BYTES)
                .seek(SEEK_SET, 0).readLong()
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    /// boolean
    /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 将 [Object] 对象实例转换成`Boolean`类型的对象实例。修改原来的 [Object] 对象不会对
     * 新的创建的`Boolean`对象实例有任何影响，并且这个函数也不会抛出异常。如果对象是 `null` 的话那
     * 么则返回 `false`
     *
     * @param obj
     * `Object` 对象实例
     *
     * @return 根据传入的其他对象实例转换成`Boolean`对象实例。
     *
     * @see String.valueOf
     */
    @JvmStatic
    fun atobool(obj: Any?): Boolean {
        if (obj is Boolean) return obj
        if (obj is Number) return obj.toInt() > 0
        return strxmatch(strupper(obj), "TRUE|ON|Y|YES")
    }

    /**
     * `atos` 方法的简化重载形式，不使用自定义映射。
     *
     * @param any 要转换的输入对象，可以是任意类型
     * @return 转换后的字符串
     */
    @JvmStatic
    fun atos(any: Any?): String = atos(any, null)

    /**
     * `atos` 方法用于将任意类型的对象转换为字符串。提供了两种重载形式，
     * 一种不使用映射，另一种可以通过 `StreamMapping` 进行自定义映射。
     *
     * 如果输入为 `null`，则返回空字符串。如果输入已经是字符串，则直接返回该字符串。
     * 如果输入是 `CharArray` 或 `ByteArray`，则将其转换为字符串。如果输入类型不匹配，
     * 且提供了 `StreamMapping`，则使用该映射将输入对象转换为字符串；否则，将对象的字符串表示形式返回。
     *
     * @param any 要转换的输入对象，可以是任意类型
     * @param mapping 自定义的映射函数（可选），用于将对象转换为字符串
     * @return 转换后的字符串
     */
    @JvmStatic
    fun atos(any: Any?, mapping: StreamMapping<Any, String>?): String {
        if (any == null)
            return ""
        if (any is String)
            return valueMap(any, mapping)
        if (any is CharArray)
            return valueMap(atos(any, 0, any.size), mapping)
        if (any is ByteArray)
            return valueMap(atos(any, 0, any.size), mapping)
        return valueMap("$any", mapping)
    }

    /** value 映射 */
    private fun valueMap(any: String, mapping: StreamMapping<Any, String>?): String
        = mapping?.map(any) ?: any

    /**
     * 通过 `obj` 子字符串、`off` 数组偏移量和 `len` 长度分配
     * 一个新的字符串对象。`off` 是字符数组中第一个字符的索引位置。`len` 参数
     * 是整个字符串的长度。
     *
     * @param obj 子字符串对象实例
     * @param off 子字符串中第一个字符开始的索引位置
     * @param len 新的字符串长度，该长度不能超过 `sub` 被截取的子字符串长度
     * @return 根据 `off` 和 `len` 分配出来的新字符串
     * @throws NullPointerException `sub` 子字符串参数不能为空
     * @throws ArrayIndexOutOfBoundsException 如果参数 `len` 超出整个子字符串的大小后会抛出数组越界异常。
     */
    @JvmStatic
    fun atos(obj: Any?, off: Int, len: Int): String {
        return atos(atos(obj), off, len)
    }

    /**
     * 通过 `sub` 子字符串、`off` 数组偏移量和 `len` 长度分配
     * 一个新的字符串对象。`off` 是字符数组中第一个字符的索引位置。`len` 参数
     * 是整个字符串的长度。
     *
     * @param sub 子字符串对象实例
     * @param off 子字符串中第一个字符开始的索引位置
     * @param len 新的字符串长度，该长度不能超过 `sub` 被截取的子字符串长度。如果长度
     *            是 `0` 那么表示到最后，如果是负数，表示截取到 len - n
     * @return 根据 `off` 和 `len` 分配出来的新字符串
     * @throws NullPointerException `sub` 子字符串参数不能为空
     * @throws ArrayIndexOutOfBoundsException 如果参数 `len` 超出整个子字符串的大小后会抛出数组越界异常。
     */
    fun atos(sub: String, off: Int, len: Int): String {
        return atos(sub.toCharArray(), off, len)
    }

    /**
     * 通过 [b] 字符数组、[off] 数组偏移量和 [len] 长度分配
     * 一个新的字符串对象。[off] 是字符数组中第一个字符的索引位置。[len] 参数
     * 是整个字符串的长度。
     * <p>
     * 分配新的字符串后，修改字节数组不会对新分配的字符串造成影响。
     *
     * @param b 字节数组
     * @param off 字节数组中第一个字符开始的索引位置
     * @param len 新的字符串长度，该长度不能超过 {@code b} 字节数组长度
     * @return 根据 {@code off} 和 {@code len} 分配出来的新字符串
     * @throws ArrayIndexOutOfBoundsException 如果参数 {@code len} 超出整个字符数组
     *                                        的大小后会抛出数组越界异常。
     * @see String#String(byte[], int, int)
     */
    @JvmStatic
    fun atos(b: CharArray, off: Int, len: Int): String = String(ArrayUtils.arraycopy(b, off, len))

    /**
     * 通过 [b] 字节数组、[off] 数组偏移量和 [len] 长度分配
     * 一个新的字符串对象。[off] 是字符数组中第一个字符的索引位置。[len] 参数
     * 是整个字符串的长度。
     * <p>
     * 分配新的字符串后，修改字节数组不会对新分配的字符串造成影响。
     *
     * @param b 字节数组
     * @param off 字节数组中第一个字符开始的索引位置
     * @param len 新的字符串长度，该长度不能超过 {@code b} 字节数组长度
     * @return 根据 {@code off} 和 {@code len} 分配出来的新字符串
     * @throws ArrayIndexOutOfBoundsException 如果参数 {@code len} 超出整个字符数组
     *                                        的大小后会抛出数组越界异常。
     * @see String#String(byte[], int, int)
     */
    @JvmStatic
    fun atos(b: ByteArray, off: Int, len: Int): String = String(ArrayUtils.arraycopy(b, off, len))

    /**
     * 标准格式化打印输出
     */
    @JvmStatic
    fun printf(vfmt: String, vararg args: Any): PrintStream = IOUtils.stdout.printf(vfmt, *args)

}