package com.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayOutputStreamTest {
    @Test
    public void cons() {
        ByteArrayOutputStream o = new ByteArrayOutputStream();
    }

    @Test
    public void cons2() {
        ByteArrayOutputStream o = new ByteArrayOutputStream(16);
    }

    @Test
    public void write() {
        ByteArrayOutputStream o = new ByteArrayOutputStream(2);
        o.write(97);
        o.write(97);
        o.write(97);

        Assert.assertEquals("aaa", o.toString());
    }

    @Test
    public void writeByte() {
        ByteArrayOutputStream o = new ByteArrayOutputStream(2);
        byte[] bytes = new byte[]{97, 98, 99, 100};
        o.write(bytes, 1, 3);

        Assert.assertEquals("bcd", o.toString());
    }

    @Test
    public void writeTo() throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        byteOut.write(98);
        FileOutputStream fileOut = new FileOutputStream("src/main/resources/source");
        byteOut.writeTo(fileOut);

        Assert.assertEquals(98, new FileInputStream("src/main/resources/source").read());
    }

    @Test
    public void reset() {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream(2);
        byteOut.write(98);
        byteOut.write(98);

        Assert.assertEquals(2, byteOut.size());
        Assert.assertEquals("bb", byteOut.toString());

        byteOut.reset();

        Assert.assertEquals(0, byteOut.size());
        Assert.assertEquals("", byteOut.toString());
    }

    @Test
    public void toByteArray() {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream(2);
        byteOut.write(97);
        byteOut.write(97);
        byte[] bytes = byteOut.toByteArray();

        Assert.assertEquals(97, bytes[0]);
        Assert.assertEquals(97, bytes[1]);
    }

}
