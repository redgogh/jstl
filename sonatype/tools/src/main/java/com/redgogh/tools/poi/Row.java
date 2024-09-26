package com.redgogh.tools.poi;

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

import com.redgogh.tools.StreamMapper;
import com.redgogh.tools.collection.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 表示一行数据，继承自 {@link ArrayList}，用于存储字符串类型的单元格数据。
 *
 * <p>此类提供了多种构造函数，以便于创建行对象，并可通过索引获取单元格数据。
 *
 * <p>示例用法：
 * <pre>
 *      Row row = new Row("Value1", "Value2");
 *      String firstValue = row.get(0);
 * </pre>
 *
 * @author RedGogh
 */
public class Row extends ArrayList<String> {

    /**
     * 创建一个空的行对象。
     */
    public Row() {
    }

    /**
     * 创建一个具有指定初始容量的行对象。
     *
     * @param initialCapacity 初始容量。
     */
    public Row(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * 创建一个行对象，使用指定的集合初始化。
     *
     * @param c 用于初始化行的字符串集合。
     */
    public Row(@NotNull Collection<? extends String> c) {
        super(c);
    }

    /**
     * 创建一个行对象，使用可变参数初始化。
     *
     * @param values 初始化行的字符串值。
     */
    public Row(String... values) {
        super(Lists.of(values));
    }

    /**
     * 获取指定索引的单元格数据，并通过提供的映射器进行转换。
     *
     * <p>此方法将行中指定索引的单元格数据映射到指定类型。
     *
     * <p>示例用法：
     * <pre>
     *      String value = row.get(0, String::toUpperCase);
     * </pre>
     *
     * @param i      单元格的索引。
     * @param mapper 用于转换的映射器。
     * @param <R>    转换后的返回类型。
     * @return 转换后的单元格数据。
     */
    public <R> R get(int i, StreamMapper<Object, R> mapper) {
        return mapper.call(get(i));
    }

}
