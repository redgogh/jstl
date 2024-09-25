package com.redgogh.tools.http;

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

import com.redgogh.tools.io.File;
import com.redgogh.tools.io.IOUtils;
import okhttp3.ResponseBody;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

import static com.redgogh.tools.Assert.throwIfNull;

public class OctetStreamResponse implements Closeable {

    private final okhttp3.Response response;

    OctetStreamResponse(okhttp3.Response response) {
        this.response = response;
    }

    /**
     * 将响应体内容传输到指定路径的文件。
     *
     * <p>此方法通过调用 {@link #transferTo(File)} 实现，
     * 将给定路径 {@code path} 转换为 {@link File} 对象，
     * 并将响应体内容写入该文件。
     *
     * @param path 要传输内容的目标文件路径
     * @return 传输完成的 {@link File} 对象
     */
    public File transferTo(String path) {
        return transferTo(File.wrap(path));
    }

    /**
     * 将响应体内容传输到指定的 {@code java.io.File} 对象。
     *
     * <p>此方法将响应体内容写入提供的 {@link java.io.File} 实例，
     * 并返回该文件。
     *
     * @param file 要传输内容的目标文件
     * @return 传输完成的 {@link File} 对象
     */
    public File transferTo(java.io.File file) {
        return transferTo(File.wrap(file));
    }

    /**
     * 将响应体内容传输到指定的 {@link File} 对象。
     *
     * <p>从响应体中获取输入流，并将其内容写入提供的
     * {@link File} 实例，然后返回该文件。
     *
     * @param file 要传输内容的目标 {@link File} 对象
     * @return 传输完成的 {@link File} 对象
     */
    public File transferTo(File file) {
        ResponseBody body = throwIfNull(response.body(), "没有数据响应。");
        IOUtils.write(body.byteStream(), file);
        close();
        return file;
    }

    @Override
    public void close() {
        IOUtils.closeQuietly(response);
    }

}
