package com.example.jdk.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelTest {
    @Test
    public void open() throws IOException {
        ServerSocketChannel s = ServerSocketChannel.open();
    }

    @Test
    public void validOps() throws IOException {
        ServerSocketChannel s = ServerSocketChannel.open();
        int v = s.validOps();
    }

    @Test
    public void bind() throws IOException {
        ServerSocketChannel s = ServerSocketChannel.open();
        InetSocketAddress ad = new InetSocketAddress(6666);
        s.bind(ad, 0);
    }

    @Test
    public void setOption() throws IOException {
        ServerSocketChannel s = ServerSocketChannel.open();
        s.setOption(StandardSocketOptions.SO_RCVBUF, 3);
    }

    @Test
    public void socket() throws IOException {
        ServerSocketChannel s = ServerSocketChannel.open();
        ServerSocket ss = s.socket();
    }

    @Test
    public void accept() throws IOException {
        ServerSocketChannel s = ServerSocketChannel.open();
        InetSocketAddress ad = new InetSocketAddress(6666);
        s.bind(ad, 0);
        SocketChannel c = s.accept();
    }

    @Test
    public void acceptClient() throws IOException {
        SocketChannel s = SocketChannel.open(new InetSocketAddress(6666));
        s.write(ByteBuffer.allocate(6).put(new byte[]{97, 98}));
    }

    @Test
    public void getLocalAddress() throws IOException {
        ServerSocketChannel s = ServerSocketChannel.open();
        InetSocketAddress ad = new InetSocketAddress(6666);
        s.bind(ad, 0);
        SocketAddress a = s.getLocalAddress();
    }


}
