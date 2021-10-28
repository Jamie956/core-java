package com.jamie.socket;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * channel 通道：负责传输 缓冲区数据
 * <p>
 * 通道类型：
 * FileChannel
 * SocketChannel
 * ServerSocketChannel
 * DatagramChannel
 */
public class ChannelTest {

    /**
     * 通道传输 非直接缓冲区
     */
    @Test
    public void t1() throws IOException {
        //创建文件输入输出流实例
        FileInputStream in = new FileInputStream(new File("src/main/resources/source"));
        FileOutputStream out = new FileOutputStream(new File("src/main/resources/copy_source"));

        //从流中获取通道
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();

        //创建缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //通道传输缓冲区数据
        while (inChannel.read(buf) != -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }
        //省略关闭流操作
    }

    /**
     * 通道传输 直接缓冲区，内存映射文件
     */
    @Test
    public void t2() throws IOException {
        //open 读写通道
        FileChannel inChannel = FileChannel.open(Paths.get("src/main/resources/source"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("src/main/resources/copy_source"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //内存映射文件 缓冲区
        MappedByteBuffer inMap = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMap = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区进行数据的读写操作
        byte[] bytes = new byte[inMap.limit()];
        inMap.get(bytes);
        outMap.put(bytes);

        //关闭通道
        inChannel.close();
        outChannel.close();

    }

    /**
     * 直接缓冲区 文件通道 transferTo
     */
    @Test
    public void t3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("src/main/resources/source"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("src/main/resources/copy_source"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        inChannel.transferTo(0, inChannel.size(), outChannel);

        inChannel.close();
        outChannel.close();

    }

    //参考
    //字符集
    @Test
    public void test6() throws IOException {
        Charset cs1 = Charset.forName("GBK");

        //获取编码器
        CharsetEncoder ce = cs1.newEncoder();

        //获取解码器
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("！");
        cBuf.flip();

        //编码
        ByteBuffer bBuf = ce.encode(cBuf);

        for (int i = 0; i < 12; i++) {
            System.out.println(bBuf.get());
        }

        //解码
        bBuf.flip();
        CharBuffer cBuf2 = cd.decode(bBuf);
        System.out.println(cBuf2.toString());

        System.out.println("------------------------------------------------------");

        Charset cs2 = Charset.forName("GBK");
        bBuf.flip();
        CharBuffer cBuf3 = cs2.decode(bBuf);
        System.out.println(cBuf3.toString());
    }

    //分散和聚集
    @Test
    public void test4() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("1.txt", "rw");

        //1. 获取通道
        FileChannel channel1 = raf1.getChannel();

        //2. 分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        //3. 分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        channel1.read(bufs);

        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }

        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("-----------------");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        //4. 聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();

        channel2.write(bufs);
    }
}
