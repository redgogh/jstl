package com.redgogh.libraries.springframework.boot.web.annotation;

/* ************************************************************************
 *
 * Copyright (C) 2020 RedGogh All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ************************************************************************/

/* Creates on 2022/11/6. */

import java.lang.annotation.*;

/**
 * 标记方法为开放 API 接口
 *
 * <p>`OpenAPI` 是一个自定义注解，用于标记方法为开放 API 接口。此注解可用于
 * 描述和文档化需要公开访问的方法。
 *
 * <p>该注解可与运行时反射机制结合使用，帮助框架或工具识别需要开放的 API 方法。
 *
 * <p>注解使用了以下元注解：
 * <ul>
 *     <li>{@link Target}: 指定注解的应用目标为方法。</li>
 *     <li>{@link Retention}: 指定注解的保留策略为运行时，这样可以通过反射访问注解信息。</li>
 *     <li>{@link Documented}: 指示使用该注解的元素应在 Javadoc 或类似工具中文档化。</li>
 * </ul>
 *
 * @author RedGogh
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OpenAPI {
}
