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

import com.redgogh.tools.generators.ChanceMaker;
import org.junit.Test;

import static com.redgogh.tools.io.IOUtils.stdout;

@SuppressWarnings("ALL")
public class ChanceMakerExample {

    /**
     * 模拟 1% 中奖概率
     */
    @Test
    public void onePercentChanceExample() {
        int loopCount = 100;
        int luckCount = 0;
        for (int i = 0; i < loopCount; i++) {
            if (ChanceMaker.isLuck(1.0f))
                luckCount++;
        }
        stdout.printf("循环次数：%s，中奖次数：%s\n", loopCount, luckCount);
    }

}
