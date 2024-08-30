package com.redgogh.libraries.springframework.boot.web.utils;

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

/* Creates on 2023/5/18. */

import com.redgogh.vortextools.exception.IOWriteException;
import com.redgogh.vortextools.io.IOUtils;
import com.redgogh.vortextools.io.UFile;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.redgogh.vortextools.StringUtils.*;

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
    public static void addFileHeader(HttpServletResponse response, UFile file) {
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
