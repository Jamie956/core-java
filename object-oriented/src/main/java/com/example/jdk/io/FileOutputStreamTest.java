package com.example.jdk.io;

import org.junit.Test;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileOutputStreamTest {
    @Test
    public void cons() throws FileNotFoundException {
        FileOutputStream fo = new FileOutputStream("resources/source");
    }

    @Test
    public void cons2() throws FileNotFoundException {
        FileOutputStream fo = new FileOutputStream("resources/source", true);
    }

    @Test
    public void cons3() throws FileNotFoundException {
        FileOutputStream fo = new FileOutputStream(new File("resources/source"));
    }

    @Test
    public void cons4() throws IOException {
        FileOutputStream fo = new FileOutputStream(new File("resources/source"), true);
        fo.write(97);
    }

    @Test
    public void cons5() {
        FileOutputStream fo = new FileOutputStream(new FileDescriptor());
    }

    @Test
    public void write() throws IOException {
        FileOutputStream fo = new FileOutputStream("resources/source");
        fo.write(97);
    }

    @Test
    public void writeBytes() throws IOException {
        FileOutputStream fo = new FileOutputStream("resources/source");
        byte[] bytes = new byte[]{97, 98};
        fo.write(bytes);
    }

    @Test
    public void writeBytesOff() throws IOException {
        FileOutputStream fo = new FileOutputStream("resources/source");
        byte[] bytes = new byte[]{97, 98, 98, 98};
        fo.write(bytes, 1, 2);
    }

    @Test
    public void close() throws IOException {
        FileOutputStream fo = new FileOutputStream("resources/source");
        fo.close();
    }

    @Test
    public void getFD() throws IOException {
        FileOutputStream fo = new FileOutputStream("resources/source");
        FileDescriptor fd = fo.getFD();
    }

    @Test
    public void getChannel() throws IOException {
        FileOutputStream fo = new FileOutputStream("resources/source");
        FileChannel channel = fo.getChannel();
    }
}
