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

import org.junit.Test;

import static com.redgogh.tools.BasicConverter.atos;
import static com.redgogh.tools.StringUtils.strcut;

@SuppressWarnings("ALL")
public class StringUtilsExample {

    @Test
    public void atosExample() {
        String vanGogh = "Vincent Van Gogh";
        System.out.printf("atos: byte array to string: %s\n", atos(vanGogh.getBytes()));
        System.out.printf("atos: byte array [7 - len] to string: %s\n", atos(vanGogh.getBytes(), 7, 0));
    }

    @Test
    public void strcutExmaple() {
        String author = "Red Gogh";
        System.out.println(strcut(author, 0, -1));
    }

}
