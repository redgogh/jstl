package com.redgogh.libraries.springframework.boot.web;

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

/* Create on 2023/8/22 */

import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author RedGogh
 */
public class SpringConfigurableApplicationContext {

    private static ConfigurableApplicationContext _configurableApplicationContext;

    public static void configure(ConfigurableApplicationContext configurableApplicationContext) {
        _configurableApplicationContext = configurableApplicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) _configurableApplicationContext.getBean(name);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> beanType) {
        return (T) _configurableApplicationContext.getBean(beanType);
    }

}
