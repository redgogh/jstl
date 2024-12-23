package org.redgogh.devtools.lang.test;

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

import org.redgogh.devtools.lang.generators.ChanceMaker;
import org.junit.Test;

import static org.redgogh.devtools.lang.io.IOUtils.stdout;

@SuppressWarnings("ALL")
public class ChanceMakerTest {

    /**
     * 模拟 1% 中奖概率
     */
    @Test
    public void onePercentChanceTest() {
        int loopCount = 100;
        int luckCount = 0;
        for (int i = 0; i < loopCount; i++) {
            if (ChanceMaker.isLuck(1.0f))
                luckCount++;
        }
        stdout.printf("循环次数：%s，中奖次数：%s\n", loopCount, luckCount);
    }

}
