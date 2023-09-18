package com.net;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTest {
    @Test
    public void server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        while (true) {
            Socket socket = serverSocket.accept();
        }
    }

    @Test
    public void cons() {
        Socket socket = new Socket();
    }

    @Test
    public void cons2() {
        Socket socket = new Socket(null);
    }

    @Test
    public void cons3() throws IOException {
        Socket socket = new Socket("127.0.0.1", 6666);
    }

    @Test
    public void cons4() throws IOException {
        Socket socket = new Socket(InetAddress.getByName(null), 6666);
    }

    @Test
    public void cons5() throws IOException {
        Socket socket = new Socket("127.0.0.1", 6666, InetAddress.getByName(null), 6666);
    }

    @Test
    public void connect() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 6666));
    }

    @Test
    public void connect2() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 6666), 1);
    }

    @Test
    public void bind() throws IOException {
        Socket socket = new Socket();
        socket.bind(new InetSocketAddress("127.0.0.1", 6666));
    }

    @Test
    public void getInetAddress() throws IOException {
        Socket socket = new Socket("127.0.0.1", 6666);
        InetAddress a = socket.getInetAddress();
    }



}
