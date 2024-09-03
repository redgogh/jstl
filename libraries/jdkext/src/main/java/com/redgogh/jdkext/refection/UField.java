package com.redgogh.jdkext.refection;

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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static com.redgogh.jdkext.Assert.*;
import static com.redgogh.jdkext.Optional.optionalIfError;
import static com.redgogh.jdkext.StringUtils.strwfmt;

/**
 * 类属性`Field`对象封装
 *
 * @author RedGogh   
 */
@SuppressWarnings({
        "LombokGetterMayBeUsed"
})
public class UField {
    /**
     * 成员属性对象
     */
    private final Field property;
    /**
     * 属性修饰符
     */
    private final int modifiers;
    /**
     * 属性所在类
     */
    private final Class<?> inClass;
    /**
     * 属性路径
     */
    private final String path;
    /**
     * 成员名称
     */
    private final String name;

    public UField(String name, Class<?> inDescriptor) {
        this(findDescriptorProperty(name, inDescriptor));
    }

    /**
     * #brief：通过`Field`对象初始化`ObjectProperty`<p>
     *
     * 通过`Field`对象初始化`ObjectProperty`, 这个构造器会通过 {@code field} 参数
     * 获取属性的名称，类型等元数据信息。从而初始化`ObjectProperty`对象实例。
     *
     * @param field
     *        属性
     */
    public UField(Field field) {
        this.property = field;
        this.property.setAccessible(true);
        this.modifiers = property.getModifiers();
        this.inClass = field.getDeclaringClass();
        this.name = field.getName();
        this.path = strwfmt("%s#%s", inClass.getName(), name);
    }

    ///////////////////////////////////////////////////////////////////////////
    /// get
    ///////////////////////////////////////////////////////////////////////////

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    /**
     * 返回当前属性对象类型
     */
    public Class<?> getType() {
        return property.getType();
    }

    /**
     * 当前成员是否使用了`static`修饰
     */
    public boolean isStatic() {
        return (modifiers & Modifier.STATIC) > 0;
    }

    /**
     * 当前成员是否使用了`final`修饰
     */
    public boolean isFinal() {
        return (modifiers & Modifier.FINAL) > 0;
    }

    /** 属性数据读取接口 */
    interface PropertyReader<T> { T read(); }
    /** 属性数据写入接口 */
    interface PropertyWriter { void write(Object value); }

    /**
     * 可访问的`Property`对象实例，该实例默认`Field`对象的
     * `Accessible`为true；并且支持读写操作。
     */
    static class PropertyDescriptor implements PropertyReader<Object>, PropertyWriter {
        private final Field field; /* 可访问的 field */
        private final Object instance;
        /**
         * 创建 Property
         */
        public PropertyDescriptor(Field field, Object instance) {
            this.field = field;
            this.instance = instance;
        }
        /**
         * 获取当前属性中的值
         */
        public Object read() {
            return throwIfError(() -> field.get(instance));
        }
        /**
         * 设置当前属性中的值
         */
        public void write(Object value) {
            throwIfError(() -> field.set(instance, value));
        }
    }

    private PropertyDescriptor createPropertyDescriptor(Object obj) {
        return new PropertyDescriptor(property, obj);
    }

    /**
     * 将`BuiltinPublicProperty`对象映射为读取
     */
    private PropertyReader<Object> getPropertyDescriptorReader(Object obj) {
        return createPropertyDescriptor(obj);
    }

    /**
     * 将`BuiltinPublicProperty`对象映射为写入
     */
    private PropertyWriter getPropertyDescriptorWriter(Object obj) {
        return createPropertyDescriptor(obj);
    }

    /**
     * 查找 {@code descriptor} 参数中是否存在 {@code name} 属性。这个
     * 函数会优先从当前 {@code descriptor} 类对象中查找属性;<p>
     *
     * 如果当前类中找不到 {@code name} 属性则会从父类一直往上查找。直到找不到为止。如果
     * 存在 {@code name} 属性则返回该属性 Field 对象。不存在则抛出异常。
     */
    static Field findDescriptorProperty(String name, Class<?> descriptor) {
        Field property = optionalIfError(() -> descriptor.getDeclaredField(name),
                findDescriptorProperty0(name, descriptor));
        return throwIfNull(property, "属性`%s`在`%s`类中不存在", name, descriptor.getName());
    }

