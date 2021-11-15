package com.jamie.io;


import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class FileWriteTest {
    private static int COUNT = 10000;
    //    private String SRC_PATH = "src/main/resources";
    private String SRC_PATH = "basic/src/main/resources";

    /**
     * FileOutputStream执行耗时:26 毫秒
     * BufferedOutputStream执行耗时:2 毫秒
     * FileWriter执行耗时:7 毫秒
     */
    public static void main(String[] args) {
        FileWriteTest fileWriteTest = new FileWriteTest();
        fileWriteTest.fileOutputStreamNoBuffer();
        fileWriteTest.fileOutputStreamBuffer();
        fileWriteTest.fileWriter();
    }

    /**
     * FileOutputStream
     */
    @Test
    public void fileOutputStreamNoBuffer() {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(SRC_PATH + "/add1.txt"));
            long begin = System.currentTimeMillis();
            for (int i = 0; i < COUNT; i++) {
                out.write("测试java 文件操作\r\n".getBytes());
            }
            System.out.println("FileOutputStream执行耗时:" + (System.currentTimeMillis() - begin) + " 毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * FileOutputStream -> BufferedOutputStream
     */
    @Test
    public void fileOutputStreamBuffer() {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(SRC_PATH + "/add2.txt"));
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            long begin2 = System.currentTimeMillis();
            for (int i = 0; i < COUNT; i++) {
                bufferedOutputStream.write("测试java 文件操作\r\n".getBytes());
            }
            bufferedOutputStream.flush();
            System.out.println("BufferedOutputStream执行耗时:" + (System.currentTimeMillis() - begin2) + " 毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * FileWriter
     */
    @Test
    public void fileWriter() {
        FileWriter fw = null;
        try {
            fw = new FileWriter(SRC_PATH + "/add3.txt");
            long begin3 = System.currentTimeMillis();
            for (int i = 0; i < COUNT; i++) {
                fw.write("测试java 文件操作\r\n");
            }
            System.out.println("FileWriter执行耗时:" + (System.currentTimeMillis() - begin3) + " 毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

