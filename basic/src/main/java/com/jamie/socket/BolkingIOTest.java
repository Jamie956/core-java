package com.jamie.socket;

import com.jamie.concurrency.ThreadUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BolkingIOTest {
    /**
     * 阻塞IO，服务端
     * 终端连接服务端：telnet 127.0.0.1 6666
     */
    @Test
    public void server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);

        //accept 阻塞监听 客户端连接
        while (true) {
            System.out.println("等待下一个客户端连接...");
            Socket socket = serverSocket.accept();
            System.out.println("连接成功!");

            ThreadUtil.execute(() -> {
                System.out.println("分配线程与新连接通信 " + Thread.currentThread().getName());

                //获取socket 输入流
                try (InputStream in = socket.getInputStream()) {
                    byte[] bytes = new byte[1024];
                    while (true) {
                        int length = in.read(bytes);
                        if (length != -1) {
                            System.out.println(new String(bytes, 0, length));
                        } else {
                            break;
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    @Test
    public void client() throws IOException {
        Socket socket = new Socket("127.0.0.1", 6666);
        while (true) {

        }
    }





    @Test
    public void notBlockingServer() {
        try {
            ServerSocket ss = new ServerSocket(8081);

            while (true) {
                Socket socket = ss.accept();
                OutputStream out = socket.getOutputStream();
                out.write("hi".getBytes());

                out.close();
                socket.close();
            }

//            ss.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notBlockingClient() {
        try {
            Socket socket = new Socket("127.0.0.1", 8081);
            InputStream in = socket.getInputStream();
            Scanner scan = new Scanner(in);

            while (scan.hasNext()) {
                System.out.println(scan.next());
            }

            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