    /** 递归从 {@code descriptor} 的父类查找 {@code name} 属性 */
    static Field findDescriptorProperty0(String name, Class<?> descriptor) {
        Field rfield;
        Class<?> superclass = descriptor.getSuperclass();

        /* 如果没有父类直接跳出该方法 */
        if (superclass == null)
            return null;

        if ((rfield = optionalIfError(() -> superclass.getDeclaredField(name), null)) == null)
            rfield = findDescriptorProperty0(name, superclass);

        return rfield;
    }

    /**
     * #brief：根据属性名称写入对象属性数据<p>
     *
     * 根据属性名写入数据到指定属性中，通过反射获取属性`Field`并对这个属性
     * 对象进行反射赋值。不会经过`set`函数。
     *
     * @param instance
     *        对象实例
     */
    public Object read(Object instance) {
        return getPropertyDescriptorReader(instance).read();
    }

    /**
     * #brief：根据属性名称写入对象属性数据<p>
     *
     * 根据属性名写入数据到指定属性中，通过反射获取属性`Field`并对这个属性
     * 对象进行反射赋值。不会经过`set`函数。
     *
     * @param value
     *        写入的数据
     *
     * @param instance
     *        对象实例
     */
    public void write(Object instance, Object value) {
        getPropertyDescriptorWriter(instance).write(value);
    }

    /**
     * #brief：将`src`实例中的当前属性对象拷贝到`dest`中<p>
     *
     * 将`src`实例中的当前属性对象拷贝到`dest`中，首先必须确保`src`对象中
     * 和`dest`对象中都存在当前`Property`属性。否则拷贝失败。
     *
     * @param src
     *        源对象实例
     *
     * @param dest
     *        目标对象实例
     */
    public static void copy(Object src, Object dest, String name) {
        /* read value of src. */
        Object value = new UField(name, src.getClass()).read(src);
        /* write to dest. */
        UField destUField = new UField(name, dest.getClass());
        destUField.write(dest, value);
    }

    /**
     * #brief：将`src`实例中的当前属性对象拷贝到`dest`中（字段不存在则忽略）<p>
     *
     * 将`src`实例中的当前属性对象拷贝到`dest`中，如果字段不存在则忽略掉
     * 不抛出异常。
     *
     * @param src
     *        源对象实例
     *
     * @param dest
     *        目标对象实例
     */
    public static void copyIgnoreError(Object src, Object dest, String name) {
        ignore(() -> copy(src, dest, name));
    }

    /**
     * #brief：判断当前属性上是否存在 {@code annotation} 注解<p>
     *
     * 判断当前属性上是否存在 {@code annotation} 注解，如果存在传入的注解类则
     * 返回 {@code true}；否则不存在返回 {@code false}。
     *
     * @param annotation
     *        注解类
     *
     * @return 如果存在传入的注解类则返回 {@code true}；否则不存在
     *         则返回 {@code false}。
     */
    public boolean hasAnnotation(Class<? extends Annotation> annotation) {
        return getAnnotation(annotation) != null;
    }

    /**
     * #brief：获取属性上的注解信息<p>
     *
     * 获取属性上的注解信息，根据 {@code annotation} 参数获取当前属性上的注解。
     * 如果注解存在则返回注解对象，不存在则返回 {@code null}。
     *
     * @param annotation
     *        注解类
     *
     * @return 如果 {@code annotation} 被注解在当前属性上，那么就
     *         返回注解对象。如果不存在则返回 {@code null}。
     */
    public <T extends Annotation> T getAnnotation(Class<T> annotation) {
        T a = property.getDeclaredAnnotation(annotation);
        if (a == null)
            a = property.getAnnotation(annotation);
        return a;
    }

}
