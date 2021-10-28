package com.jamie;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 缓冲区（Buffer）：本质上是数组，Java NIO 中负责数据存取
 * <p>
 * 缓冲区数据类型：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * <p>
 * 缓冲区变量：
 * capacity: 容量，缓冲区数组长度，最大存储数据量
 * limit: 缓冲区可操作数据的大小
 * position: 缓冲区正在操作数据的位置
 * mark: 当前 position 的位置
 * <p>
 * 缓冲区类型：
 * 直接缓冲区 allocateDirect：在物理内存中创建，比较高效
 * 非直接缓冲区 allocate：在JVM 中创建
 */
public class NIOBufferTest {
    /**
     * 字节缓冲区 ByteBuffer API
     */
    @Test
    public void t1() {
        String str = "halo";
        //allocate 从内存申请缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //put 写入缓冲区，最终把源数组复制到堆数组 heap buffers，position 按输入字节长度移动
        buf.put(str.getBytes());

        //读取数据模式，修改指针，position = 0, limit = 数据结束位置
        buf.flip();

        //读取数据
        byte[] bytes = new byte[buf.limit()];
        //System.arraycopy() 堆数组复制到目标数组
        buf.get(bytes);
        String getBufStr = new String(bytes, 0, bytes.length);

        //position 设为0，即可重复读取数据
        buf.rewind();

        //重设limit -> 容量，旧数据依然存在，但数据结束位置的指针不再标记
        buf.clear();

    }

    @Test
    public void t2() {
        String str = "halo";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());
        buf.flip();

        buf.get();
        //mark = position 记录当前 position=1, mark=1
        buf.mark();
        //读取一个字节，position 指针移动一位，position=2, mark=1
        buf.get();
        //position = mark 将position 设为 mark，position=1
        buf.reset();

    }

    /**
     * 直接缓冲区
     */
    @Test
    public void t3() {
        ByteBuffer.allocateDirect(1024);
    }

}
