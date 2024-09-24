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

import com.redgogh.tools.collection.Lists;
import com.redgogh.tools.generators.RandomGenerator;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author RedGogh
 */
@SuppressWarnings("ALL")
public class ThreadExecutorExample {

    @Test
    public void newExecutorFixedPoolExample() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(128);

        List<Integer> numbers = Lists.newSynchronizedList();

        for (int i = 0; i < (1024 * 1024); i++) {
            executor.execute(() -> {
                numbers.add(RandomGenerator.nextInt(6) + RandomGenerator.nextInt(6));
            });
        }

        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);

        Integer sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println("beg="+ Lists.beg(numbers) + ", sum=" + sum);
    }

}
