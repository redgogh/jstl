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

/* Creates on 2022/8/8. */

import com.redgogh.vortextools.http.FormBodyBuilder;
import com.redgogh.vortextools.http.HttpClients;
import com.redgogh.vortextools.io.UFile;
import org.junit.Test;

@SuppressWarnings("ALL")
public class HttpClientsExample {

    @Test
    public void callFormBodyExample() {
        FormBodyBuilder formBodyBuilder = new FormBodyBuilder();
        formBodyBuilder.put("document", new UFile("src/main/java/com/redgogh/vortextools/ArrayUtils.java"));
        System.out.println(HttpClients.post("http://127.0.0.1:8001/security/check-file", formBodyBuilder));
    }

}
