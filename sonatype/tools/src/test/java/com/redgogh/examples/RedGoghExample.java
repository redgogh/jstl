package com.redgogh.examples;

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

import org.junit.Test;

import static com.redgogh.tools.BasicConverts.atobool;

@SuppressWarnings("ALL")
public class RedGoghExample {

    /**
     * atobool
     */
    @Test
    public void atoboolExample() {
        System.out.printf("----------------------------\n");
        System.out.printf("atobool 'y' example: %s\n", atobool("y"));
        System.out.printf("atobool 'n' example: %s\n", atobool("n"));
        System.out.printf("----------------------------\n");
        System.out.printf("atobool '1' example: %s\n", atobool(1));
        System.out.printf("atobool '0' example: %s\n", atobool(0));
        System.out.printf("----------------------------\n");
        System.out.printf("atobool 'true' example: %s\n", atobool("true"));
        System.out.printf("atobool 'false' example: %s\n", atobool("false"));
        System.out.printf("----------------------------\n");
    }

}
