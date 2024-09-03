package com.redgogh.examples;

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

import com.redgogh.vortextools.time.DateFormatter;
import com.redgogh.vortextools.time.Date;
import org.junit.Test;

import static com.redgogh.vortextools.AnyObjects.atobool;
import static com.redgogh.vortextools.AnyObjects.printf;

@SuppressWarnings("ALL")
public class DateExample {

    private Date x = new Date("yyyy-MM-dd HH:mm:ss", "2019-10-10 01:00:02");
    private Date y = new Date("yyyy-MM-dd HH:mm:ss", "2019-10-10 01:00:03");

    /**
     * 使用表达式创建日期
     */
    @Test
    public void patternToCreateUDateExample() {
        Date date = new Date("yyyy-MM-dd HH:mm:ss", "2019-10-08 11:45:14");
        printf("pattern of yyyy-MM-dd HH:mm:ss to create Date: %s\n", date);
        printf("year:                                          %s\n", date.getYear());
        printf("month:                                         %s\n", date.getMonth());
        printf("day:                                           %s\n", date.getDay());
        printf("week:                                          %s\n", date.getWeekOfMonth());
        printf("hour:                                          %s\n", date.getHours());
        printf("minutes:                                       %s\n", date.getMinutes());
        printf("seconds:                                       %s\n", date.getSeconds());
    }

    /**
     * 比较一个日期是否小于另一个日期
     */
    @Test
    public void dateCompareExample() {
        printf("Date x  < y compare result is: %s\n", x.lt(y));
        printf("Date x  > y compare result is: %s\n", x.gt(y));
        printf("Date x == y compare result is: %s\n", x.eq(y));
        printf("Date x <= y compare result is: %s\n", x.lteq(y));
        printf("Date x >= y compare result is: %s\n", x.gteq(y));
    }

    /**
     * 计算时间差
     */
    @Test
    public void dateDiffInExample() {
        Date a = new Date("2018-01-01 00:00:02");
        Date b = new Date("2018-01-01 00:00:05");
        printf("date diff in days result is: %s\n", a.diffInDays(b));
        printf("date diff in hours result is: %s\n", a.diffInHours(b));
        printf("date diff in seconds result is: %s\n", a.diffInSeconds(b));
    }

    /**
     * 计算日期是否在某个区间内
     */
    @Test
    public void betweenExample() {
        Date x = new Date("2018-01-01 00:00:05");
        Date y = new Date("2019-01-01 00:00:05");
        Date a = new Date("2018-12-31 00:00:05");
        System.out.println(a.between(x, y));
    }

}
