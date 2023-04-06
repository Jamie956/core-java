package com.example.jdk.io;

import org.junit.Test;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileInputStreamTest {
    @Test
    public void cons() throws FileNotFoundException {
        FileInputStream in = new FileInputStream("resources/source");
    }

    @Test
    public void cons2() throws FileNotFoundException {
        File f = new File("resources/source");
        FileInputStream in = new FileInputStream(f);
    }

    @Test
    public void cons3() throws FileNotFoundException {
        FileDescriptor fd = new FileDescriptor();
        FileInputStream in = new FileInputStream(fd);
    }

    @Test
    public void read() throws IOException {
        FileInputStream in = new FileInputStream("resources/source");
        int read1 = in.read();
        int read2 = in.read();
        int read3 = in.read();
        int read4 = in.read();
    }

    @Test
    public void readBuf() throws IOException {
        File f = new File("resources/source");
        FileInputStream in = new FileInputStream(f);
        byte[] bs = new byte[1024];
        int read = in.read(bs);
    }

    @Test
    public void readBufOff() throws IOException {
        FileInputStream in = new FileInputStream("resources/source");
        byte[] bs = new byte[1024];
        int read = in.read(bs, 2, 2);
    }

    @Test
    public void skip() throws IOException {
        FileInputStream in = new FileInputStream("resources/source");
        long skip = in.skip(1L);

        int r1 = in.read();
        int r2 = in.read();
        int r3 = in.read();
    }

    @Test
    public void available() throws IOException {
        FileInputStream in = new FileInputStream("resources/source");
        int i = in.available();
        int r1 = in.read();
        int i1 = in.available();
    }

    @Test
    public void close() throws IOException {
        FileInputStream in = new FileInputStream("resources/source");
        in.close();
    }

    @Test
    public void getFD() throws IOException {
        FileInputStream in = new FileInputStream("resources/source");
        FileDescriptor fd = in.getFD();
    }

    @Test
    public void getChannel() throws IOException {
        FileInputStream in = new FileInputStream("resources/source");
        FileChannel fc = in.getChannel();
    }

}
