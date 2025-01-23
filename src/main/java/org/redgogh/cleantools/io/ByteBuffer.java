package org.redgogh.devtools.io;

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

import org.redgogh.devtools.base.ArrayUtils;

/**
 * @author RedGogh
 */
public abstract class ByteBuffer {

    /** 读写指针 */
    protected int position;

    /** 字节缓冲区内部真实数据大小 */
    protected int capacity;

    /* 临时缓冲区 */
    private final byte[] buftmp = new byte[16];

    /* 控制读写指针属性 */
    public static final SeekOption SEEK_SET = (byteBuffer, value) -> byteBuffer.position = value;
    public static final SeekOption SEEK_CUR = (byteBuffer, value) -> byteBuffer.position += value;
    public static final SeekOption SEEK_END = (byteBuffer, value) -> byteBuffer.position = byteBuffer.capacity - value;

    public interface SeekOption {
        void getpos(ByteBuffer byteBuffer, int value);
    }

    /**
     * @return 分配一个默认 4kb 大小的 {@link ByteBuffer} 缓冲区。
     */
    public static ByteBuffer allocate() {
        return allocate(IOUtils.DEFAULT_BYTE_BUFFER_SIZE);
    }

    /**
     * 分配一个默认 {@code size} 大小的 {@link ByteBuffer} 缓冲区。该缓冲区使用 JVM 中内部的堆内存储存数据,
     * 所以这个 {@link ByteBuffer} 的最大存储大小取决于 JVM 的启动参数 {@code -Xmx} 设置的堆内存
     * 有多大。
     * <p>
     * 但是在 JDK1.9 版本之前一般 JVM 的堆内存不会调整到太大，通常来说就几百兆，几个G的大小。JDK9 后
     * 的新 GC 机制对于特大内存（如几百G、几TB的内存）、大数据来说后续 JVM 的内存可能已经足够使用了
     * 并不需要在使用之前的 Unsafe 类去堆外分配新的内存。而且这种情况也不是安全的。
     * <p>
     * 所以通常来说在堆中分配的内存就足以完成 90% 的使用需求了，这个类后续应该也不会扩展 DirectByteBuffer
     * 实现类。
     *
     * @return 创建一个 {@link HeapByteBuffer} 子类对象实例，内部的缓冲区默认大小为
     *         参数 {@code size} 的值。
     */
    public static ByteBuffer allocate(int size) {
        return new HeapByteBuffer(size);
    }

    /**
     * #brief：传入一个字节数组，将字节数组封装成 ByteBuf 对象。读写指针默认在最后一位。<p>
     *
     * 传入一个字节数组，将字节数组封装成 ByteBuf 对象。这个字节数组可以是
     * 任意大小。封装后读写指针位置将重置在最后一位方便数据写入。<p>
     *
     * @param b
     *        字节数组
     *
     * @return 封装了 {@code b} 字节数组的缓冲区对象实例。
     */
    public static ByteBuffer wrap(byte[] b) {
        return wrap(b, 0, b.length);
    }

    /**
     * #brief：传入一个字节数组，将字节数组封装成 ByteBuf 对象。读写指针默认在最后一位。<p>
     *
     * 传入一个字节数组，将字节数组封装成 ByteBuf 对象。这个字节数组可以是
     * 任意大小。封装后读写指针位置将重置在最后一位方便数据写入。<p>
     *
     * @param b
     *        要写入数据的字节数组
     *
     * @param off
     *        开始索引位置
     *
     * @param len
     *        写入长度
     *
     * @return 封装了 {@code b} 字节数组的缓冲区对象实例。
     */
    public static ByteBuffer wrap(byte[] b, int off, int len) {
        ByteBuffer buffer = allocate();
        buffer.write(b, off, len);
        return buffer;
    }

    /**
     * @return 返回缓冲区的真实数据大小
     */
    public int size() {
        return capacity;
    }

