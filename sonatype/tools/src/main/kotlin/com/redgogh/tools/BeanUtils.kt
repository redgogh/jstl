package com.redgogh.tools

import com.redgogh.tools.collection.Lists
import com.redgogh.tools.refection.UClass
import com.redgogh.tools.refection.UField

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
 * Bean工具类，方便实现对两个对象之间的属性拷贝，这属于浅拷贝。如果需要
 * 深拷贝，可以使用 JSON 工具类实现对象的深拷贝。
 *
 * 注意这个类中的不遵循 Java 所谓的狗屎规范。可以在无 Getter/Setter
 * 函数下对对象实现浅拷贝。
 *
 * @author RedGogh
 */
object BeanUtils {

    /**
     * #brief: 将 [src] 集合列表中的对象属性拷贝到新的 [T] 对象实例列表中。
     *
     * 该函数用于将一个对象的属性值 `浅拷贝` 到另一个对象中。具体而言：它会将 [src] 集合对象中的
     * 属性（包括空值属性）拷贝到根据 [destClass] 实例化出来的 [T] 对象中去。如果 [src] 属性中
     * 包含 [ignores] 中被忽略的属性，那么它将不会被拷贝。
     *
     * @param src
     *        源对象实例
     *
     * @param destClass
     *        目标对象类 类型，通过这个类型创建新的对象实例集合
     *
     * @param ignores
     *        被忽略的属性名
     */
    @JvmStatic
    fun <T> copyProperties(src: Collection<*>, destClass: Class<T>, vararg ignores: String): List<T> {
        val retvals = Lists.of<T>()
        for (any in src)
            retvals.add(copyProperties(any!!, destClass, *ignores))
        return retvals
    }

    /**
     * #brief: 创建 [destClass] 实例对象，然后将 [src] 中的数据浅拷贝到新的 [destClass]
     *         实例对象中。
     *
     * 该函数用于将一个对象的属性值 `浅拷贝` 到另一个对象中。具体而言：它会将 [src] 对象中的
     * 属性（包括空值属性）拷贝到实例化出来的 `instance` 对象中去。如果 [src] 属性中包含 [ignores] 中
     * 被忽略的属性，那么它将不会被拷贝。
     *
     * @param src
     *        源对象实例
     *
     * @param destClass
     *        目标对象类 类型，通过这个类型创建对象实例
     *
     * @param ignores
     *        被忽略的属性名
     */
    @JvmStatic
    fun <T> copyProperties(src: Any, destClass: Class<T>, vararg ignores: String): T {
        val instance = UClass.newInstance(destClass)
        copyProperties(src, instance!!, *ignores)
        return instance
    }

    /**
     * #brief: 将 [src] 对象中的属性拷贝到 [dest] 对象中。
     *
     * 该函数用于将一个对象的属性值 `浅拷贝` 到另一个对象中。具体而言：它会将 [src] 对象中的
     * 属性（包括空值属性）拷贝到 [dest] 对象中去。如果 [src] 属性中包含 [ignores] 中
     * 被忽略的属性，那么它将不会被拷贝。
     *
     * @param src
     *        源对象实例
     *
     * @param dest
     *        目标对象实例
     *
     * @param ignores
     *        被忽略的属性名
     */
    @JvmStatic
    fun copyProperties(src: Any, dest: Any, vararg ignores: String) {
        val uClass = UClass(dest)
        for (field in uClass.declaredFields) {
            val name = field.name
            if (ignores.isNotEmpty() && (name in ignores))
                continue
            UField.copyIgnoreError(src, dest, field.name)
        }
    }

}
