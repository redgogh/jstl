package com.redgogh.examples;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 VCentGogh                                                  *|
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

import com.redgogh.libext.logging.Logger;
import com.redgogh.libext.logging.LoggerFactory;
import org.junit.Test;

@SuppressWarnings("ALL")
public class LoggingExample {

    private static Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    @Test
    public void loggingExample() {
        logger.info("INFO");
        logger.debug("DEBUG");
        logger.warn("WARN");
        logger.error("ERROR");
    }

    @Test
    public void loggingErrorLevelExample() {
        try {
            Object o = null;
            o.hashCode();
        } catch (Exception e) {
            logger.error("loggingErrorLevelExample --- %s", e, "ERROR");
        }
    }

}