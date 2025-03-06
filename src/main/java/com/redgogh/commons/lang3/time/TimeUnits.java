package com.redgogh.commons.lang3.time;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2019-2024 RedGogh All rights reserved.                          *|
|*                                                                                  *|
|*    Licensed under the Apache License, Version 2.0 (the "License");               *|
|*    you may not use this file except in compliance with the License.              *|
|*    You may obtain a copy of the License at                                       *|
|*                                                                                  *|
|*        http://www.apache.org/licenses/LICENSE-2.0                                *|
|*                                                                                  *|
|*    Unless required by applicable law or agreed to in writing, software           *|
|*    distributed under the License is distributed on an "AS IS" BASIS,             *|
|*    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.      *|
|*    See the License for the specific language governing permissions and           *|
|*    limitations under the License.                                                *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

/* Creates on 2022/3/30. */

import com.redgogh.commons.lang3.exception.UnsupportedOperationException;
import com.redgogh.commons.lang3.utils.Assert;
import com.redgogh.commons.lang3.utils.Enumerate;
import org.joda.time.DateTime;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 时间单位操作符
 *
 * @author Red Gogh   
 */
public enum TimeUnits {

    /** 毫秒 */
    MILLISECONDS,
    /** 秒 */
    SECONDS,
    /** 分 */
    MINUTES,
    /** 小时 */
    HOURS,
    /** 天 */
    DAYS,
    /** 周 */
    WEEKS,
    /** 月 */
    MONTHS,
    /** 年 */
    YEARS,
    ;

    private final TimeUnit timeUnit;

    TimeUnits() {
        timeUnit = Enumerate.find(TimeUnit.class, name());
    }

    public Date plus(int value) {
        return plus(DateFormatter.now(), value);
    }

    /**
     * #brief：将指定日期对象加上 unit + 当前枚举<p>
     *
     * 将指定日期对象加上 unit + 当前枚举。假设需要让一个日期对象加一天，可以
     * 这样使用：{@code TimeUnits.DAYS.plus(date, 1L)}。
     *
     * @param date
     *        日期对象实例
     *
     * @param unit
     *        添加的单位数
     *
     * @return 加上对应 TimeUnits + unit 后的日期
     */
    public Date plus(Date date, int unit) {
        DateTime calc = new DateTime(date);
        DateTime retval = null;
        switch (this) {
            case MILLISECONDS:
                retval = calc.plusMillis(unit);
                break;
            case SECONDS:
                retval = calc.plusSeconds(unit);
                break;
            case MINUTES:
                retval = calc.plusMinutes(unit);
                break;
            case HOURS:
                retval = calc.plusHours(unit);
                break;
            case DAYS:
                retval = calc.plusDays(unit);
                break;
            case WEEKS:
                retval = calc.plusWeeks(unit);
                break;
            case MONTHS:
                retval = calc.plusMonths(unit);
                break;
            case YEARS:
                retval = calc.plusYears(unit);
                break;
        }

        return retval.toDate();
    }

    public Date minus(int unit) {
        return minus(DateFormatter.now(), unit);
    }

    /**
     * #brief：将指定日期对象减去 unit - 当前枚举<p>
     *
     * 将指定日期对象减去 unit - 当前枚举。假设需要让一个日期对象加一天，可以
     * 这样使用：{@code TimeUnits.DAYS.plus(date, 1L)}。
     *
     * @param date
     *        日期对象实例
     *
     * @param unit
     *        添加的单位数
     *
     * @return 减去对应 TimeUnits - unit 后的日期
     */
    public Date minus(Date date, int unit) {
        DateTime calc = new DateTime(date);
        DateTime retval = null;
        switch (this) {
            case MILLISECONDS:
                retval = calc.minusMillis(unit);
                break;
            case SECONDS:
                retval = calc.minusSeconds(unit);
                break;
            case MINUTES:
                retval = calc.minusMinutes(unit);
                break;
            case HOURS:
                retval = calc.minusHours(unit);
                break;
            case DAYS:
                retval = calc.minusDays(unit);
                break;
            case WEEKS:
                retval = calc.minusWeeks(unit);
                break;
            case MONTHS:
                retval = calc.minusMonths(unit);
                break;
            case YEARS:
                retval = calc.minusYears(unit);
                break;
        }

        return retval.toDate();
    }

