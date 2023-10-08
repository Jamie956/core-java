package com.io;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest {
    /**
     * 阻塞IO，服务端
     * 终端连接服务端：telnet 127.0.0.1 6666
     */
    @Test
    public void server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("等待下一个客户端连接...");
        //accept 阻塞线程，监听客户端连接
        Socket socket = serverSocket.accept();
        System.out.println("连接成功!");

        //等待客户端输入
        try (InputStream in = socket.getInputStream()) {
            byte[] bytes = new byte[1024];
            while (true) {
                int length = in.read(bytes);
                if (length != -1) {
                    System.out.println(new String(bytes, 0, length));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void client() throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 6666);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(new byte[]{56, 57});
        Thread.sleep(10000);
        outputStream.write(new byte[]{67, 68});
    }

}
