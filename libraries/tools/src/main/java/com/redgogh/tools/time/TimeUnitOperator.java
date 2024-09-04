package com.redgogh.tools.time;

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

import com.redgogh.tools.Upgrade;
import org.joda.time.DateTime;

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
    @Upgrade(features = "OpenJDK17/EnhancedSwitchMigration")
    @SuppressWarnings("EnhancedSwitchMigration")
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
        ;
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
    @Upgrade(features = "OpenJDK17/EnhancedSwitchMigration")
    @SuppressWarnings("EnhancedSwitchMigration")
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
        ;
        return new Date(retval.toDate());
    }

}
