package com.redgogh.tools.io;

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

import com.redgogh.tools.logging.Logger;
import com.redgogh.tools.logging.LoggerFactory;
import org.jetbrains.annotations.NotNull;

import java.io.*;

/**
 * 文件输出流
 */
public class FileByteWriter extends FileOutputStream {

    private static final Logger logger = LoggerFactory.getLogger(FileByteWriter.class);

    public FileByteWriter(@NotNull String name) throws FileNotFoundException {
        super(name);
    }

    public FileByteWriter(@NotNull File file) throws FileNotFoundException {
        super(file);
    }

    public FileByteWriter(@NotNull FileDescriptor fdObj) {
        super(fdObj);
    }

    public interface FileByteWriterResource {
        void call(FileByteWriter byteWriter);
    }

    /**
     * #brief: 调用文件字节读取资源
     *
     * <p>该函数用于调用指定的 {@code FileByteReaderResource} 对象，并对其应用当前对象。
     * 在调用过程中，如果发生异常，会记录错误日志。
     * 函数执行完后，无论是否发生异常，都会尝试关闭当前对象的资源。
     * <p>
     * <h2>注意事项：</h2>
     * <ul>
     *   <li> 确保传入的 {@code fileByteReaderResource} 参数为非空且已经正确初始化。</li>
     *   <li> 异常被捕获后不会抛出，错误详情会记录在日志中。</li>
     *   <li> 使用 {@code IOUtils.closeQuietly(this)} 方法在 {@code finally} 块中关闭资源，
     *        不会抛出关闭异常。</li>
     * </ul>
     *
     * @param fileByteReaderResource
     *        需要应用的文件字节读取资源对象
     */
    public void call(FileByteWriterResource fileByteReaderResource) {
        try {
            fileByteReaderResource.call(this);
        } catch (Exception e) {
            logger.error("FileByteWriter call error!", e);
        } finally {
            IOUtils.closeQuietly(this);
        }
    }

}
