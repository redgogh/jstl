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

/**
 * Date 日期对象，使用的时候注意不要用到 {@link java.util.Date}
 * 日期类了哦 ~
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
     * Allocates a <code>Date</code> object and initializes it so that
     * it represents the date and time indicated by the string
     * <code>s</code>, which is interpreted as if by the
     * {@link java.util.Date#parse} method.
     *
     * @param   s   a string representation of the date.
     * @see     java.text.DateFormat
     * @see     java.util.Date#parse(java.lang.String)
     * @deprecated As of JDK version 1.1,
     * replaced by <code>DateFormat.parse(String s)</code>.
     */
    @Deprecated
    public Date(String s) {
        super(s);
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
     * #brief：将指定日期对象加上 (value * op) <p>
     *
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
     * #brief：将指定日期对象减去 (value * op) <p>
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
