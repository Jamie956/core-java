package com.example.jdk.net;

import org.junit.Test;

import java.io.IOException;
import java.net.*;
import java.nio.channels.ServerSocketChannel;

public class ServerSocketTest {
    @Test
    public void cons() throws IOException {
        ServerSocket ss = new ServerSocket();
    }

    @Test
    public void cons3() throws IOException {
        ServerSocket ss = new ServerSocket(8989, 3, null);
    }
//
    @Test
    public void cons4() throws IOException {
        ServerSocket ss = new ServerSocket(8989, 3, InetAddress.getByName("127.0.0.1"));
    }

    @Test
    public void bind() throws IOException {
        ServerSocket ss = new ServerSocket();
        InetSocketAddress add = new InetSocketAddress(8989);
        ss.bind(add, 50);
    }

    @Test
    public void getInetAddress() throws IOException {
        ServerSocket ss = new ServerSocket(8989);
        InetAddress inetAddress = ss.getInetAddress();
    }

    @Test
    public void getLocalPort() throws IOException {
        ServerSocket ss = new ServerSocket(8989);
        int localPort = ss.getLocalPort();
    }

    @Test
    public void getLocalSocketAddress() throws IOException {
        ServerSocket ss = new ServerSocket(8989);
        SocketAddress localSocketAddress = ss.getLocalSocketAddress();
    }

    @Test
    public void accept() throws IOException {
        ServerSocket ss = new ServerSocket(8989);
        Socket accept = ss.accept();
    }

    @Test
    public void close() throws IOException {
        ServerSocket ss = new ServerSocket(8989);
        ss.close();
    }

    @Test
    public void getChannel() throws IOException {
        ServerSocket ss = new ServerSocket(8989);
        ServerSocketChannel channel = ss.getChannel();
    }

    @Test
    public void isBound() throws IOException {
        ServerSocket ss = new ServerSocket(8989);
        boolean b = ss.isBound();
    }

    @Test
    public void isClosed() throws IOException {
        ServerSocket ss = new ServerSocket(8989);
        boolean b = ss.isClosed();
    }

    @Test
    public void setSoTimeout() throws IOException {
        ServerSocket ss = new ServerSocket(8989);
        ss.setSoTimeout(100);
    }

    @Test
    public void getSoTimeout() throws IOException {
        ServerSocket ss = new ServerSocket(8989);
        int soTimeout = ss.getSoTimeout();
    }

    @Test
    public void setReuseAddress() throws IOException {
        ServerSocket ss = new ServerSocket(8989);
        ss.setReuseAddress(true);
    }

    @Test
    public void getReuseAddress() throws IOException {
        ServerSocket ss = new ServerSocket(8989);
        boolean reuseAddress = ss.getReuseAddress();
    }

    @Test
    public void setSocketFactory() throws IOException {
        ServerSocket ss = new ServerSocket(8989);
//         ss.setSocketFactory();
    }

    @Test
    public void setReceiveBufferSize() throws IOException {
        ServerSocket ss = new ServerSocket(8989);
        ss.setReceiveBufferSize(10);
    }

    @Test
    public void getReceiveBufferSize() throws IOException {
        ServerSocket ss = new ServerSocket(8989);
        int receiveBufferSize = ss.getReceiveBufferSize();
    }


}
