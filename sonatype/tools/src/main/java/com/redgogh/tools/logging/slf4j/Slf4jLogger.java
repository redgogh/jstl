package com.redgogh.tools.logging.slf4j;

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
import org.slf4j.helpers.NOPLogger;

import static com.redgogh.tools.StringUtils.strwfmt;

/**
 * @author RedGogh   
 */
public class Slf4jLogger implements Logger {

    private final org.slf4j.Logger logger;

    public Slf4jLogger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean isValid() {
        return !(logger instanceof NOPLogger);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void info(String message, Object... args) {
        logger.info(strwfmt(message, args));
    }

    @Override
    public void warn(String message, Object... args) {
        logger.warn(strwfmt(message, args));
    }

    @Override
    public void debug(String message, Object... args) {
        logger.debug(strwfmt(message, args));
    }

    @Override
    public void error(String message, Object... args) {
        logger.error(strwfmt(message, args));
    }

    @Override
    public void error(String message, Throwable e, Object... args) {
        logger.error(strwfmt(message, args), e);
    }

}