    /**
     * #brief: 计算两个日期之间的时间差
     *
     * <p>根据当前时间单位（如秒、分钟、小时等），计算两个日期之间的时间差。
     * 支持的时间单位包括毫秒、秒、分钟、小时、天、周、月和年。
     * 日期会被转换为系统默认时区进行计算。
     *
     * @param a 起始日期
     * @param b 结束日期
     * @return 两个日期之间的时间差，单位为当前时间单位
     */
    public long between(Date a, Date b) {
        ChronoUnit chrono = null;
        switch (this) {
            case MILLISECONDS:
                chrono = ChronoUnit.MILLIS;
                break;
            case SECONDS:
                chrono = ChronoUnit.SECONDS;
                break;
            case MINUTES:
                chrono = ChronoUnit.MINUTES;
                break;
            case HOURS:
                chrono = ChronoUnit.HOURS;
                break;
            case DAYS:
                chrono = ChronoUnit.DAYS;
                break;
            case WEEKS:
                chrono = ChronoUnit.WEEKS;
                break;
            case MONTHS:
                chrono = ChronoUnit.MONTHS;
                break;
            case YEARS:
                chrono = ChronoUnit.YEARS;
                break;
        }
        return (int) chrono.between(
                a.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                b.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    /**
     * #brief: 将给定时长转换为纳秒
     *
     * <p>该方法将指定的时长转换为纳秒。如果当前类的名称匹配 `TimeUnit` 枚举的某个常量，
     * 则使用相应的 `TimeUnit` 枚举的 `toNanos` 方法进行转换；否则抛出 `UnsupportedOperationException` 异常。
     *
     * @param duration 需要转换的时长
     * @return 转换后的纳秒值
     * @throws UnsupportedOperationException 如果当前名称无法匹配到 `TimeUnit` 枚举常量
     */
    public long toNanos(long duration) {
        return toTimeUnitCheckNull().toNanos(duration);
    }

    /**
     * #brief: 将给定时长转换为毫秒
     *
     * <p>该方法将指定的时长转换为毫秒。如果当前类的名称匹配 `TimeUnit` 枚举的某个常量，
     * 则使用相应的 `TimeUnit` 枚举的 `toMillis` 方法进行转换；否则抛出 `UnsupportedOperationException` 异常。
     *
     * @param duration 需要转换的时长
     * @return 转换后的毫秒值
     * @throws UnsupportedOperationException 如果当前名称无法匹配到 `TimeUnit` 枚举常量
     */
    public long toMillis(long duration) {
        return toTimeUnitCheckNull().toMillis(duration);
    }

    /**
     * #brief: 将给定时长转换为秒
     *
     * <p>该方法将指定的时长转换为秒。如果当前类的名称匹配 `TimeUnit` 枚举的某个常量，
     * 则使用相应的 `TimeUnit` 枚举的 `toSeconds` 方法进行转换；否则抛出 `UnsupportedOperationException` 异常。
     *
     * @param duration 需要转换的时长
     * @return 转换后的秒值
     * @throws UnsupportedOperationException 如果当前名称无法匹配到 `TimeUnit` 枚举常量
     */
    public long toSeconds(long duration) {
        return toTimeUnitCheckNull().toSeconds(duration);
    }

    /**
     * #brief: 将给定时长转换为分钟
     *
     * <p>该方法将指定的时长转换为分钟。如果当前类的名称匹配 `TimeUnit` 枚举的某个常量，
     * 则使用相应的 `TimeUnit` 枚举的 `toMinutes` 方法进行转换；否则抛出 `UnsupportedOperationException` 异常。
     *
     * @param duration 需要转换的时长
     * @return 转换后的分钟值
     * @throws UnsupportedOperationException 如果当前名称无法匹配到 `TimeUnit` 枚举常量
     */
    public long toMinutes(long duration) {
        return toTimeUnitCheckNull().toMinutes(duration);
    }

    /**
     * #brief: 将给定时长转换为小时
     *
     * <p>该方法将指定的时长转换为小时。如果当前类的名称匹配 `TimeUnit` 枚举的某个常量，
     * 则使用相应的 `TimeUnit` 枚举的 `toHours` 方法进行转换；否则抛出 `UnsupportedOperationException` 异常。
     *
     * @param duration 需要转换的时长
     * @return 转换后的小时值
     * @throws UnsupportedOperationException 如果当前名称无法匹配到 `TimeUnit` 枚举常量
     */
    public long toHours(long duration) {
        return toTimeUnitCheckNull().toHours(duration);
    }

    /**
     * #brief: 将给定时长转换为天
     *
     * <p>该方法将指定的时长转换为天。如果当前类的名称匹配 `TimeUnit` 枚举的某个常量，
     * 则使用相应的 `TimeUnit` 枚举的 `toDays` 方法进行转换；否则抛出 `UnsupportedOperationException` 异常。
     *
     * @param duration 需要转换的时长
     * @return 转换后的天数值
     * @throws UnsupportedOperationException 如果当前名称无法匹配到 `TimeUnit` 枚举常量
     */
    public long toDays(long duration) {
        return toTimeUnitCheckNull().toDays(duration);
    }

    private TimeUnit toTimeUnitCheckNull() {
        Assert.notNull(timeUnit, "TimeUnit不支持【%s】", name());
        return timeUnit;
    }

    public TimeUnit toTimeUnit() {
        return timeUnit;
    }

}
