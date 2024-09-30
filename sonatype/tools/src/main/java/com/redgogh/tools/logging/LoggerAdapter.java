package com.redgogh.tools.logging;

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

/* Creates on 2019/11/05. */

/**
 * 日志是匹配器
 *
 * @author RedGogh   
 */
public interface LoggerAdapter {

    /**
     * 根据传入的 {@code name} 来指定日志别名，如果是根据类型传入那么打印名称
     * 则会是类的全路径。
     *
     * @param name
     *        日志别名
     *
     * @return 日志记录器
     */
    Logger getLogger(String name);

    /**
     * 根据传入的 {@code aClass} 来指定日志对象路径，打印内容为 {@code aClass} 类
     * 对象所在包的全路径名称。
     *
     * @param aClass
     *        日志对象
     *
     * @return 日志记录器
     */
    Logger getLogger(Class<?> aClass);

}

