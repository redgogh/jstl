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

import com.redgogh.tools.logging.slf4j.Slf4jLoggerAdapter;
import com.redgogh.tools.logging.standard.StandardLoggerAdapter;
import com.redgogh.tools.refection.UClass;

import static com.redgogh.tools.StringUtils.strwfmt;

/**
 * @author RedGogh   
 */
public class LoggerFactory {

    /** 日志适配器 */
    private static LoggerAdapter loggerAdapterInstance = null;
    /** 标准适配器 */
    private static final LoggerAdapter standardLoggerAdapterInstance = new StandardLoggerAdapter();

    static {
        tryFindBestChoiceLoggerAdapter(LoggerFactory::slf4j);
        tryFindBestChoiceLoggerAdapter(LoggerFactory::standard);
    }

    /**
     * 根据传入的 {@code name} 来指定日志别名，如果是根据类型传入那么打印名称
     * 则会是类的全路径。
     *
     * @param name
     *        日志别名
     *
     * @return 日志记录器
     */
    public static Logger getLogger(String name) {
        return loggerAdapterInstance.getLogger(name);
    }

    /**
     * 根据传入的 {@code _class} 来指定日志对象路径，打印内容为 {@code _class} 类
     * 对象所在包的全路径名称。
     *
     * @param aClass
     *        日志对象
     *
     * @return 日志记录器
     */
    public static Logger getLogger(Class<?> aClass) {
        return loggerAdapterInstance.getLogger(aClass);
    }

    /**
     * #brief: 获取指定名称的本地调试日志记录器
     *
     * <p>根据提供的名称获取本地调试日志记录器。返回与该名称关联的日志记录器实例，
     * 该实例由标准日志适配器提供。
     *
     * @param name 日志记录器的名称
     * @return 与指定名称关联的日志记录器实例
     */
    public static Logger getDebuggerLogger(String name) {
        return standardLoggerAdapterInstance.getLogger(name);
    }

    /**
     * #brief: 获取指定类的本地调试日志记录器
     *
     * <p>根据提供的类获取本地调试日志记录器。返回与该类关联的日志记录器实例，
     * 该实例由标准日志适配器提供。
     *
     * @param aClass 日志记录器关联的类
     * @return 与指定类关联的日志记录器实例
     */
    public static Logger getDebuggerLogger(Class<?> aClass) {
        return standardLoggerAdapterInstance.getLogger(aClass);
    }

    /**
     * 尝试查找最优解的日志适配器
     */
    private static void tryFindBestChoiceLoggerAdapter(Runnable runnable) {
        try {
            if (loggerAdapterInstance == null)
                runnable.run();
        } catch (Throwable ignoreException) {
            // ignore...
        }
    }

    private static void slf4j() {
        try {
            Slf4jLoggerAdapter adapter = UClass.newInstance(Slf4jLoggerAdapter.class);
            Logger logger = adapter.getLogger(LoggerFactory.class);
            if (!logger.isValid())
                return;
            logger.info(formatBestChoice("SLF4J"));
            loggerAdapterInstance = adapter;
        } catch (Throwable ignoreException) {
            // ignore...
        }
    }

    private static void standard() {
        try {
            Logger logger = standardLoggerAdapterInstance.getLogger(LoggerFactory.class);
            logger.info(formatBestChoice("STANDARD"));
            loggerAdapterInstance = standardLoggerAdapterInstance;
        } catch (Throwable ignoreException) {
            // ignore...
        }
    }

    /** 打印当前日志框架初始化使用的适配器 */
    private static String formatBestChoice(String adapter) {
        return strwfmt("Try find best choice logger adapter, current use %s adapter.", adapter);
    }

}

