package com.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

public class SelectorTest {

    @Test
    public void open() throws IOException {
        Selector selector = Selector.open();
    }

    @Test
    public void isOpen() throws IOException {
        Selector selector = Selector.open();
        boolean b = selector.isOpen();
    }

    @Test
    public void provider() throws IOException {
        Selector selector = Selector.open();
        SelectorProvider provider = selector.provider();
    }

    @Test
    public void keys() throws IOException {
        Selector selector = Selector.open();
        Set<SelectionKey> keys = selector.keys();
    }

    @Test
    public void selectedKeys() throws IOException {
        Selector selector = Selector.open();
        Set<SelectionKey> keys = selector.selectedKeys();
    }

    @Test
    public void selectNow() throws IOException {
        Selector selector = Selector.open();
        int i = selector.selectNow();
    }

    @Test
    public void select() throws IOException {
        Selector selector = Selector.open();
        int s = selector.select(0);
    }

    @Test
    public void select2() throws IOException {
        Selector selector = Selector.open();
        int s = selector.select();
    }

    @Test
    public void wakeup() throws IOException {
        Selector selector = Selector.open();
        Selector w = selector.wakeup();
    }

    @Test
    public void close() throws IOException {
        Selector selector = Selector.open();
        selector.close();
    }


    //---------------------------------------
    @Test
    public void server2() {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(8000));
            ssc.configureBlocking(false);

            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer readBuff = ByteBuffer.allocate(128);
            ByteBuffer writeBuff = ByteBuffer.allocate(128);

            while (true) {
                int nReady = selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();

                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();

                    if (key.isAcceptable()) {
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        readBuff.clear();
                        socketChannel.read(readBuff);
                        System.out.println("server receive: " + new String(readBuff.array()));
                        readBuff.flip();
                        key.interestOps(SelectionKey.OP_WRITE);
                    } else if (key.isWritable()) {
                        String msg = "hello client " + System.currentTimeMillis();

                        writeBuff.rewind();
                        writeBuff.put(msg.getBytes());
                        writeBuff.rewind();

                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.write(writeBuff);
                        System.out.println("server send: " + new String(writeBuff.array()));
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void client2() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(8000));

        ByteBuffer writeBuffer = ByteBuffer.allocate(32);
        ByteBuffer readBuffer = ByteBuffer.allocate(32);

        while (true) {
            String msg = "hello server " + System.currentTimeMillis();
            writeBuffer.rewind();
            writeBuffer.put(msg.getBytes());
            writeBuffer.rewind();
            socketChannel.write(writeBuffer);
            System.out.println("client send: " + new String(writeBuffer.array()));

            readBuffer.clear();
            socketChannel.read(readBuffer);
            System.out.println("client receive: " + new String(readBuffer.array()));
        }

    }

}
