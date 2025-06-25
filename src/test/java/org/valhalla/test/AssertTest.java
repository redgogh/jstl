package org.valhalla.test;

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

import org.valhalla.utils.Assert;
import org.valhalla.utils.Optional;
import org.junit.Test;
import org.valhalla.exception.AssertException;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class AssertTest {

    @Test
    public void ifEmptyTest() {
        List<String> list = new ArrayList<>();
        System.out.printf("-Test: Assert.ifEmpty() object `list` is empty: %s\n", Optional.ifError(() -> Assert.notEmpty(list), "SUCCESS", "ERROR"));
        list.add("Hello");
        System.out.printf("-Test: Assert.ifEmpty() object `list` not empty: %s\n", Optional.ifError(() -> Assert.notEmpty(list), "SUCCESS", "ERROR"));
    }

    @Test
    public void throwNullTest() throws Exception {
        throw new RuntimeException("null");
    }

    public static void main(String[] args) {
        throw new AssertException("null");
    }

}
