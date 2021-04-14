package com.jamie.io;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class IOTest {

    public static void main(String[] args) {
        writerIO("./myf.txt", "hi world");
        cpFile("./myf.txt", "./cpmyf.txt");
    }

    /**
     * 字符流写出到文件
     *
     * @param fileName
     * @param content
     */
    public static void writerIO(String fileName, String content) {
        try (Writer out = new FileWriter(fileName)) {
            out.write(content);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符流 复制文件
     *
     * @param srcFile
     * @param destFile
     */
    public static void cpFile(String srcFile, String destFile) {
        try (Reader in = new FileReader(srcFile);
             Writer out = new FileWriter(destFile)) {

            char[] buf = new char[1024];
            while (true) {
                int len = in.read(buf);
                if (len != -1) {
                    out.write(buf, 0, len);
                } else {
                    break;
                }
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把字节写到文件
     * new byte[]{65, 66, 67}
     */
    @Test
    public void fileOut(String fileName, byte[] bs) {
        try (OutputStream out = new FileOutputStream(fileName)) {
            out.write(bs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节流
     *
     * @param srcFile
     * @param destFile
     */
    @Test
    public void fileIO(String srcFile, String destFile) {
        try (InputStream in = new FileInputStream(srcFile);
             OutputStream out = new FileOutputStream(destFile)) {

            //逐个字节写出
            while (true) {
                int b = in.read();
                if (b != -1) {
                    out.write(b);
                } else {
                    break;
                }
            }

            //buf 写出
            byte[] b = new byte[20];
            while (true) {
                int len = in.read(b);
                if (len != -1) {
                    out.write(b, 0, len);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
