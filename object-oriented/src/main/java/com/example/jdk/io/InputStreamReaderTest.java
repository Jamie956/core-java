package com.example.jdk.io;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class InputStreamReaderTest {
    @Test
    public void cons() throws FileNotFoundException {
        FileInputStream fi = new FileInputStream("resources/source");
        InputStreamReader isr = new InputStreamReader(fi);
    }

    @Test
    public void cons2() throws FileNotFoundException {
        FileInputStream fi = new FileInputStream("resources/source");
        InputStreamReader isr = new InputStreamReader(fi, Charset.defaultCharset());
    }

    @Test
    public void getEncoding() throws FileNotFoundException {
        FileInputStream fi = new FileInputStream("resources/source");
        InputStreamReader isr = new InputStreamReader(fi, Charset.defaultCharset());
        String encoding = isr.getEncoding();
    }

    @Test
    public void read() throws IOException {
        FileInputStream fi = new FileInputStream("resources/source");
        InputStreamReader isr = new InputStreamReader(fi, Charset.defaultCharset());
        int r1 = isr.read();
        int r2 = isr.read();
        int r3 = isr.read();
    }

    @Test
    public void readBuf() throws IOException {
        FileInputStream fi = new FileInputStream("resources/source");
        InputStreamReader isr = new InputStreamReader(fi, Charset.defaultCharset());
        char[] buf = new char[1024];
        int r = isr.read(buf, 1, 10);
    }

}
