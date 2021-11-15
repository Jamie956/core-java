package com.jamie.io;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 缓冲区（Buffer）：本质上是数组，Java NIO 中负责数据存取，非线程安全
 * <p>
 * Buffer 变量：
 * capacity: 容纳元素的数量，不为负数，不可改变
 * limit: 首个不可读写元素的索引，不为负数，不大于 capacity
 * position: 下一个准备读写的元素的索引，不为负数，不大于 limit
 * mark: 标记索引，调用reset方法将 position -> mark，mark 非负且不大于 position
 * <p>
 * clear(): 为读新序列或写操作做准备，limit -> capacity，position -> zero
 * flip(): 为写新序列或get操作做准备，limit -> current position，position -> zero
 * rewind(): 复读，position -> zero
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
 * 缓冲区类型：
 * 直接缓冲区 allocateDirect：在物理内存中创建，比较高效
 * 非直接缓冲区 allocate：在JVM 中创建
 */
public class ByteBufferApiTest {

    @Test
    public void t1() {
        String str = "halo";
        byte[] strBytes = str.getBytes();
        //申请内存缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println(String.format("1 capacity=[%s], limit=[%s], position=[%s]", byteBuffer.capacity(), byteBuffer.limit(), byteBuffer.position()));

        //写入缓冲区，position指针移动
        byteBuffer.put(strBytes);

        System.out.println(String.format("2 capacity=[%s], limit=[%s], position=[%s]", byteBuffer.capacity(), byteBuffer.limit(), byteBuffer.position()));

        //为写新序列或get操作做准备，limit -> current position，position -> zero
        byteBuffer.flip();
        System.out.println("flip()");

        System.out.println(String.format("3 capacity=[%s], limit=[%s], position=[%s]", byteBuffer.capacity(), byteBuffer.limit(), byteBuffer.position()));

        //读取数据，读取之后 position指针移动
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println("读取：" + new String(bytes, 0, bytes.length));

        System.out.println(String.format("4 capacity=[%s], limit=[%s], position=[%s]", byteBuffer.capacity(), byteBuffer.limit(), byteBuffer.position()));

        //复读，position -> zero
        byteBuffer.rewind();
        System.out.println("rewind");
        System.out.println(String.format("5 capacity=[%s], limit=[%s], position=[%s]", byteBuffer.capacity(), byteBuffer.limit(), byteBuffer.position()));

        byte[] bytes2 = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes2);
        String readStr2 = new String(bytes2, 0, bytes2.length);
        System.out.println("读取：" + readStr2);

        //为读新序列或写操作做准备，limit -> capacity，position -> zero
        byteBuffer.clear();
        System.out.println("clear");
        System.out.println(String.format("6 capacity=[%s], limit=[%s], position=[%s]", byteBuffer.capacity(), byteBuffer.limit(), byteBuffer.position()));

        byte[] bytes3 = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes3);
        String readStr3 = new String(bytes3, 0, bytes3.length);
        System.out.println("读取：" + readStr3);

        byteBuffer.clear();

        System.out.println(String.format("7 capacity=[%s], limit=[%s], position=[%s]", byteBuffer.capacity(), byteBuffer.limit(), byteBuffer.position()));

        byteBuffer.put(str.getBytes());

        System.out.println(String.format("8 capacity=[%s], limit=[%s], position=[%s]", byteBuffer.capacity(), byteBuffer.limit(), byteBuffer.position()));

        //标记索引，调用reset方法将 position -> mark，mark 非负且不大于 position
        byteBuffer.mark();
        System.out.println("mark()");

        System.out.println(String.format("9 capacity=[%s], limit=[%s], position=[%s]", byteBuffer.capacity(), byteBuffer.limit(), byteBuffer.position()));

        String str2 = "tim";
        byteBuffer.put(str2.getBytes());

        System.out.println(String.format("10 capacity=[%s], limit=[%s], position=[%s]", byteBuffer.capacity(), byteBuffer.limit(), byteBuffer.position()));

        //调用reset方法将 position -> mark
        byteBuffer.reset();
        System.out.println("reset()");

        System.out.println(String.format("11 capacity=[%s], limit=[%s], position=[%s]", byteBuffer.capacity(), byteBuffer.limit(), byteBuffer.position()));

        int length = str2.getBytes().length;
        byte[] bytes4 = new byte[length];
        for (int i = 0; i < length; i++) {
            byte b = byteBuffer.get();
            bytes4[i] = b;
        }
        System.out.println("读取: " + new String(bytes4, 0, length));

        //申请直接缓冲区
        ByteBuffer.allocateDirect(1024);
    }

}