    /**
     * #brief: 重置读写指针位置。<p>
     *
     * 设置 {@link ByteBuffer} 内部读写指针偏移量，这个 {@link ByteBuffer} 是支持随机读写的对象，
     * 所以可以通过 {@code seek()} 函数设置内部的读写指针。当执行完所有写入操作时，使用者应该调
     * 用 {@code seek(SEEK_SET, 0)} 来重置缓冲区内部的读写指针位置。它会被重置到 {@code 0} 的
     * 位置上。
     * <p>
     * 如果需要继续写入，应该调用 {@code seek(SEEK_END, 0)} 来重置到缓冲区最后的位置，然后才应该
     * 调用缓冲区的写入函数。
     * <p>
     * 如果你是 c/c艹 的使用者，那么你应该很清楚这个函数在做什么！
     *
     * @param sop
     *        设置操作，{@code SEEK_SET}、{@code SEEK_CUR}、{@code SEEK_END}
     *
     * @param off
     *        读写指针偏移量
     *
     * @see #SEEK_SET
     * @see #SEEK_CUR
     * @see #SEEK_END
     */
    public ByteBuffer seek(SeekOption sop, int off) {
        sop.getpos(this, off);
        return this;
    }

    /**
     * #brief: 从字节数组中读取前 4 个字节并转换为 int 类型的值。<p>
     *
     * 根据当前 {@code position} 的开始位置往后读取 4 个字节的数据。将
     * 读取到的字节数组转换成一个 int 类型结果。
     *
     * @return 从字节数组读到的 int 值
     */
    public int readInt() {
        read(buftmp, 0, Integer.BYTES);
        return ((buftmp[0] & 0xff) << 24)
                | ((buftmp[1] & 0xff) << 16)
                | ((buftmp[2] & 0xff) << 8)
                |  (buftmp[3] & 0xff);
    }

    /**
     * #brief: 从字节数组中读取前 8 个字节并转换为 long 类型的值。<p>
     *
     * 根据当前 {@code position} 的开始位置往后读取 8 个字节的数据。将
     * 读取到的字节数组转换成一个 long 类型结果。
     *
     * @return 从字节数组读到的 long 值
     */
    public long readLong() {
        read(buftmp, 0, Long.BYTES);
        long value = 0L;
        for (int i = 0; i < Long.BYTES; i++)
            value = (value << 8) | (buftmp[i] & 0xFF);
        return value;
    }

    /**
     * 读取 {@link ByteBuffer} 中的字节数据，通过传入的 {@code b}、{@code off}、{@code len} 这三个参数
     * 来确定需要读取多少个字节到 {@code b} 字节数组中。这个函数内部调用了 {@link #read0(byte[], int, int)}
     * 函数。它与 {@code read0()} 函数唯一不同的区别就在于，这个函数会做索引越界等校验，来确保读取时的安全性、
     * 以及稳定性
     *
     * @param b
     *        读取 {@link ByteBuffer} 中数据到 {@code b} 这个字节数组中
     *
     * @param off
     *        字节数组起始索引，从 {@code b[off]} 开始写入第一个字节
     *
     * @param len
     *        读取的总长度，字节数组结束索引为 {@code b[off + len]}
     */
    public int read(byte[] b, int off, int len) {
        ArrayUtils.checkIndexSize(off, len, b.length);
        int remcap = capacity - position;
        if (remcap == 0)
            return IOUtils.EOF;
        if (len > remcap)
            len = remcap;
        read0(b, off, len);
        return len;
    }

    /**
     * #brief: ByteBuf 数据读取唯一接口，需要子类自行实现。<p>
     *
     * 读取 {@link ByteBuffer} 中的字节数据，这个函数是一个抽象函数，同时也是所有 {@code read()}
     * 函数的底层接口。类似 {@link #write0(byte[], int, int)} 函数的作用，所有的读取操作底层都
     * 必须由它来完成，子类需要实现这个函数。可以参考 {@link HeapByteBuffer} 实现。这是目前唯一
     * 一个库内自带的实现子类。
     * <p>
     * 实现 {@code read0()} 函数内部并不需要做任何有关校验的代码，因为校验代码在父类中已经提供
     * 了，所以子类实现 {@code read0()} 时只需要实现读取即可。
     *
     * @param b
     *        读取 {@link ByteBuffer} 中数据到 {@code b} 这个字节数组中
     *
     * @param off
     *        字节数组起始索引，从 {@code b[off]} 开始写入第一个字节
     *
     * @param len
     *        读取的总长度，字节数组结束索引为 {@code b[off + len]}
     */
    abstract void read0(byte[] b, int off, int len);

