package com.io;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.*;

public class InputOutputTest {
    @Before
    public void initFile() throws IOException {
        FileOutputStream fileOut = new FileOutputStream("src/main/resources/source");
        fileOut.write(97);
        fileOut.write(98);
        fileOut.write(99);
        fileOut.write(100);

        fileOut.write("对唔海珠".getBytes());
    }

    /**
     * 文件输入流写 -> 字节数组输出流
     */
    @Test
    public void fileStream2ByteStream() {
        try (FileInputStream inputStream = new FileInputStream("src/main/resources/source");
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            while (true) {
                int readLength = inputStream.read(buffer);
                if (readLength != -1) {
                    byteArrayOutputStream.write(buffer, 0, readLength);
                } else {
                    break;
                }
            }
            Assert.assertEquals("abcd对唔海珠", byteArrayOutputStream.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 文件 Reader 输入流 -> 字符数组输出流
     */
    @Test
    public void fileChar2CharArray() {
        try (FileReader fileReader = new FileReader("src/main/resources/source");
             CharArrayWriter charArrayWriter = new CharArrayWriter()) {
            char[] buffer = new char[1024];
            while (true) {
                int readLength = fileReader.read(buffer);
                if (readLength != -1) {
                    charArrayWriter.write(buffer, 0, readLength);
                } else {
                    break;
                }
            }
            Assert.assertEquals("abcd对唔海珠", charArrayWriter.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件输入流 -> 文件输出流
     */
    @Test
    public void fileStream() {
        try (InputStream in = new FileInputStream("src/main/resources/source");
             OutputStream out = new FileOutputStream("src/main/resources/source_cp")) {
            byte[] b = new byte[1024];
            while (true) {
                int len = in.read(b);
                if (len != -1) {
                    out.write(b, 0, len);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
