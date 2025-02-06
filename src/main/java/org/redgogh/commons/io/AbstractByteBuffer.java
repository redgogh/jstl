package org.redgogh.commons.io;

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

/* Creates on 2023/5/8. */

import org.redgogh.commons.utils.ArrayUtils;

/**
 * ByteBuffer 默认抽象实现类
 *
 * @author RedGogh
 */
public abstract class AbstractByteBuffer extends ByteBuffer {

    /** 读写指针 */
    protected int position;

    /** 字节缓冲区内部真实数据大小 */
    protected int capacity;

    /* 临时缓冲区 */
    private final byte[] tmp = new byte[16];

    public int size() {
        return capacity;
    }

    public int readableBytes() {
        return capacity - position;
    }

    public ByteBuffer seekSet(int off) {
        position = off;
        return this;
    }

    public ByteBuffer seekCur(int off) {
        position += off;
        return this;
    }

    public ByteBuffer seekEnd(int off) {
        position = capacity - off;
        return this;
    }

    public byte readByte() {
        readBytes(tmp, 0, 1);
        return tmp[0];
    }

    public int readShort() {
        readBytes(tmp, 0, Short.BYTES);
        return (short) ((tmp[0] << 8) | tmp[1] & 0xFF);
    }

    public int readInt() {
        readBytes(tmp, 0, Integer.BYTES);
        return ((tmp[0] & 0xff) << 24)
                | ((tmp[1] & 0xff) << 16)
                | ((tmp[2] & 0xff) << 8)
                |  (tmp[3] & 0xff);
    }

    public long readLong() {
        readBytes(tmp, 0, Long.BYTES);
        long value = 0L;
        for (int i = 0; i < Long.BYTES; i++)
            value = (value << 8) | (tmp[i] & 0xFF);
        return value;
    }

    public int readBytes(byte[] b, int off, int len) {
        ArrayUtils.checkIndexSize(off, len, b.length);
        int remcap = capacity - position;
        if (remcap == 0)
            return IOUtils.EOF;
        if (len > remcap)
            len = remcap;
        read0(b, off, len);
        return len;
    }

    public void writeShort(short value) {
        writeBytes(new byte[]{
                (byte) (value >> 8),
                (byte) value,
        }, 0, Short.BYTES);
    }

    public void writeInt(int i) {
        tmp[0] = (byte) ((i >> 24) & 0xff);
        tmp[1] = (byte) ((i >> 16) & 0xff);
        tmp[2] = (byte) ((i >> 8) & 0xff);
        tmp[3] = (byte) (i & 0xff);
        writeBytes(tmp, 0, Integer.BYTES);
    }

    public void writeLong(long value) {
        for (int i = 0; i < Long.BYTES; i++)
            tmp[i] = (byte) ((value >> ((Long.BYTES - i - 1) * 8)) & 0xFF);
        writeBytes(tmp, 0, Long.BYTES);
    }

    public void writeByte(byte b) {
        tmp[0] = b;
        writeBytes(tmp, 0, Byte.BYTES);
    }

    public void writeBytes(byte[] b) {
        writeBytes(b, 0, b.length);
    }

    public void writeBytes(byte[] b, int off, int len) {
        ArrayUtils.checkIndexSize(off, len, b.length);
        write0(b, off, len);
    }

    abstract void read0(byte[] b, int off, int len);

    abstract void write0(byte[] b, int off, int len);

    public byte[] toByteArray() {
        byte[] retval = new byte[capacity];
        seekSet(0);
        readBytes(retval, 0, retval.length);
        seekEnd(0);
        return retval;
    }

}
