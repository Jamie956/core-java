package com.net;

import org.junit.Test;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.Charset;

public class HttpURLConnectionTest {
    @Test
    public void setRequestMethod() throws IOException {
        URL a = new URL("https://www.baidu.com");
        HttpURLConnection c = new HttpURLConnection(a, null);
        c.setRequestMethod("get");
    }

    @Test
    public void getHeaderField() throws IOException {
        URL a = new URL("https://www.baidu.com");
        HttpURLConnection c = new HttpURLConnection(a, null);
        for (int i = 0; i < 10; i++) {
            String f = c.getHeaderField(i);
            System.out.println(f);
        }
    }

    @Test
    public void getHeaderFieldKey() throws IOException {
        URL a = new URL("https://www.baidu.com");
        HttpURLConnection c = new HttpURLConnection(a, null);
        for (int i = 0; i < 10; i++) {
            String f = c.getHeaderFieldKey(i);
            System.out.println(f);
        }
    }

    @Test
    public void connect() throws IOException {
        URL a = new URL("https://www.baidu.com");
        HttpURLConnection c = new HttpURLConnection(a, null);
        c.connect();
    }

    @Test
    public void getOutputStream() throws IOException {
        URL a = new URL("https://www.baidu.com");
        HttpURLConnection c = new HttpURLConnection(a, null);
        c.setDoOutput(true);
        OutputStream out = c.getOutputStream();
    }

    @Test
    public void getInputStream() throws IOException {
        URL a = new URL("https://www.baidu.com");

        HttpURLConnection c = new HttpURLConnection(a, null);
        InputStream in = c.getInputStream();

        byte[] buf = new byte[1024];
        int read = -1;
        StringBuilder sb = new StringBuilder();
        while ((read = in.read(buf)) != -1) {
            sb.append(new String(buf, 0, read, Charset.defaultCharset()));
        }
        String s = sb.toString();
    }

    public static class ServerSocketTest {
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
}
