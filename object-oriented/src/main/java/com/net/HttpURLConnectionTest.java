package com.net;

import org.junit.Test;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
}
