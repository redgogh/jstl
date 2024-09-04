package com.redgogh.libext.enums;

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

import com.redgogh.libext.exception.InvalidArgumentException;
import com.redgogh.libext.refection.UClass;

import static com.redgogh.libext.StringUtils.strieq;

/**
 * @author RedGogh
 */
public class Enumerates {

    @SuppressWarnings("unchecked")
    private static <E extends Enum<E>> E[] values(Class<? extends Enum<E>> enumClass) {
        return (E[]) new UClass(enumClass).staticInvoke("values");
    }

    @SuppressWarnings({"unchecked", "UnusedReturnValue"})
    public static <E extends Enum<E>> E checkout(Class<? extends Enum<E>> enumClass, String name) {
        Enum<E>[] values = values(enumClass);
        for (Enum<E> value : values)
            if (strieq(value.name(), name))
                return (E) value;
        throw new InvalidArgumentException("参数错误【%s】常量不存在！", name);
    }

}
