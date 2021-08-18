package com.jamie.io;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class IOTest {

    public static void main(String[] args) {
//        writerIO("./myf.txt", "hi world");
//        cpFile("./myf.txt", "./cpmyf.txt");
    }

    /**
     * 文件 转 数组字节流
     * 文件 -> 文件输入流 -> 字节数组输出流 -> 字节数组
     */
    public static byte[] file2ByteArray(String filePath) {
        try (FileInputStream in = new FileInputStream(new File(filePath)); ByteArrayOutputStream bao = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            while (true) {
                int len = in.read(buf);
                if (len != -1) {
                    bao.write(buf, 0, len);
                } else {
                    break;
                }
            }
            return bao.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符 -> FileWriter -> 文件
     *
     * @param fileName 路径
     * @param content  内容
     */
    public static void writeContent2File(String fileName, String content) {
        try (Writer out = new FileWriter(fileName)) {
            out.write(content);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件
     * 文件 -> FileReader -> FileWriter -> cp文件
     * @param srcFile  源文件
     * @param destFile 目标文件
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
     * 字节数组转文件
     * 字节数组 -> 文件输出流 -> 文件
     */
    @Test
    public void byte2File(String fileName, byte[] bs) {
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
