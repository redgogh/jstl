package org.redgogh.cleantools.test;

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

import org.redgogh.cleantools.time.TimeUnitOperator;
import org.junit.Test;

@SuppressWarnings("ALL")
public class TimeUnitOperatorTest {

    /**
     * TimeUnitOperator：时间加一天
     */
    @Test
    public void timeUnitAddOperateTest() {
        System.out.printf("TimeUnitOperator DAY add 1 example: %s\n", TimeUnitOperator.DAYS.add(1));
    }

    /**
     * TimeUnitOperator：时间减一天
     */
    @Test
    public void timeUnitMinusOperateTest() {
        System.out.printf("TimeUnitOperator DAY minus 1 example: %s\n", TimeUnitOperator.DAYS.minus(1));
    }

}
