package com.redgogh.examples;

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

import com.redgogh.tools.time.Date;
import org.junit.Test;

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
        System.out.printf("pattern of yyyy-MM-dd HH:mm:ss to create Date: %s\n", date);
        System.out.printf("year:                                          %s\n", date.getYear());
        System.out.printf("month:                                         %s\n", date.getMonth());
        System.out.printf("day:                                           %s\n", date.getDay());
        System.out.printf("week:                                          %s\n", date.getWeekOfMonth());
        System.out.printf("hour:                                          %s\n", date.getHours());
        System.out.printf("minutes:                                       %s\n", date.getMinutes());
        System.out.printf("seconds:                                       %s\n", date.getSeconds());
    }

    /**
     * 比较一个日期是否小于另一个日期
     */
    @Test
    public void dateCompareExample() {
        System.out.printf("Date x  < y compare result is: %s\n", x.lt(y));
        System.out.printf("Date x  > y compare result is: %s\n", x.gt(y));
        System.out.printf("Date x == y compare result is: %s\n", x.eq(y));
        System.out.printf("Date x <= y compare result is: %s\n", x.lteq(y));
        System.out.printf("Date x >= y compare result is: %s\n", x.gteq(y));
    }

    /**
     * 计算时间差
     */
    @Test
    public void dateDiffInExample() {
        Date a = new Date("2018-01-01 00:00:02");
        Date b = new Date("2018-01-01 00:00:05");
        System.out.printf("date diff in days result is: %s\n", a.diffInDays(b));
        System.out.printf("date diff in hours result is: %s\n", a.diffInHours(b));
        System.out.printf("date diff in seconds result is: %s\n", a.diffInSeconds(b));
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
