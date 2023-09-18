package com.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SocketChannelTest {

    @Test
    public void writeServer() throws IOException {
        ServerSocketChannel s = ServerSocketChannel.open();
        s.bind(new InetSocketAddress(6666));
        SocketChannel in = s.accept();

        ByteBuffer bb = ByteBuffer.allocate(12).put(new byte[]{97, 98, 99});
        bb.flip();
        in.write(bb);
    }

    @Test
    public void readServer() throws IOException {
        ServerSocketChannel s = ServerSocketChannel.open();
        s.bind(new InetSocketAddress(6666));
        SocketChannel in = s.accept();

        ByteBuffer bb = ByteBuffer.allocate(4);
        if (in.read(bb) != -1) {
            System.out.println(bb.get(0));
            System.out.println(bb.get(1));
        }
    }

    @Test
    public void open() throws IOException {
        SocketChannel s = SocketChannel.open(new InetSocketAddress(6666));
        int i = s.validOps();
    }

    @Test
    public void bind() throws IOException {
        InetSocketAddress ad = new InetSocketAddress(6666);
        SocketChannel s = SocketChannel.open();
        s.bind(ad);
    }

    @Test
    public void setOption() throws IOException {
        SocketChannel s = SocketChannel.open(new InetSocketAddress(6666));
        s.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
    }

    @Test
    public void shutdownInput() throws IOException {
        SocketChannel s = SocketChannel.open(new InetSocketAddress(6666));
        s.shutdownInput();
    }

    @Test
    public void shutdownOutput() throws IOException {
        SocketChannel s = SocketChannel.open(new InetSocketAddress(6666));
        s.shutdownOutput();
    }

    @Test
    public void socket() throws IOException {
        SocketChannel s = SocketChannel.open(new InetSocketAddress(6666));
        Socket socket = s.socket();
    }

    @Test
    public void isConnected() throws IOException {
        SocketChannel s = SocketChannel.open(new InetSocketAddress(6666));
        boolean c = s.isConnected();
    }

    @Test
    public void isConnectionPending() throws IOException {
        SocketChannel s = SocketChannel.open(new InetSocketAddress(6666));
        boolean c = s.isConnectionPending();
    }

    @Test
    public void connect() throws IOException {
        InetSocketAddress ad = new InetSocketAddress(6666);
        SocketChannel s = SocketChannel.open();
        boolean c = s.connect(ad);
    }

    @Test
    public void finishConnect() throws IOException {
        SocketChannel s = SocketChannel.open(new InetSocketAddress(6666));
        boolean c = s.finishConnect();
    }

    @Test
    public void getRemoteAddress() throws IOException {
        SocketChannel s = SocketChannel.open(new InetSocketAddress(6666));
        SocketAddress rad = s.getRemoteAddress();
    }

    @Test
    public void read() throws IOException {
        SocketChannel s = SocketChannel.open(new InetSocketAddress(6666));
        ByteBuffer bb = ByteBuffer.allocate(12);
        s.read(bb);
        s.shutdownOutput();
    }

    @Test
    public void read2() throws IOException {
        InetSocketAddress ad = new InetSocketAddress(6666);
        SocketChannel s = SocketChannel.open(ad);

        ByteBuffer bb1 = ByteBuffer.allocate(2);
        ByteBuffer bb2 = ByteBuffer.allocate(2);
        ByteBuffer[] bbs = {bb1, bb2};
        s.read(bbs, 0, bbs.length);

        s.shutdownOutput();
    }

    @Test
    public void write() throws IOException {
        SocketChannel s = SocketChannel.open(new InetSocketAddress(6666));
        ByteBuffer bb = ByteBuffer.allocate(4).put(new byte[]{99, 100, 101, 102});
        bb.flip();
        s.write(bb);
        s.shutdownOutput();
    }

    @Test
    public void getLocalAddress() throws IOException {
        SocketChannel s = SocketChannel.open(new InetSocketAddress(6666));
        SocketAddress sad = s.getLocalAddress();
    }


}
