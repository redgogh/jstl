package com.redgogh.libext.time;

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

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Date 日期对象，使用的时候注意不要用到 {@link java.util.Date}
 * 日期类了哦 ~
 * <p>
 * 在多线程环境下需要注意，如果需要使用和 {@link Date#setTime}
 * 函数有关的方法，或者说需要多个线程修改内部的 time 变量的情况下
 * 会导致线程不安全。所以请尽量避免在多线程环境下多个线程同时对一个
 * 日期对象进行读写操作。如果需要请搭配 `ThreadLocal` 等线程安全
 * 工具使用。
 * <p>
 * 因为 jdk 的日期类最初设计问题，所以本质上并不是 {@code Date} 对象线程
 * 不安全，而是和日期操作有关的 {@code Calendar} 类，它是全局共享的，然后
 * Date 又使用了 Calendar 做日期的计算操作。所以 Calendar 类是线程不安全的
 * 然后 Date 类有依赖 Calendar 提供的函数，这也就间接导致了 Date 也是不安全
 * 的。
 */
public class Date extends java.util.Date {

    /**
     * Allocates a <code>Date</code> object and initializes it so that
     * it represents the time at which it was allocated, measured to the
     * nearest millisecond.
     *
     * @see     java.lang.System#currentTimeMillis()
     */
    public Date() {
        super();
    }

    /**
     * Allocates a <code>Date</code> object and initializes it to
     * represent the specified number of milliseconds since the
     * standard base time known as "the epoch", namely January 1,
     * 1970, 00:00:00 GMT.
     *
     * @param   date   the milliseconds since January 1, 1970, 00:00:00 GMT.
     * @see     java.lang.System#currentTimeMillis()
     */
    public Date(long date) {
        super(date);
    }

    /**
     * 分配一个 <code>Date</code> 对象并使用 <code>Date</code> 对象
     * 初始化。
     *
     * @param date 日期对象
     */
    public Date(java.util.Date date) {
        this(date.getTime());
    }

    /**
     * Allocates a <code>Date</code> object and initializes it so that
     * it represents midnight, local time, at the beginning of the day
     * specified by the <code>year</code>, <code>month</code>, and
     * <code>date</code> arguments.
     *
     * @param   year    the year minus 1900.
     * @param   month   the month between 0-11.
     * @param   date    the day of the month between 1-31.
     * @see     java.util.Calendar
     * @deprecated As of JDK version 1.1,
     * replaced by <code>Calendar.set(year + 1900, month, date)</code>
     * or <code>GregorianCalendar(year + 1900, month, date)</code>.
     */
    @Deprecated
    public Date(int year, int month, int date) {
        super(year, month, date);
    }

    /**
     * Allocates a <code>Date</code> object and initializes it so that
     * it represents the instant at the start of the minute specified by
     * the <code>year</code>, <code>month</code>, <code>date</code>,
     * <code>hrs</code>, and <code>min</code> arguments, in the local
     * time zone.
     *
     * @param   year    the year minus 1900.
     * @param   month   the month between 0-11.
     * @param   date    the day of the month between 1-31.
     * @param   hrs     the hours between 0-23.
     * @param   min     the minutes between 0-59.
     * @see     java.util.Calendar
     * @deprecated As of JDK version 1.1,
     * replaced by <code>Calendar.set(year + 1900, month, date,
     * hrs, min)</code> or <code>GregorianCalendar(year + 1900,
     * month, date, hrs, min)</code>.
     */
    @Deprecated
    public Date(int year, int month, int date, int hrs, int min) {
        super(year, month, date, hrs, min);
    }

    /**
     * Allocates a <code>Date</code> object and initializes it so that
     * it represents the instant at the start of the second specified
     * by the <code>year</code>, <code>month</code>, <code>date</code>,
     * <code>hrs</code>, <code>min</code>, and <code>sec</code> arguments,
     * in the local time zone.
     *
     * @param   year    the year minus 1900.
     * @param   month   the month between 0-11.
     * @param   date    the day of the month between 1-31.
     * @param   hrs     the hours between 0-23.
     * @param   min     the minutes between 0-59.
     * @param   sec     the seconds between 0-59.
     * @see     java.util.Calendar
     * @deprecated As of JDK version 1.1,
     * replaced by <code>Calendar.set(year + 1900, month, date,
     * hrs, min, sec)</code> or <code>GregorianCalendar(year + 1900,
     * month, date, hrs, min, sec)</code>.
     */
    @Deprecated
    public Date(int year, int month, int date, int hrs, int min, int sec) {
        super(year, month, date, hrs, min, sec);
    }

    /**
     * 使用指定的日期时间字符串初始化一个 `Date` 对象。
     * <p>
     * 该构造函数将日期时间字符串解析为 `Date` 对象。默认的日期时间格式为
     * "yyyy-MM-dd HH:mm:ss"，即年-月-日 时:分:秒。
     *
     * @param value 日期时间字符串，必须符合 "yyyy-MM-dd HH:mm:ss" 格式。
     *
     * @throws IllegalArgumentException 如果传入的字符串不符合预期的日期时间格式或无法解析。
     */
    public Date(String value) {
        this("yyyy-MM-dd HH:mm:ss", value);
    }

    /**
     * 使用字符串来创建 {@code Date} 对象示例，可以手动指定日期正则
     * 表达是，如：{@code yyyy-MM-dd HH:mm:ss} 之类的。
     *
     * @param pattern 表达式
     * @param value   日期值
     */
    public Date(String pattern, String value) {
        this(DateFormatter.parse(pattern, value));
    }

    /**
     * 当前 {@code Date} 日期实例与另一个 {@code Date} 日期
     * 实例做比较，比较当前日期是否小于参数 {@code date} 日期。
     * 如果小于则返回 `true`。
     *
     * @param date 被比较的日期对象实例
     * @return 如果当前日期小于 {@code date} 则返回 `true`
     */
    public boolean lt(Date date) {
        return this.getTime() < date.getTime();
    }

    /**
     * 当前 {@code Date} 日期实例与另一个 {@code Date} 日期
     * 实例做比较，比较当前日期是否大于参数 {@code date} 日期。
     * 如果大于则返回 `true`。
     *
     * @param date 被比较的日期对象实例
     * @return 如果当前日期大于 {@code date} 则返回 `true`
     */
    public boolean gt(Date date) {
        return this.getTime() > date.getTime();
    }

    /**
     * 当前 {@code Date} 日期实例与另一个 {@code Date} 日期
     * 实例做比较，比较当前日期是否等于参数 {@code date} 日期。
     * 如果等于则返回 `true`。
     *
     * @param date 被比较的日期对象实例
     * @return 如果当前日期等于 {@code date} 则返回 `true`
     */
    public boolean eq(Date date) {
        return this.getTime() == date.getTime();
    }

    /**
     * 当前 {@code Date} 日期实例与另一个 {@code Date} 日期
     * 实例做比较，比较当前日期是否小于等于参数 {@code date} 日期。
     * 如果小于等于则返回 `true`。
     *
     * @param date 被比较的日期对象实例
     * @return 如果当前日期小于等于 {@code date} 则返回 `true`
     */
    public boolean lteq(Date date) {
        return lt(date) || eq(date);
    }

    /**
     * 当前 {@code Date} 日期实例与另一个 {@code Date} 日期
     * 实例做比较，比较当前日期是否大于等于参数 {@code date} 日期。
     * 如果大于等于则返回 `true`。
     *
     * @param date 被比较的日期对象实例
     * @return 如果当前日期大于等于 {@code date} 则返回 `true`
     */
    public boolean gteq(Date date) {
        return gt(date) || eq(date);
    }

    /**
     * 判断当前对象是否在指定的开始日期和结束日期之间（包含边界）。
     * <p>
     * 该方法检查当前对象的日期是否大于等于开始日期，并且小于等于结束日期。
     *
     * @param begin 开始日期，作为比较的下限。
     * @param end 结束日期，作为比较的上限。
     * @return 如果当前对象在指定的开始和结束日期之间（包含边界），则返回 true；否则返回 false。
     */
    public boolean between(Date begin, Date end) {
        return begin.lteq(this) && end.gteq(this);
    }

    /**
     * #brief：将指定日期对象加上 (value * op) 
     * <p>
     * 将指定日期对象加上 (value * op)。假设需要让一个日期对象加一天，可以
     * 这样使用：{@code udate.add(TimeUintOperator.DAY, 1)}。
     *
     * @param op
     *        时间单位操作符
     *
     * @param value
     *        添加的单位数
     *
     * @return 当前 {@code Date} 对象引用
     */
    public Date add(TimeUnitOperator op, int value) {
        setTime(op.add(this, value).getTime());
        return this;
    }

    /**
     * #brief：将指定日期对象减去 (value * op) 
     *
     * 将指定日期对象减去 (value * op)。假设需要让一个日期对象减去一天，可以
     * 这样使用：{@code udate.minus(TimeUintOperator.DAY, 1)}。
     *
     * @param op
     *        时间单位操作符
     *
     * @param value
     *        添加的单位数
     *
     * @return 当前 {@code Date} 对象引用
     */
    public Date minus(TimeUnitOperator op, int value) {
        setTime(op.minus(this, value).getTime());
        return this;
    }

    /**
     * 获取当前对象的年份。
     * 
     * 该方法使用 `Calendar` 类获取当前对象（假设为 `Date` 或类似对象）的年份部分。
     * 方法内部会创建一个 `Calendar` 实例，并将当前对象的时间设置到该实例中，
     * 然后从 `Calendar` 中提取出年份并返回。
     *
     * @return 当前对象的年份，类型为 int。
     */
    @SuppressWarnings("deprecation")
    public int getYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前对象的月份。
     * 
     * 该方法使用 `Calendar` 类获取当前对象（假设为 `Date` 或类似对象）的月份部分。
     * 方法内部创建一个 `Calendar` 实例，将当前对象的时间设置到该实例中，
     * 然后从 `Calendar` 中提取出月份并返回。
     *
     * @return 当前对象的月份，类型为 int。注意：月份是从 0 开始的（1 月为 0，2 月为 1，
     *         依此类推）所以返回结果需要 + 1。
     */
    @SuppressWarnings({"deprecation", "MagicConstant"})
    public int getMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前对象的日期（即日）。
     * <p>
     * 该方法使用 `Calendar` 类获取当前对象（假设为 `Date` 或类似对象）的日期部分。
     * 方法内部创建一个 `Calendar` 实例，将当前对象的时间设置到该实例中，
     * 然后从 `Calendar` 中提取出日期并返回。
     *
     * @return 当前对象的日期，类型为 int。
     */
    @SuppressWarnings("deprecation")
    public int getDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前对象在一年中的第几周。
     * <p>
     * 该方法使用 `Calendar` 类获取当前对象（假设为 `Date` 或类似对象）在一年中的周数。
     * 方法内部创建一个 `Calendar` 实例，将当前对象的时间设置到该实例中，
     * 然后从 `Calendar` 中提取出年份中的周数并返回。
     *
     * @return 当前对象在一年中的第几周，类型为 int。
     */
    public int getWeekOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取当前对象在一个月中的第几周。
     * <p>
     * 该方法使用 `Calendar` 类获取当前对象（假设为 `Date` 或类似对象）在一个月中的周数。
     * 方法内部创建一个 `Calendar` 实例，将当前对象的时间设置到该实例中，
     * 然后从 `Calendar` 中提取出一个月中的周数并返回。
     *
     * @return 当前对象在一个月中的第几周，类型为 int。
     */
    public int getWeekOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this);
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获取当前对象的小时数（12 小时制）。
     * <p>
     * 该方法使用 `Calendar` 类获取当前对象（假设为 `Date` 或类似对象）的小时部分，
     * 使用的是 12 小时制。
     * 方法内部创建一个 `Calendar` 实例，将当前对象的时间设置到该实例中，
     * 然后从 `Calendar` 中提取出小时数并返回。
     *
     * @return 当前对象的小时数（12 小时制），类型为 int。
     */
    @SuppressWarnings("deprecation")
    public int getHours() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this);
        return calendar.get(Calendar.HOUR);
    }

    /**
     * 获取当前对象的分钟数。
     * <p>
     * 该方法使用 `Calendar` 类获取当前对象（假设为 `Date` 或类似对象）的分钟部分。
     * 方法内部创建一个 `Calendar` 实例，将当前对象的时间设置到该实例中，
     * 然后从 `Calendar` 中提取出分钟数并返回。
     *
     * @return 当前对象的分钟数，类型为 int。
     */
    @SuppressWarnings("deprecation")
    public int getMinutes() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 计算当前对象与指定日期之间的天数差。
     * <p>
     * 该方法通过获取当前对象和指定日期的时间戳（以毫秒为单位），
     * 计算它们之间的绝对差值，并将其转换为天数返回。
     *
     * @param date 要比较的日期。
     * @return 当前对象与指定日期之间的天数差，类型为 int。
     */
    public int diffInDays(Date date) {
        long a = this.getTime();
        long b = date.getTime();
        return (int) TimeUnit.MILLISECONDS.toDays(Math.abs(a - b));
    }

    /**
     * 计算当前对象与指定日期之间的小时差。
     * <p>
     * 该方法通过获取当前对象和指定日期的时间戳（以毫秒为单位），
     * 计算它们之间的绝对差值，并将其转换为小时数返回。
     *
     * @param date 要比较的日期。
     * @return 当前对象与指定日期之间的小时差，类型为 int。
     */
    public int diffInHours(Date date) {
        long a = this.getTime();
        long b = date.getTime();
        return (int) TimeUnit.MILLISECONDS.toHours(Math.abs(a - b));
    }

    /**
     * 计算当前对象与指定日期之间的分钟差。
     * <p>
     * 该方法通过获取当前对象和指定日期的时间戳（以毫秒为单位），
     * 计算它们之间的绝对差值，并将其转换为分钟数返回。
     *
     * @param date 要比较的日期。
     * @return 当前对象与指定日期之间的分钟差，类型为 int。
     */
    public int diffInMinutes(Date date) {
        long a = this.getTime();
        long b = date.getTime();
        return (int) TimeUnit.MILLISECONDS.toMinutes(Math.abs(a - b));
    }

    /**
     * 计算当前对象与指定日期之间的秒数差。
     * <p>
     * 该方法通过获取当前对象和指定日期的时间戳（以毫秒为单位），
     * 计算它们之间的绝对差值，并将其转换为秒数返回。
     *
     * @param date 要比较的日期。
     * @return 当前对象与指定日期之间的秒数差，类型为 int。
     */
    public int diffInSeconds(Date date) {
        long a = this.getTime();
        long b = date.getTime();
        return (int) TimeUnit.MILLISECONDS.toSeconds(Math.abs(a - b));
    }

    /**
     * 获取当前对象的秒数。
     * <p>
     * 该方法使用 `Calendar` 类获取当前对象（假设为 `Date` 或类似对象）的秒部分。
     * 方法内部创建一个 `Calendar` 实例，将当前对象的时间设置到该实例中，
     * 然后从 `Calendar` 中提取出秒数并返回。
     *
     * @return 当前对象的秒数，类型为 int。
     */
    @SuppressWarnings("deprecation")
    public int getSeconds() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 当前 <code>Date</code> 对象格式化转字符串，通过 {@link DateFormatter#fmt(Date)}
     * 默认的格式化函数进行格式化。规则为：yyyy-MM-dd HH:mm:ss
     *
     * @return 格式化后的日期字符串（yyyy-MM-dd HH:mm:ss）
     */
    @Override
    public String toString() {
        return DateFormatter.fmt(this);
    }

    /**
     * 自定义格式化，将当前 <code>Date</code> 对象格式化转字符串，格式化规则可以
     * 自定义。
     *
     * @return 格式化后的日期字符串
     */
    public String toString(String pattern) {
        return DateFormatter.fmt(pattern, this);
    }

}
