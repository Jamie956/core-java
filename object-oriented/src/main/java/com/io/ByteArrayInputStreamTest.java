package com.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ByteArrayInputStreamTest {
    @Test
    public void cons() {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
    }

    @Test
    public void cons2() {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes, 1, 2);
    }

    @Test
    public void read() {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        int r1 = in.read();
        int r2 = in.read();
        int r3 = in.read();
        int r4 = in.read();
    }

    @Test
    public void readBufOff() {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        byte[] buf = new byte[1024];
        int r1 = in.read(buf, 1, 3);
    }

    @Test
    public void skip() {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        in.skip(2L);

        int r1 = in.read();
        int r2 = in.read();
        int r3 = in.read();
        int r4 = in.read();
    }

    @Test
    public void available() {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        int r1 = in.read();
        int a = in.available();
        int r2 = in.read();
        int r3 = in.read();
        int r4 = in.read();
    }

    @Test
    public void mark() {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        in.mark(2);
        int r2 = in.read();
        int r3 = in.read();
        int r4 = in.read();
    }

    @Test
    public void reset() {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        int r2 = in.read();
        in.reset();
        int r3 = in.read();
        int r4 = in.read();
    }

    @Test
    public void close() throws IOException {
        byte[] bytes = new byte[]{97, 98, 99, 0, 0, 0, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        in.close();
    }
}
