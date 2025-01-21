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

import org.junit.Test;
import org.redgogh.cleantools.base.BasicConverter;
import org.redgogh.cleantools.base.StringUtils;
import org.redgogh.cleantools.collect.Lists;
import org.redgogh.cleantools.generators.RandomGenerator;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.redgogh.cleantools.base.StringOperator.STRING_OPERATOR_TRIM;
import static org.redgogh.cleantools.base.StringUtils.strcut;

@SuppressWarnings("ALL")
public class StringUtilsTest {

    @Test
    public void operatorsTest() {
        String redGogh = " Red ";
        System.out.printf("Not uses operator: <%s>\n", strcut(redGogh, 0, 0));
        System.out.printf("Uses operator    : <%s>\n", strcut(redGogh, 0, 0, STRING_OPERATOR_TRIM));
    }

    @Test
    public void atosTest() {
        String vanGogh = "Vincent Van Gogh";
        System.out.printf("atos: byte array to string: %s\n", BasicConverter.atos(vanGogh.getBytes()));
        System.out.printf("atos: byte array [7 - len] to string: %s\n", BasicConverter.atos(vanGogh.getBytes(), 7, 0));
    }

    @Test
    public void strcutExmaple() {
        String author = "Red Gogh";
        System.out.println(strcut(author, 0, -1));
    }

    @Test
    public void globMatcherTest() {
        List<String> paths = Lists.of();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.print("\rsize: " + paths.size()), 0, 10, TimeUnit.MILLISECONDS);

        boolean isContinue = true;
        while (isContinue) {
            paths.add("/" + RandomGenerator.nextLetterCode(3, 8) + "/" + RandomGenerator.nextLetterCode(0, 8) + "." + RandomGenerator.nextLetterCode(3, 6));
            if (paths.size() > 10000000)
                isContinue = false;
        }
        scheduledExecutorService.shutdown();
        System.out.println();
        paths.add("/api/dwada.xdwadpojawopdjf");
        paths.forEach(V -> {
            if (StringUtils.strant(V, "/api/*.x*f"))
                System.out.println(V);
        });
    }

}
