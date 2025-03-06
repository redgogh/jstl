package com.redgogh.commons.test;

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

import com.redgogh.commons.lang3.time.Chrono;

import java.util.Date;

@SuppressWarnings("ALL")
public class ChronoTest {

    public static void main(String[] args) {
        Chrono chrono = new Chrono(new Date());

        System.out.printf("plus Weeks 1: %s\n", chrono.plusWeeks(1));

        System.out.printf("Day of week: %s\n", chrono.getDayOfWeek());
        System.out.printf("Day of month: %s\n", chrono.getDayOfMonth());
        System.out.printf("Day of year: %s\n", chrono.getDayOfYear());

        System.out.printf("Week of month: %s\n", chrono.getWeekOfMonth());
        System.out.printf("Week of year: %s\n", chrono.getWeekOfYear());

        System.out.printf("Year: %s\n", chrono.getYear());
        System.out.printf("Month: %s\n", chrono.getMonth());
        System.out.printf("Day: %s\n", chrono.getDayOfMonth());
        System.out.printf("Hour: %s\n", chrono.getHour());
        System.out.printf("Minute: %s\n", chrono.getMinute());
        System.out.printf("Second: %s\n", chrono.getSecond());
        System.out.printf("Milli: %s\n", chrono.getMilli());
        System.out.printf("Nano: %s\n", chrono.getNano());

        Chrono c1 = new Chrono(2023, 2, 1, 19, 3, 10);
        Chrono c2 = new Chrono(2025, 3, 2, 23, 5, 23);
        System.out.printf("Between Nanos: %s\n", c1.betweenNanos(c2));
        System.out.printf("Between Millis: %s\n", c1.betweenMillis(c2));
        System.out.printf("Between Seconds: %s\n", c1.betweenSeconds(c2));
        System.out.printf("Between Minutes: %s\n", c1.betweenMinutes(c2));
        System.out.printf("Between Hours: %s\n", c1.betweenHours(c2));
        System.out.printf("Between Days: %s\n", c1.betweenDays(c2));
        System.out.printf("Between Months: %s\n", c1.betweenMonths(c2));
        System.out.printf("Between Weeks: %s\n", c1.betweenWeeks(c2));
        System.out.printf("Between Year: %s\n", c1.betweenYears(c2));
    }

}
