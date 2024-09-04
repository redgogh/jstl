package com.redgogh.tools.refection;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 RedGogh                                                       *|
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

/* Creates on 2019/5/16. */

import com.redgogh.tools.collection.Collects;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static com.redgogh.tools.Assert.throwIfError;
import static com.redgogh.tools.Assert.throwIfNull;
import static com.redgogh.tools.collection.Collects.*;

/**
 * 类属性`Object`对象封装
 *
 * @author RedGogh   
 */
public class UClass {
    /**
     * 对象类属性
     */
    @Getter
    private transient final Class<?> descriptor;
    /**
     * 属性列表
     */
    private final Map<String, UField> properties = Collects.asMap();

    public UClass(Object instance) {
        this(instance.getClass());
    }

    public UClass(Class<?> descriptor) {
        this.descriptor = descriptor;
        /* init */
        List<UField> descriptorProperties = getDescriptorProperties(descriptor, asList());
        for (UField property : descriptorProperties) {
            String name = property.getName();
            if (!properties.containsKey(name))
                properties.put(property.getName(), property);
        }
    }

    public static UClass forName(String className) {
        Class<?> clazz = (Class<?>) throwIfError(() -> Class.forName(className));
        return new UClass(clazz);
    }

    ////////////////////////////////////////////////////////////////////////////
    // static
    ////////////////////////////////////////////////////////////////////////////

    /**
     * 实例化一个类对象，根据类的构造器传入参数数据。
     *
     * @param descriptor
     *        类对象
     *
     * @param parameters
     *        构造器参数，如果使用空构造器就不传
     *
     * @return 类对象实例
     */
    public static <T> T newInstance(Class<T> descriptor, Object... parameters) {
        try {
            Class<?>[] parametersClassArray = toClassArray(parameters);
            Constructor<T> constructor = descriptor.getConstructor(parametersClassArray);
            return constructor.newInstance(parameters);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将参数数组转换成类型数组
     */
    public static Class<?>[] toClassArray(Object... parameters) {
        List<Class<?>> parametersClassList = Collects.asList();
        for (Object parameter : parameters)
            parametersClassList.add(parameter.getClass());
        Class<?>[] parametersClassArray = new Class<?>[parametersClassList.size()];
        parametersClassList.toArray(parametersClassArray);
        return parametersClassArray;
    }

    ////////////////////////////////////////////////////////////////////////////
    // java class methods
    ////////////////////////////////////////////////////////////////////////////

    public String getName() {
        return descriptor.getName();
    }

    public String getSimpleName() {
        return descriptor.getSimpleName();
    }

    ////////////////////////////////////////////////////////////////////////////
    // methods
    ////////////////////////////////////////////////////////////////////////////

    /**
     * #brief：实例化当前类对象，可选构造函数参数<p>
     * <p>
     * 实例化当前类对象，可选构造函数参数。通过 {@code args} 参数列表自动选择
     * 实例化对象的构造器。如果没有参数默认使用无参构造器初始化。
     *
     * @param args 构造器参数（可变
     * @return 实例化类过后的对象实例
     */
    @SuppressWarnings("unchecked")
    public <T> T newInstance(Object... args) {
        return (T) newInstance(descriptor, args);
    }

    /**
     * #brief：获取当前类下的所有属性列表<p>
     * <p>
     * 获取当前类下的所有属性列表，属性列表已经是封装好了的`ObjectProperty`
     * 对象实例，可以直接使用。
     *
     * @return 封装好的`ObjectProperty`实例列表
     */
    public List<UField> getProperties() {
        return getProperties(true);
    }

    /**
     * #brief：获取当前类下的所有属性列表<p>
     * <p>
     * 获取当前类下的所有属性列表，的属性列表已经是封装好了的`UField`
     * 对象实例，可以直接使用。
     *
     * @return 封装好的`ObjectProperty`实例列表
     */
    public List<UField> getProperties(boolean isFilter) {
        if (!isFilter)
            return Collects.asList(properties.values());
        /* 筛选出修饰符非 static & final 的属性列表 */
        return listFilter(properties.values(), value -> (!value.isStatic() && !value.isFinal()));
    }

    /**
     * 递归查找属性列表
     */
    static List<UField> getDescriptorProperties(Class<?> descriptor,
                                                List<UField> descriptorProperties) {
        // 获取所有成员
        Field[] properties = descriptor.getDeclaredFields();
        descriptorProperties.addAll(listMap(properties, UField::new));

        Class<?> superclass = descriptor.getSuperclass();
        if (superclass != Object.class)
            getDescriptorProperties(superclass, descriptorProperties);

        return descriptorProperties;
    }

    @SuppressWarnings({"unchecked"})
    public <R> R readProperty(String name, Object instance) {
        return (R) throwIfNull(properties.get(name), "`%s`属性成员不存在", name)
                .read(instance);
    }

    public Object staticInvoke(String name, Object... args) {
        return throwIfError(() -> {
            Method method = args == null ? descriptor.getDeclaredMethod(name) :
                    descriptor.getDeclaredMethod(name, toClassArray(args));
            return method.invoke(null, args);
        }, "invoke failed.");
    }

    /**
     * Returns the class loader for the class.  Some implementations may use
     * null to represent the bootstrap class loader. This method will return
     * null in such implementations if this class was loaded by the bootstrap
     * class loader.
     *
     * <p>If this {@code Class} object
     * represents a primitive type or void, null is returned.
     *
     * @return the class loader that loaded the class or interface
     * represented by this {@code Class} object.
     * @throws SecurityException if a security manager is present, and the caller's class loader
     *                           is not {@code null} and is not the same as or an ancestor of the
     *                           class loader for the class whose class loader is requested,
     *                           and the caller does not have the
     *                           {@link RuntimePermission}{@code ("getClassLoader")}
     * @see ClassLoader
     * @see SecurityManager#checkPermission
     * @see RuntimePermission
     */
    public ClassLoader getClassLoader() {
        return descriptor.getClassLoader();
    }

}
