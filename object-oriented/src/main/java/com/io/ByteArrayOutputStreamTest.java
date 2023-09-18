package com.io;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
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
    }

    @Test
    public void writeByte() {
        ByteArrayOutputStream o = new ByteArrayOutputStream(2);
        byte[] bytes = new byte[]{97, 98, 99, 100};
        o.write(bytes, 1, 3);
    }

    @Test
    public void writeTo() throws IOException {
        ByteArrayOutputStream o = new ByteArrayOutputStream(2);
        FileOutputStream fo = new FileOutputStream("resources/source");
        o.writeTo(fo);
    }

    @Test
    public void reset() {
        ByteArrayOutputStream o = new ByteArrayOutputStream(2);
        o.reset();
    }

    @Test
    public void toByteArray() {
        ByteArrayOutputStream o = new ByteArrayOutputStream(2);
        o.write(97);
        o.write(97);
        byte[] bytes = o.toByteArray();
    }

    @Test
    public void toString1() {
        ByteArrayOutputStream o = new ByteArrayOutputStream(2);
        o.write(97);
        o.write(97);
        o.toString();
    }

    @Test
    public void toString2() throws IOException {
        ByteArrayOutputStream o = new ByteArrayOutputStream(2);
        o.write(97);
        o.write(97);
        o.toString("utf-8");
    }

}
