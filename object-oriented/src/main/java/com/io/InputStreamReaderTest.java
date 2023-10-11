package com.io;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static java.nio.charset.Charset.defaultCharset;

public class InputStreamReaderTest {
    @Before
    public void initFile() throws IOException {
        FileOutputStream fileOut = new FileOutputStream("src/main/resources/source");
        fileOut.write(97);
        fileOut.write(98);
        fileOut.write(99);
        fileOut.write(100);

        fileOut.write("对唔海珠".getBytes());
    }

    @Test
    public void cons() throws IOException {
        FileInputStream fi = new FileInputStream("src/main/resources/source");
        InputStreamReader isr = new InputStreamReader(fi);

        Assert.assertEquals(97, isr.read());
        Assert.assertEquals(98, isr.read());
        Assert.assertEquals(99, isr.read());
        Assert.assertEquals(100, isr.read());
    }

    @Test
    public void cons2() throws IOException {
        FileInputStream fi = new FileInputStream("src/main/resources/source");
        InputStreamReader isr = new InputStreamReader(fi, defaultCharset());

        char[] buf = new char[128];
        isr.read(buf);
        Assert.assertEquals("abcd对唔海珠", new String(buf).substring(0, 8));

    }

    @Test
    public void getEncoding() throws FileNotFoundException {
        FileInputStream fi = new FileInputStream("src/main/resources/source");
        InputStreamReader isr = new InputStreamReader(fi, defaultCharset());
        String encoding = isr.getEncoding();
        Assert.assertEquals("UTF8", encoding);
    }

    @Test
    public void readBuf() throws IOException {
        FileInputStream fi = new FileInputStream("src/main/resources/source");
        InputStreamReader isr = new InputStreamReader(fi, defaultCharset());
        char[] buf = new char[12];
        isr.read(buf, 1, 3);

        Assert.assertEquals(97, buf[1]);
        Assert.assertEquals(98, buf[2]);
        Assert.assertEquals(99, buf[3]);
    }

}
