package com.example.jdk.net;

import org.junit.Test;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

public class URLTest {
    @Test
    public void cons1() throws MalformedURLException {
        URL u = new URL("https://www.baidu.com/");
    }

    @Test
    public void cons2() throws MalformedURLException {
        URL u = new URL("   https://www.baidu.com/    ");
    }

    @Test
    public void getDefaultPort() throws MalformedURLException {
        URL u = new URL("https://www.baidu.com");
        int p = u.getDefaultPort();
    }

    @Test
    public void equals() throws MalformedURLException {
        URL a = new URL("https://www.baidu.com");
        URL b = new URL("https://www.baidu.com#aa");
        boolean c = a.equals(b);
    }

    @Test
    public void hashCode1() throws MalformedURLException {
        URL a = new URL("https://www.baidu.com");
        int h = a.hashCode();
    }

    @Test
    public void sameFile() throws MalformedURLException {
        URL a = new URL("https://www.baidu.com");
        URL b = new URL("https://www.baidu.com#aa");
        boolean c = a.sameFile(b);
    }

    @Test
    public void toString1() throws MalformedURLException {
        URL a = new URL("https://www.baidu.com");
        String s = a.toString();
    }

    @Test
    public void toURI() throws MalformedURLException, URISyntaxException {
        URL a = new URL("https://www.baidu.com");
        URI s = a.toURI();
    }

    @Test
    public void openConnection() throws IOException {
        URL a = new URL("https://www.baidu.com");
        URLConnection c = a.openConnection();
    }

    @Test
    public void openConnection2() throws IOException {
        URL a = new URL("https://www.baidu.com");
        //?
        URLConnection c = a.openConnection(new Proxy(Proxy.Type.DIRECT, null));
    }

    @Test
    public void openStream() throws IOException {
        URL a = new URL("https://www.baidu.com");
        InputStream in = a.openStream();
        InputStreamReader inr = new InputStreamReader(in, Charset.defaultCharset());
        BufferedReader br = new BufferedReader(inr);
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String s = sb.toString();
    }

    @Test
    public void getContent() throws IOException {
        URL a = new URL("https://www.baidu.com");
        InputStream in = (InputStream) a.getContent();

        byte[] buf = new byte[1024];
        int read = -1;
        StringBuilder sb = new StringBuilder();
        while ((read = in.read(buf)) != -1) {
            sb.append(new String(buf, 0, read, Charset.defaultCharset()));
        }
        String s = sb.toString();
    }

    @Test
    public void getContent2() throws IOException {
        URL a = new URL("https://www.baidu.com");
        Class[] cs = {InputStream.class};
        InputStream in = (InputStream) a.getContent(cs);

        byte[] buf = new byte[1024];
        int read = -1;
        StringBuilder sb = new StringBuilder();
        while ((read = in.read(buf)) != -1) {
            sb.append(new String(buf, 0, read, Charset.defaultCharset()));
        }
        String s = sb.toString();
    }

    @Test
    public void setURLStreamHandlerFactory() throws IOException {
        URL a = new URL("https://www.baidu.com");
        URL.setURLStreamHandlerFactory(null);
    }


    //--------------------- 场景模拟 ---------------------
    @Test
    public void get() {
        try {
            String link = "https://www.baidu.com/";
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, Charset.defaultCharset());
            BufferedReader br = new BufferedReader(isr);
            String str;
            StringBuilder sb = new StringBuilder();
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void post() {
        try {
            String path = "https://www.baidu.com/";
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(2000);
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            OutputStream outputStream = connection.getOutputStream();

            PrintWriter printWriter = new PrintWriter(outputStream);
            //post的参数 xx=xx&yy=yy
            printWriter.write("xx=xx&yy=yy");
            printWriter.flush();

            InputStream inputStream = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1) {
                bos.write(arr, 0, len);
                bos.flush();
            }
            System.out.println(bos.toString("utf-8"));
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
