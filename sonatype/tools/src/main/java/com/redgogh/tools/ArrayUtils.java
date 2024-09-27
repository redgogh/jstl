package com.redgogh.tools;

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

import static com.redgogh.tools.Assert.throwIfFalse;

/**
 * 数组工具类
 *
 * @author RedGogh
 */
public class ArrayUtils {

    /**
     * 数据拷贝时对长度进行截断，使得数据可以像指针一样，在某个段落中前后操作
     * 数据。
     *
     * @param size 数组大小
     * @param off  偏移量
     * @param len  长度
     * @return 返回截断后的数据长度
     */
    public static int truncate(int size, int off, int len) {
        return len <= 0 ? (size - off) - Math.abs(len) : len;
    }

    public static void checkIndexSize(int off, int len, int size) {
        throwIfFalse(!((off + len ) > size), "Array offset and size out of index: %s", size);
    }

    /**
     * #brief：数组内存拷贝，将数组元素拷贝到另一个数组
     *
     * <P>该函数命名使用 c 语言中的内存拷贝函数命名。用于替换 {@link System#arraycopy} 函数
     * 使用，简化调用方式。可以拷贝任意数组对象。底层由 JVM C++ Native 方法实现。调用者无需
     * 关心实现。
     *
     * @param src
     *        原数组，被拷贝的数组对象。
     *
     * @param srcPos
     *        开始偏移量，从 {@code src[srcPos]} 作为拷贝起始位置。
     *
     * @param dest
     *        目标数组对象，将拷贝的数据放入 {@code dest} 对象中。
     *
     * @param destPos
     *        开始偏移量，从 {@code dest[destPos]} 作为拷贝起始位置。
     *
     * @param length
     *        拷贝长度，从偏移位置开始拷贝 {@code length} 个元素。
     */
    @SuppressWarnings("all")
    public static void arraycopy(Object src, int  srcPos,
                                Object dest, int destPos,
                                int length) {
        java.lang.System.arraycopy(src, srcPos, dest, destPos, length);
    }

    /**
     * #brief：拷贝数组中的数据到另一个新实例化的数组中
     *
     * <p>将数组总的数据拷贝到一个新的数组中，数组的长度介于 {@code original.length} 到
     * {@code -original.length} 之间。可以通过数组切片的形式拷贝数组。当 {@code len} 参
     * 数为负数时，那么则表示 {@code len=original.length - len} 的新数组长度。也就是从数组
     * 最后开始计算。如果 {@code len} 是 {@code 0} 则表示 从 original[off] - original[original.length]
     * 之间的数据。
     *
     * <p>该函数有很多相似函数，支持泛型对象拷贝。
     *
     * @param original
     *        原数组对象，将从这个数组对象中拷贝数据到新的数组对象中。
     *
     * @param off
     *        偏移量，拷贝起始位置将从 {@code origin[off]} 开始算。
     *
     * @param len
     *        拷贝长度，表示拷贝多长的数据到新的数组中，original[off] - original[original.length - len]。
     *        需要注意的是 {@code off} 的大小和 {@code len} 的大小加起来不能超过 {@code original.length}
     *        必须满足该条件 {@code (off + len) <= original.length} 否则会出现数组越界情况，
     *
     * @return 拷贝后的新数组对象。
     */
    public static byte[] arraycopy(byte[] original, int off, int len) {
        len = truncate(original.length, off, len);
        byte[] ret = new byte[len];
        arraycopy(original, off, ret, 0, len);
        return ret;
    }

    /**
     * #brief：拷贝数组中的数据到另一个新实例化的数组中
     *
     * <p>将数组总的数据拷贝到一个新的数组中，数组的长度介于 {@code original.length} 到
     * {@code -original.length} 之间。可以通过数组切片的形式拷贝数组。当 {@code len} 参
     * 数为负数时，那么则表示 {@code len=original.length - len} 的新数组长度。也就是从数组
     * 最后开始计算。如果 {@code len} 是 {@code 0} 则表示 从 original[off] - original[original.length]
     * 之间的数据。
     *
     * <p>该函数有很多相似函数，支持泛型对象拷贝。
     *
     * @param original
     *        原数组对象，将从这个数组对象中拷贝数据到新的数组对象中。
     *
     * @param off
     *        偏移量，拷贝起始位置将从 {@code origin[off]} 开始算。
     *
     * @param len
     *        拷贝长度，表示拷贝多长的数据到新的数组中，original[off] - original[original.length - len]。
     *        需要注意的是 {@code off} 的大小和 {@code len} 的大小加起来不能超过 {@code original.length}
     *        必须满足该条件 {@code (off + len) <= original.length} 否则会出现数组越界情况，
     *
     * @return 拷贝后的新数组对象。
     */
    public static char[] arraycopy(char[] original, int off, int len) {
        len = truncate(original.length, off, len);
        char[] ret = new char[len];
        arraycopy(original, off, ret, 0, len);
        return ret;
    }

}
