package com.redgogh.tools.enums;

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

/* Create on 2019/6/14 */

import com.redgogh.tools.exception.InvalidArgumentException;
import com.redgogh.tools.refection.UClass;

import static com.redgogh.tools.StringUtils.strieq;

/**
 * @author RedGogh
 */
public class Enumerates {

    /**
     * #brief: 获取指定枚举类的所有枚举值
     *
     * <p>该方法通过反射机制获取枚举类的所有枚举值。需要传入枚举类的 `Class` 对象，并返回
     * 枚举值数组。由于使用了类型转换，因此标记了 `@SuppressWarnings("unchecked")` 注解以
     * 忽略未经检查的类型转换警告。
     *
     * @param enumClass 枚举类的 `Class` 对象
     * @param <E> 枚举类的类型参数
     * @return 枚举类中定义的所有枚举值的数组
     */
    @SuppressWarnings("unchecked")
    private static <E extends Enum<E>> E[] values(Class<? extends Enum<E>> enumClass) {
        return (E[]) new UClass(enumClass).staticInvoke("values");
    }

    /**
     * #brief: 根据名称查找并返回指定枚举类的枚举值
     *
     * <p>该方法查找枚举类中名称匹配的枚举值。如果找到与提供的名称匹配的枚举值，则返回该
     * 枚举值。如果没有找到，则抛出 `InvalidArgumentException` 异常，指明参数错误和常量不存在。
     *
     * <p>方法首先调用 `values` 方法获取所有枚举值，然后遍历这些枚举值，比较它们的名称与
     * 提供的名称。如果找到匹配项，则返回对应的枚举值。否则，抛出 `InvalidArgumentException` 异常。
     *
     * @param enumClass 枚举类的 `Class` 对象
     * @param name 要查找的枚举值名称
     * @param <E> 枚举类的类型参数
     * @return 与提供名称匹配的枚举值
     * @throws InvalidArgumentException 如果提供的名称没有匹配的枚举值
     */
    @SuppressWarnings({"unchecked", "UnusedReturnValue"})
    public static <E extends Enum<E>> E checkout(Class<? extends Enum<E>> enumClass, String name) {
        Enum<E>[] values = values(enumClass);
        for (Enum<E> value : values)
            if (strieq(value.name(), name))
                return (E) value;
        throw new InvalidArgumentException("参数错误【%s】常量不存在！", name);
    }

}
