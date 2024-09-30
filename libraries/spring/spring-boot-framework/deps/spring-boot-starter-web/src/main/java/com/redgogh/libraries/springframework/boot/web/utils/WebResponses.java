package com.redgogh.libraries.springframework.boot.web.utils;

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

/* Creates on 2023/5/18. */

import com.redgogh.tools.exception.IOWriteException;
import com.redgogh.tools.io.IOUtils;
import com.redgogh.tools.io.File;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.redgogh.tools.StringUtils.*;

/**
 * @author RedGogh
 */
public class WebResponses {

    /**
     * 添加文件返回对象
     *
     * @param response
     *        响应对象
     *
     * @param file
     *        文件对象
     */
    public static void addFileHeader(HttpServletResponse response, File file) {
        try {
            response.reset();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setContentLength((int) file.length());
            response.setHeader("Content-Disposition", strwfmt("attachment;filename=%s", file.getName()));
            IOUtils.write(file.openByteReader(), response.getOutputStream());
        } catch (IOException e) {
            response.reset();
            throw new IOWriteException(e);
        }
    }

}