    /**
     * #brief: 写入 int 类型的数据到字节缓冲区中。<p>
     *
     * 写入 int 值到字节缓冲区中，占用 4 个字节的空间。先写入高位字节，
     * 后写入低位字节。
     *
     * @param i
     *        int 类型的整数
     */
    public void write(int i) {
        buftmp[0] = (byte) ((i >> 24) & 0xff);
        buftmp[1] = (byte) ((i >> 16) & 0xff);
        buftmp[2] = (byte) ((i >> 8) & 0xff);
        buftmp[3] = (byte) (i & 0xff);
        write(buftmp, 0, Integer.BYTES);
    }

    /**
     * #brief: 写入 int 类型的数据到字节缓冲区中。<p>
     *
     * 写入 int 值到字节缓冲区中，占用 4 个字节的空间。先写入高位字节，
     * 后写入低位字节。
     *
     * @param value
     *        long 类型的整数
     */
    public void write(long value) {
        for (int i = 0; i < Long.BYTES; i++)
            buftmp[i] = (byte) ((value >> ((Long.BYTES - i - 1) * 8)) & 0xFF);
        write(buftmp, 0, Long.BYTES);
    }

    /**
     * #brief: 写入单个字节数据到缓冲区中
     *
     * @param b
     *        要写入的字节
     */
    public void write(byte b) {
        buftmp[0] = b;
        write(buftmp, 0, Byte.BYTES);
    }

    /**
     * 将整个字节缓冲的内容写入到 {@link ByteBuffer} 中。
     */
    public void write(byte[] b) {
        write(b, 0, b.length);
    }

    /**
     * 通过 {@code off} 索引读取字节数组中的内存写入到缓冲区。读取的第一个字节从 {@code b[off]} 开始
     * 到 {@code b[len]} 结束。它与大部分 io 操作的传参方式相同。
     *
     * @param b
     *        要写入数据的字节数组
     *
     * @param off
     *        开始索引位置
     *
     * @param len
     *        写入长度
     */
    public void write(byte[] b, int off, int len) {
        ArrayUtils.checkIndexSize(off, len, b.length);
        write0(b, off, len);
    }

    /**
     * #brief: ByteBuf 数据写入唯一接口，需要子类自行实现。<p>
     *
     * {@code write0()} 函数是 {@link ByteBuffer} 抽象类中需要子类去实现的一个函数。这个函数是
     * 所有数据写入的唯一接口，写入到 {@link ByteBuffer} 中的所有数据都必须经过它，其他的写入函数
     * 也只是在它之上做了一层封装，所以 {@code write0()} 这个函数就决定子类实现的 {@link ByteBuffer}
     * 好不好用、性能、安全等所有关键性问题。
     * <p>
     * 写入数据会有空指针、长度、偏移量等索引检查，但这个函数不需要做任何检查，因为在其他 {@code write()}
     * 函数中都已经做了这样的数据写入检查了。它只需要确保内部缓冲区的容量足够大，并且不会超出边界溢出即可。
     * 完成了这两个需求，那么它就算是一个合格的 {@link ByteBuffer} 子类。
     *
     * @param b
     *        写入的数据内容字节数组
     *
     * @param off
     *        从参数 {@code b[off]} 位置开始读取数据写入到 {@link ByteBuffer}
     *
     * @param len
     *        字节数组的结束位置，读取的内容从 {@code b[off]} 到 {@code b[len]}
     */
    abstract void write0(byte[] b, int off, int len);

    /**
     * @return 分配一个新的 {@code byte} 字节数组，将 {@link ByteBuffer} 缓冲区中
     *         有效的数据拷贝到新分配的字节数组，并返回。
     */
    public byte[] toByteArray() {
        byte[] retval = new byte[capacity];
        seek(SEEK_SET, 0);
        read(retval, 0, retval.length);
        seek(SEEK_END, 0);
        return retval;
    }

}
