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

/* Creates on 2023/4/29. */

/**
 * `ConvertMapper` 接口定义了一个通用的转换映射功能，
 * 用于将类型为 {@code T} 的值转换为类型为 {@code R} 的值。
 * 该接口的主要目的是提供一个抽象的方法，以便实现不同类型的转换逻辑。
 *
 * <p>实现此接口的类需要定义 {@code call} 方法，
 * 该方法接受一个 {@code T} 类型的参数并返回一个 {@code R} 类型的结果。
 *
 * <p>此接口适用于需要类型转换的场景，例如在数据处理、
 * 数据传输等过程中进行类型的转换与映射。
 *
 * @param <T> 输入值的类型
 * @param <R> 输出值的类型
 *
 * @author RedGogh
 */
public interface StreamMapper<T, R> {
    /**
     * 将给定的输入值转换为输出值。
     *
     * @param value 要转换的输入值
     * @return 转换后的输出值
     */
    R call(T value);
}
