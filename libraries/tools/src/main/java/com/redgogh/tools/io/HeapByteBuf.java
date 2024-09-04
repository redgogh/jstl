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

/* Creates on 2023/5/8. */

/**
 * @author RedGogh
 */
public class  HeapByteBuf extends ByteBuf {

    /** 字节缓冲区 */
    private byte[] buf;
    /** 扩容次数 */
    private int count = 1;
    /** 每次扩容大小为初始分配大小 */
    private final int initializeCapacity;

    HeapByteBuf(int capacity) {
        initializeCapacity = capacity;
        buf = new byte[initializeCapacity];
    }

    /** 确保数据写入时缓冲区内部容量足够 */
    private void ensureCapacity(int size) {
        if (buf.length < (capacity + size)) {
            byte[] nbuf = new byte[((buf.length + size) + initializeCapacity) * count];
            System.arraycopy(buf, 0, nbuf, 0, buf.length);
            buf = nbuf;
            ++count;
        }
    }

    @Override
    public void read0(byte[] b, int off, int len) {
        System.arraycopy(buf, position, b, off, len);
        position += len;
    }

    @Override
    void write0(byte[] a, int off, int len) {
        ensureCapacity(len);
        System.arraycopy(a, off, buf, position, len);
        position += len;
        capacity += len;
    }

}
