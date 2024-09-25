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

import com.redgogh.tools.refection.UClass;
import com.redgogh.tools.refection.UField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Bean工具类，方便实现对两个对象之间的属性拷贝，这属于浅拷贝。如果需要
 * 深拷贝，可以使用 JSON 工具类实现对象的深拷贝。
 *
 * <p>注意这个类中的不遵循 Java 所谓的狗屎规范。可以在无 Getter/Setter
 * 函数下对对象实现浅拷贝。
 *
 * @author RedGogh
 */
public class BeanUtils {

    /**
     * 将 [src] 集合列表中的对象属性拷贝到新的 [T] 对象实例列表中。
     *
     * @param src        源对象实例
     * @param destClass  目标对象类 类型，通过这个类型创建新的对象实例集合
     * @param ignores    被忽略的属性名
     * @param <T>       目标对象类型
     * @return          新对象实例列表
     */
    public static <T> List<T> copyProperties(Collection<?> src, Class<T> destClass, String... ignores) {
        List<T> retvals = new ArrayList<>();
        for (Object any : src) {
            retvals.add(copyProperties(any, destClass, ignores));
        }
        return retvals;
    }

    /**
     * 创建 [destClass] 实例对象，然后将 [src] 中的数据浅拷贝到新的 [destClass]
     * 实例对象中。
     *
     * @param src        源对象实例
     * @param destClass  目标对象类 类型，通过这个类型创建对象实例
     * @param ignores    被忽略的属性名
     * @param <T>       目标对象类型
     * @return          新的对象实例
     */
    public static <T> T copyProperties(Object src, Class<T> destClass, String... ignores) {
        T instance = UClass.newInstance(destClass);
        copyProperties(src, instance, ignores);
        return instance;
    }

    /**
     * 将 [src] 对象中的属性拷贝到 [dest] 对象中。
     *
     * @param src    源对象实例
     * @param dest   目标对象实例
     * @param ignores 被忽略的属性名
     */
    public static void copyProperties(Object src, Object dest, String... ignores) {
        UClass uClass = new UClass(dest);
        for (UField field : uClass.getDeclaredFields()) {
            String name = field.getName();
            if (ignores.length > 0 && contains(ignores, name)) {
                continue;
            }
            UField.copyIgnoreError(src, dest, field.getName());
        }
    }

    private static boolean contains(String[] array, String value) {
        for (String item : array) {
            if (item.equals(value)) {
                return true;
            }
        }
        return false;
    }

}
