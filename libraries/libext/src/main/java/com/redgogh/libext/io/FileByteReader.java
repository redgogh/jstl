package com.redgogh.libext.io;

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

import com.redgogh.libext.logging.Logger;
import com.redgogh.libext.logging.LoggerFactory;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 文件输入流
 */
public class FileByteReader extends FileInputStream {

    private static final Logger logger = LoggerFactory.getLogger(FileByteReader.class);

    public interface FileByteReaderResource {
        void apply(FileByteReader byteReader);
    }

    public FileByteReader(@NotNull String name) throws FileNotFoundException {
        super(name);
    }

    public FileByteReader(@NotNull File file) throws FileNotFoundException {
        super(file);
    }

    public FileByteReader(@NotNull FileDescriptor fdObj) {
        super(fdObj);
    }

    /**
     * #brief: 调用文件字节读取资源
     *
     * 该函数用于调用指定的 {@code FileByteReaderResource} 对象，并对其应用当前对象。
     * 在调用过程中，如果发生异常，会记录错误日志。
     * 函数执行完后，无论是否发生异常，都会尝试关闭当前对象的资源。
     *
     * 注意事项：
     * - 确保传入的 {@code fileByteReaderResource} 参数为非空且已经正确初始化。
     * - 异常被捕获后不会抛出，错误详情会记录在日志中。
     * - 使用 {@code IOUtils.closeQuietly(this)} 方法在 {@code finally} 块中关闭资源，
     *   不会抛出关闭异常。
     *
     * @param fileByteReaderResource
     *        需要应用的文件字节读取资源对象
     */
    public void call(FileByteReaderResource fileByteReaderResource) {
        try {
            fileByteReaderResource.apply(this);
        } catch (Exception e) {
            logger.error("FileByteReader call error!", e);
        } finally {
            IOUtils.closeQuietly(this);
        }
    }

}
