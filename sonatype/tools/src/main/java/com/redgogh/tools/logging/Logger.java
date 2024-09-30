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
 * 可以适配多种日志框架的日志接口对象。
 *
 * @author RedGogh   
 */
public interface Logger {

    /**
     * @return 当前日志对象是否是一个有效的日志对象实例，如果不是
     *         则返回 <code>false</code>
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    boolean isValid();

    /**
     * @return 是否开启了【Debug】级别的日志调试模式，如果开启了则会打印【Debug】日志。
     *         相对应的，这个函数也会返回 {@code true}。反之则表示【Debug】模式关闭不会
     *         打印【Debug】级别的日志。
     */
    boolean isDebugEnabled();

    /**
     * 打印 【info】 级别的日志信息到控制台，如果有使用日志框架，那么会
     * 根据日志框架的配置输出到日志文件中。
     *
     * @param message
     *        日志信息
     *
     * @param args
     *        格式化参数
     */
    void info(String message, Object... args);

    /**
     * 打印 【warn】 级别的日志信息到控制台，如果有使用日志框架，那么会
     * 根据日志框架的配置输出到日志文件中。
     *
     * @param message
     *        日志信息
     *
     * @param args
     *        格式化参数
     */
    void warn(String message, Object... args);

    /**
     * 打印 【debug】 级别的日志信息到控制台，如果有使用日志框架，那么会
     * 根据日志框架的配置输出到日志文件中。
     *
     * @param message
     *        日志信息
     *
     * @param args
     *        格式化参数
     */
    void debug(String message, Object... args);

    /**
     * 打印 【error】 级别的日志信息到控制台，如果有使用日志框架，那么会
     * 根据日志框架的配置输出到日志文件中。
     *
     * @param message
     *        日志信息
     *
     * @param args
     *        格式化参数
     */
    void error(String message, Object... args);

    /**
     * 打印 【error】 级别的日志信息到控制台，如果有使用日志框架，那么会
     * 根据日志框架的配置输出到日志文件中。
     *
     * @param message
     *        日志信息
     *
     * @param e
     *        异常对象
     *
     * @param args
     *        格式化参数
     */
    void error(String message, Throwable e, Object... args);

}

