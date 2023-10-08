package com.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ByteArrayInputStreamTest {
    @Test
    public void cons() throws IOException {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        byte[] buf = new byte[12];
        in.read(buf);
        Assert.assertEquals(97, buf[0]);
        Assert.assertEquals(98, buf[1]);
        Assert.assertEquals(99, buf[2]);
        Assert.assertEquals(0, buf[3]);
        Assert.assertEquals(0, buf[4]);
        Assert.assertEquals(0, buf[5]);
        Assert.assertEquals(0, buf[6]);
    }

    @Test
    public void cons2() throws IOException {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes, 1, 2);


        byte[] buf = new byte[12];
        in.read(buf);
        Assert.assertEquals(98, buf[0]);
        Assert.assertEquals(99, buf[1]);
    }

    @Test
    public void read() {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        Assert.assertEquals(97, in.read());
        Assert.assertEquals(98, in.read());
        Assert.assertEquals(99, in.read());
        Assert.assertEquals(0, in.read());
        Assert.assertEquals(0, in.read());
        Assert.assertEquals(0, in.read());
        Assert.assertEquals(0, in.read());
    }

    @Test
    public void readBufOff() {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        byte[] buf = new byte[12];
        in.read(buf, 1, 3);

        Assert.assertEquals(0, buf[0]);
        Assert.assertEquals(97, buf[1]);
        Assert.assertEquals(98, buf[2]);
        Assert.assertEquals(99, buf[3]);
    }

    @Test
    public void skip() {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        in.skip(2L);

        Assert.assertEquals(99, in.read());
        Assert.assertEquals(0, in.read());
        Assert.assertEquals(0, in.read());
    }

    @Test
    public void available() {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        Assert.assertEquals(7, in.available());
        in.read();
        Assert.assertEquals(6, in.available());

    }

    @Test
    public void mark() {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        // ?
        in.mark(2);
        int r2 = in.read();
        int r3 = in.read();
        int r4 = in.read();
    }

    @Test
    public void reset() {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        Assert.assertEquals(97, in.read());
        in.reset();
        Assert.assertEquals(97, in.read());
        Assert.assertEquals(98, in.read());
        in.reset();
        Assert.assertEquals(97, in.read());
    }

    @Test
    public void close() throws IOException {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        in.close();
    }
}
