package com.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class SelectionKeyTest {
    @Test
    public void client() throws IOException {
        SocketChannel s = SocketChannel.open(new InetSocketAddress(6666));
        s.configureBlocking(false);
        ByteBuffer bb = ByteBuffer.allocate(12);
        bb.put("abc".getBytes());
        bb.rewind();
        s.write(bb);
        s.close();
    }

    @Test
    public void channel() throws IOException {
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.configureBlocking(false);
        ss.bind(new InetSocketAddress(6666));

        Selector selector = Selector.open();
        ss.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();

                SelectableChannel channel = next.channel();

                iterator.remove();
            }
        }
    }

    @Test
    public void selector() throws IOException {
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.configureBlocking(false);
        ss.bind(new InetSocketAddress(6666));

        Selector selector = Selector.open();
        ss.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();

                Selector se = next.selector();
                System.out.println(se == selector);

                iterator.remove();
            }
        }
    }

    @Test
    public void isValid() throws IOException {
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.configureBlocking(false);
        ss.bind(new InetSocketAddress(6666));

        Selector selector = Selector.open();
        ss.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();

                boolean b = next.isValid();

                iterator.remove();
            }
        }
    }

    @Test
    public void cancel() throws IOException {
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.configureBlocking(false);
        ss.bind(new InetSocketAddress(6666));

        Selector selector = Selector.open();
        ss.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();

                next.cancel();

                iterator.remove();
            }
        }
    }

    @Test
    public void interestOps() throws IOException {
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.configureBlocking(false);
        ss.bind(new InetSocketAddress(6666));

        Selector selector = Selector.open();
        ss.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();

                int a = next.interestOps();

                iterator.remove();
            }
        }
    }

    @Test
    public void interestOps2() throws IOException {
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.configureBlocking(false);
        ss.bind(new InetSocketAddress(6666));

        Selector selector = Selector.open();
        ss.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();

                SelectionKey i = next.interestOps(0);

                iterator.remove();
            }
        }
    }

    @Test
    public void readyOps() throws IOException {
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.configureBlocking(false);
        ss.bind(new InetSocketAddress(6666));

        Selector selector = Selector.open();
        ss.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();

                int i = next.readyOps();

                iterator.remove();
            }
        }
    }

    @Test
    public void isX() throws IOException {
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.configureBlocking(false);
        ss.bind(new InetSocketAddress(6666));

        Selector selector = Selector.open();
        ss.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();

                boolean a1 = next.isReadable();
                boolean a2 = next.isWritable();
                boolean a3 = next.isConnectable();
                boolean a4 = next.isAcceptable();

                iterator.remove();
            }
        }
    }

    @Test
    public void attach() throws IOException {
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.configureBlocking(false);
        ss.bind(new InetSocketAddress(6666));

        Selector selector = Selector.open();
        ss.register(selector, SelectionKey.OP_ACCEPT);

        Object o = new Object();

        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();

                next.attach(o);

                Object attachment = next.attachment();

                iterator.remove();
            }
        }
    }


}
