package com.redgogh.libext.logging;

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

