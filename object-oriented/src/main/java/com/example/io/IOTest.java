package com.example.io;

import org.junit.Test;
import java.io.*;

public class IOTest {
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
            String str = byteArrayOutputStream.toString();
            System.out.println(str);
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
            System.out.println(charArrayWriter.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件 Writer 输出流
     */
    @Test
    public void fileChar() {
        try (Writer out = new FileWriter("src/main/resources/output")) {
            out.write("长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串");
            out.flush();
        } catch (IOException e) {
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

    /**
     * 字节数组 -> 文件输出流
     */
    @Test
    public void fileOutputStream() {
        try (OutputStream out = new FileOutputStream("src/main/resources/output")) {
            out.write(new byte[]{65, 66});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
