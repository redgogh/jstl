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
        Date date = new Date("yyyy-MM-dd", "2019-10-10");
        printf("pattern of yyyy-MM-dd to create Date: %s\n", date);
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

}
