package com.redgogh.testing;

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

/* Create on 2023/8/10 */

import com.redgogh.libraries.springframework.boot.web.SpringApplicationBootstrap;
import com.redgogh.libraries.springframework.boot.web.annotation.EnableValidExcept;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author RedGogh
 */
@EnableValidExcept
@SpringBootApplication
public class TestingBootstrap {
    public static void main(String[] args) {
        SpringApplicationBootstrap.run(TestingBootstrap.class, args);
    }
}
