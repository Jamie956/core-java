package com.io;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileInputStreamTest {
    @Before
    public void initFile() throws IOException {
        FileOutputStream fileOut = new FileOutputStream("src/main/resources/source");
        fileOut.write(97);
        fileOut.write(98);
        fileOut.write(99);
        fileOut.write(100);
    }

    @Test
    public void cons() throws IOException {
        FileInputStream in = new FileInputStream("src/main/resources/source");

        Assert.assertEquals(97, in.read());
        Assert.assertEquals(98, in.read());
    }

    @Test
    public void cons2() throws IOException {
        File f = new File("src/main/resources/source");
        FileInputStream in = new FileInputStream(f);

        Assert.assertEquals(97, in.read());
        Assert.assertEquals(98, in.read());
    }

    @Test
    public void cons3() throws FileNotFoundException {
        FileDescriptor fd = new FileDescriptor();
        FileInputStream in = new FileInputStream(fd);
    }

    @Test
    public void readBuf() throws IOException {
        File f = new File("src/main/resources/source");
        FileInputStream in = new FileInputStream(f);
        byte[] bs = new byte[12];
        int read = in.read(bs);

        Assert.assertEquals(2, read);
        Assert.assertEquals(97, bs[0]);
        Assert.assertEquals(98, bs[1]);
    }

    @Test
    public void readBufOff() throws IOException {
        FileInputStream in = new FileInputStream("src/main/resources/source");
        byte[] bs = new byte[12];
        int read = in.read(bs, 2, 2);

        Assert.assertEquals(2, read);
        Assert.assertEquals(97, bs[2]);
        Assert.assertEquals(98, bs[3]);
    }

    @Test
    public void skip() throws IOException {
        FileInputStream in = new FileInputStream("src/main/resources/source");
        long skip = in.skip(1L);

        Assert.assertEquals(1, skip);
        Assert.assertEquals(98, in.read());
        Assert.assertEquals(99, in.read());
    }

    @Test
    public void available() throws IOException {
        FileInputStream in = new FileInputStream("src/main/resources/source");

        Assert.assertEquals(4, in.available());
        in.read();
        Assert.assertEquals(3, in.available());
    }

    @Test
    public void close() throws IOException {
        FileInputStream in = new FileInputStream("src/main/resources/source");
        in.close();
    }

    @Test
    public void getFD() throws IOException {
        FileInputStream in = new FileInputStream("src/main/resources/source");
        FileDescriptor fd = in.getFD();
    }

    @Test
    public void getChannel() throws IOException {
        FileInputStream in = new FileInputStream("src/main/resources/source");
        FileChannel fc = in.getChannel();
    }

}
