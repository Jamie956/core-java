package com.io;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputStreamWriterTest {
    @Test
    public void cons() throws FileNotFoundException {
        FileOutputStream fo = new FileOutputStream("resources/source");
        OutputStreamWriter o = new OutputStreamWriter(fo);
    }

    @Test
    public void encoding() throws FileNotFoundException {
        FileOutputStream fo = new FileOutputStream("resources/source");
        OutputStreamWriter o = new OutputStreamWriter(fo);
        String encoding = o.getEncoding();
    }

    @Test
    public void write() throws IOException {
        FileOutputStream fo = new FileOutputStream("resources/source");
        OutputStreamWriter o = new OutputStreamWriter(fo);
        o.write(97);
        o.flush();
    }

    @Test
    public void writeBuf() throws IOException {
        FileOutputStream fo = new FileOutputStream("resources/source");
        OutputStreamWriter o = new OutputStreamWriter(fo);
        char[] buf = new char[]{97, 98, 99};
        o.write(buf, 1, 2);
        o.flush();
    }

    @Test
    public void writeStr() throws IOException {
        FileOutputStream fo = new FileOutputStream("resources/source");
        OutputStreamWriter o = new OutputStreamWriter(fo);
        o.write("abc", 1, 2);
        o.flush();
    }

    @Test
    public void flush() throws IOException {
        FileOutputStream fo = new FileOutputStream("resources/source");
        OutputStreamWriter o = new OutputStreamWriter(fo);
        o.flush();
    }
}
