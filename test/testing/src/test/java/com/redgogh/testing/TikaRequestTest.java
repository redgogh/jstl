package com.redgogh.testing;

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
                .setRequestBody(new MultipartBody("file", new File("Desktop://log.txt")))
                .newCall();
        System.out.println(response.getString("data"));
    }

}
