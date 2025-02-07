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

import org.junit.Test;
import com.redgogh.commons.lang3.stream.Streams;
import com.redgogh.commons.lang3.collection.Lists;
import com.redgogh.commons.lang3.utils.RandomGenerator;

import java.util.List;

import static com.redgogh.commons.lang3.system.OSEnvironment.time;

@SuppressWarnings("ALL")
public class StreamsTest {

    @Test
    public void parallelTest() {
        List<Integer> objects = Lists.of();
        for (int i = 0; i < 100000000; i++)
            objects.add(RandomGenerator.nextInt());

        long start = time();
        System.out.println(Streams.count(objects, o -> o > 1024 && o < Integer.MAX_VALUE));
        long end = time();
        System.out.printf("非并行计算耗时：%sms\n", (end - start));
    }

}
