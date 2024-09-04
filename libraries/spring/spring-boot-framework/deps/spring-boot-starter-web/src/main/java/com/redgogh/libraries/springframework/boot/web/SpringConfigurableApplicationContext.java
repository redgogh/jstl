package com.redgogh.libraries.springframework.boot.web;

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
