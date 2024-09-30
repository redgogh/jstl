package com.redgogh.tools.logging.standard;

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

import com.redgogh.tools.logging.Logger;
import com.redgogh.tools.time.DateFormatter;

import static com.redgogh.tools.StringUtils.strwfmt;
import static com.redgogh.tools.io.IOUtils.stdout;

/**
 * @author RedGogh   
 */
public class StandardLogger implements Logger {

    private final String classpath;

    public StandardLogger(String name) {
        this.classpath = name;
    }

    public StandardLogger(Class<?> aClass) {
        this.classpath = aClass.getName();
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public void info(String message, Object... args) {
        logprint(classpath, "INFO", message, args);
    }

    @Override
    public void warn(String message, Object... args) {
        logprint(classpath, "WARN", message, args);
    }

    @Override
    public void debug(String message, Object... args) {
        logprint(classpath, "DEBUG", message, args);
    }

    @Override
    public void error(String message, Object... args) {
        logprint(classpath, "ERROR", message, args);
    }

    @Override
    public void error(String message, Throwable e, Object... args) {
        logprint(classpath, "ERROR", e, message, args);
    }

    private static void logprint(String classpath, String level, String message, Object... args) {
        logprint(classpath, level, null, message, args);
    }

    private static void logprint(String classpath, String level, Throwable e, String message, Object... args) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[stackTrace.length - 1];
        System.out.printf("%s %s [%s] %s --- %s\n",
                DateFormatter.fmt(),
                level,
                stackTraceElement.getMethodName(),
                classpath,
                strwfmt(message, args));

        if (e != null) {
            e.printStackTrace(stdout);
        }
    }

}

