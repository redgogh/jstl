package com.redgogh.vortextools.time;

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

/* Creates on 2022/3/30. */

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;

/**
 * @author RedGogh   
 */
public class DateFormatter {

    /** 默认格式化风格 */
    private static final String defaultFormatPattern = "yyyy-MM-dd HH:mm:ss";

    /** 编译一个 Formatter 对象实例，根据 Pattern 编译。默认使用
     *  UnsafeFormatter 内置格式化对象。 */
    private static Formatter compileFormatter(String pattern) {
        return new LocalSimpleDateFormatter(pattern);
    }

    /** 日期格式化接口，为了方便适配多种日期格式化工具包抽象的
     *  接口层 */
    interface Formatter {
        /** 将日期格式化成字符串 */
        String format(UDate date);
        /** 将字符串解析成日期 */
        UDate parse(String text);
    }

    /**
     * 使用 SimpleDateFormatter 实现的 Formatter 接口。
     */
    static class LocalSimpleDateFormatter implements Formatter {
        /* 使用 ThreadLocal 包装 SimpleDateFormatter 避免多线程下出现线程
        *  安全问题。 */
        private final ThreadLocal<SimpleDateFormat> sdf =
                new ThreadLocal<>();

        LocalSimpleDateFormatter(String pattern) {
            this.sdf.set(new SimpleDateFormat(pattern));
        }

        @Override
        public String format(UDate date) {
            return sdf.get().format(date);
        }

        @Override
        @SneakyThrows
        public UDate parse(String text) {
            return new UDate(sdf.get().parse(text));
        }
    }

    /** 返回当前时间 */
    public static UDate now() {
        return new UDate();
    }

    /** 当前时间戳 */
    public static long currentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * #brief：将当前时间格式化成 {@link #defaultFormatPattern} 风格<p>
     *
     * 将当前时间格式化成 {@link #defaultFormatPattern} 风格，也就是常用
     * 的：yyyy-MM-dd HH:mm:ss。该函数格式化当前日期。
     *
     * @return 格式化后的字符串
     */
    public static String fmt() {
        return fmt(now());
    }

    /**
     * #brief：将指定日期对象格式化为 {@link #defaultFormatPattern} 风格<p>
     *
     * 将指定日期对象格式化为 {@link #defaultFormatPattern} 风格，传入一个
     * 日期对象实例，然后将该日期对象格式化为 yyyy-MM-dd HH:mm:ss 字符串。
     *
     * @param date
     *        日期对象实例
     *
     * @return 格式化后的字符串
     */
    public static String fmt(UDate date) {
        return fmt(defaultFormatPattern, date);
    }

    /**
     * #brief：将当前日期格式化为指定风格<p>
     *
     * 将当前日期格式化为指定风格，传入日期格式化表达式，类似：yyyy-MM-dd 格式化出来
     * 就是：2018-06-08 这样的日期风格。
     *
     * @param pattern
     *        自定义格式化规则
     *
     * @return 格式化后的字符串
     */
    public static String fmt(String pattern) {
        return fmt(pattern, now());
    }

    /**
     * #brief：根据格式化规则将日期对象格式化为字符串<p>
     *
     * 根据格式化规则将日期对象格式化为字符串，通过 {@link SimpleDateFormat} 对象
     * 实现日期格式化。使用 `new` 关键字创建 {@link SimpleDateFormat} 对象实例满
     * 足线程安全要求。
     *
     * @param pattern
     *        格式化规则，如：“yyyy-MM-dd”
     *
     * @param date
     *        格式化日期对象，如：“DateFormatter.now()”
     *
     * @return 格式化后的字符串
     */
    public static String fmt(String pattern, UDate date) {
        return compileFormatter(pattern).format(date);
    }

    public static UDate parse(String text) {
        return parse(defaultFormatPattern, text);
    }

    /**
     * #brief：根据解析规则将字符串对象转换为日期对象实例。<p>
     *
     * 根据解析规则将字符串对象转换为日期对象实例，通过 {@link SimpleDateFormat} 对象
     * 实现日期格字符串解析。使用 `new` 关键字创建 {@link SimpleDateFormat} 对象实例满
     * 足线程安全要求。
     *
     * @param pattern
     *        格式化规则，如：“yyyy-MM-dd”
     *
     * @param text
     *        解析日期对象，如：“2018-12-18”
     *
     * @return 解析后的字符串
     */
    public static UDate parse(String pattern, String text) {
        return compileFormatter(pattern).parse(text);
    }

}
