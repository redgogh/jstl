package com.redgogh.tools.time;

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

import com.redgogh.tools.enums.Enumerates;
import com.redgogh.tools.exception.UnsupportedOperationException;
import org.joda.time.DateTime;

import java.util.concurrent.TimeUnit;

import static com.redgogh.tools.StringUtils.strwfmt;

/**
 * 时间单位操作符
 *
 * @author RedGogh   
 */
public enum TimeUnitOperator {

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

    public Date add(int value) {
        return add(DateFormatter.now(), value);
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
    public Date add(Date date, int unit) {
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

        return new Date(retval.toDate());
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

        return new Date(retval.toDate());
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
        TimeUnit timeUnit = Enumerates.find(TimeUnit.class, name());
        if (timeUnit != null)
            return timeUnit.toNanos(duration);
        throw new UnsupportedOperationException(toUnsupportedExceptMessage("toNanos"));
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
        TimeUnit timeUnit = Enumerates.find(TimeUnit.class, name());
        if (timeUnit != null)
            return timeUnit.toMillis(duration);
        throw new UnsupportedOperationException(toUnsupportedExceptMessage("toMillis"));
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
        TimeUnit timeUnit = Enumerates.find(TimeUnit.class, name());
        if (timeUnit != null)
            return timeUnit.toSeconds(duration);
        throw new UnsupportedOperationException(toUnsupportedExceptMessage("toSeconds"));
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
        TimeUnit timeUnit = Enumerates.find(TimeUnit.class, name());
        if (timeUnit != null)
            return timeUnit.toMinutes(duration);
        throw new UnsupportedOperationException(toUnsupportedExceptMessage("toMinutes"));
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
        TimeUnit timeUnit = Enumerates.find(TimeUnit.class, name());
        if (timeUnit != null)
            return timeUnit.toHours(duration);
        throw new UnsupportedOperationException(toUnsupportedExceptMessage("toHours"));
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
        TimeUnit timeUnit = Enumerates.find(TimeUnit.class, name());
        if (timeUnit != null)
            return timeUnit.toDays(duration);
        throw new UnsupportedOperationException(toUnsupportedExceptMessage("toDays"));
    }

    /** 构建异常信息 */
    private String toUnsupportedExceptMessage(String method) {
        return strwfmt("TimeUnitOperator#%s 类型暂时不支持 %s 函数转换", name(), method);
    }

}
