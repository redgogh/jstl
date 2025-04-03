package org.karatsuba.http;

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

import org.karatsuba.io.PhysicalFile;
import org.karatsuba.utils.Assert;
import org.karatsuba.io.IOUtils;
import okhttp3.ResponseBody;

import java.io.Closeable;

/**
 * 类 {@link StreamResponse} 表示一个字节流响应。
 *
 * <p>该类实现了 {@link Closeable} 接口，意味着在使用完该响应后，
 * 应该调用 {@link #close()} 方法来释放资源。
 *
 * <p>此类通常用于处理来自服务器的字节流数据，提供了方法来
 * 读取响应内容和处理响应状态。
 *
 * @author Red Gogh
 */
public class StreamResponse implements Closeable {

    private final okhttp3.Response response;

    StreamResponse(okhttp3.Response response) {
        this.response = response;
    }

    /**
     * 将响应体内容传输到指定路径的文件。
     *
     * <p>此方法通过调用 {@link #transferTo(PhysicalFile)} 实现，
     * 将给定路径 {@code path} 转换为 {@link PhysicalFile} 对象，
     * 并将响应体内容写入该文件。
     *
     * @param path 要传输内容的目标文件路径
     * @return 传输完成的 {@link PhysicalFile} 对象
     */
    public PhysicalFile transferTo(String path) {
        return transferTo(PhysicalFile.from(path));
    }

    /**
     * 将响应体内容传输到指定的 {@code java.io.File} 对象。
     *
     * <p>此方法将响应体内容写入提供的 {@link java.io.File} 实例，
     * 并返回该文件。
     *
     * @param file 要传输内容的目标文件
     * @return 传输完成的 {@link PhysicalFile} 对象
     */
    public PhysicalFile transferTo(java.io.File file) {
        return transferTo(PhysicalFile.from(file));
    }

    /**
     * 将响应体内容传输到指定的 {@link PhysicalFile} 对象。
     *
     * <p>从响应体中获取输入流，并将其内容写入提供的
     * {@link PhysicalFile} 实例，然后返回该文件。
     *
     * @param physicalFile 要传输内容的目标 {@link PhysicalFile} 对象
     * @return 传输完成的 {@link PhysicalFile} 对象
     */
    public PhysicalFile transferTo(PhysicalFile physicalFile) {
        ResponseBody body = response.body();
        Assert.notNull(body, "没有数据响应。");
        IOUtils.write(body.byteStream(), physicalFile);
        close();
        return physicalFile;
    }

    @Override
    public void close() {
        IOUtils.closeQuietly(response);
    }

}
