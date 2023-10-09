package com.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileOutputStreamTest {
    @Test
    public void cons() throws FileNotFoundException {
        FileOutputStream fo = new FileOutputStream("src/main/resources/source");
    }

    @Test
    public void cons2() throws FileNotFoundException {
        FileOutputStream fo = new FileOutputStream("src/main/resources/source", true);
    }

    @Test
    public void cons3() throws FileNotFoundException {
        FileOutputStream fo = new FileOutputStream(new File("src/main/resources/source"));
    }

    @Test
    public void cons4() throws IOException {
        FileOutputStream fo = new FileOutputStream(new File("src/main/resources/source"), true);
        fo.write(97);

        Assert.assertEquals(97, new FileInputStream("src/main/resources/source").read());
    }

    @Test
    public void cons5() {
        FileOutputStream fo = new FileOutputStream(new FileDescriptor());
    }

    @Test
    public void write() throws IOException {
        FileOutputStream fo = new FileOutputStream("src/main/resources/source");
        fo.write(97);

        Assert.assertEquals(97, new FileInputStream("src/main/resources/source").read());
    }

    @Test
    public void writeBytes() throws IOException {
        FileOutputStream fo = new FileOutputStream("src/main/resources/source");
        byte[] bytes = new byte[]{97, 98};
        fo.write(bytes);

        FileInputStream in = new FileInputStream("src/main/resources/source");
        Assert.assertEquals(97, in.read());
        Assert.assertEquals(98, in.read());
    }

    @Test
    public void writeBytesOff() throws IOException {
        FileOutputStream fo = new FileOutputStream("src/main/resources/source");
        byte[] bytes = new byte[]{97, 98, 98, 98};
        fo.write(bytes, 1, 2);

        FileInputStream in = new FileInputStream("src/main/resources/source");
        Assert.assertEquals(98, in.read());
        Assert.assertEquals(98, in.read());
    }

    @Test
    public void close() throws IOException {
        FileOutputStream fo = new FileOutputStream("src/main/resources/source");
        fo.close();
    }

    @Test
    public void getFD() throws IOException {
        FileOutputStream fo = new FileOutputStream("src/main/resources/source");
        FileDescriptor fd = fo.getFD();
    }

    @Test
    public void getChannel() throws IOException {
        FileOutputStream fo = new FileOutputStream("src/main/resources/source");
        FileChannel channel = fo.getChannel();
    }
}
