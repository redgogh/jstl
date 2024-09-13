package com.redgogh.testing;

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

/* Create on 2024/9/13 */

import com.redgogh.tools.http.HttpClient;
import com.redgogh.tools.http.MultipartBody;
import com.redgogh.tools.http.Response;
import com.redgogh.tools.io.File;
import org.junit.Test;

/**
 * @author RedGogh
 */
public class TikaRequestTest {

    @Test
    public  void parsePDFTest() {
        Response response = HttpClient.open("POST", "http://127.0.0.1:8001/testing/tika/pdf")
                .setRequestBody(new MultipartBody("file", new File("Desktop://附件八、历史迁移数据库设计.xlsx")))
                .newCall();
        System.out.println(response.getString("data"));
    }

}
