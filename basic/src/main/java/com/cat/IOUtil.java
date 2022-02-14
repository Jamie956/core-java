package com.cat;

import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;

public class IOUtil {
    /**
     * 文件 转 数组字节流
     * 文件 -> 文件输入流 -> 字节数组输出流 -> 字节数组
     */
    @Test
    public void file2ByteArray() {
        try (FileInputStream inputStream = new FileInputStream(new File("src/main/resources/source"));
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
            byte[] bytes = byteArrayOutputStream.toByteArray();
            String str = new String(bytes);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节数组转文件
     * 字节数组 -> 文件输出流 -> 文件
     */
    @Test
    public void writeBytesToFile() {
        try (OutputStream out = new FileOutputStream("src/main/resources/output")) {
            out.write(new byte[]{65, 66});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件字符输入流 -> 字符数组输出流
     */
    @Test
    public void fileCharBuffer() {
        try (FileReader fileReader = new FileReader(new File("src/main/resources/source"));
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
     * 字符串 -> FileWriter -> 文件
     */
    @Test
    public void writeContent2File() {
        try (Writer out = new FileWriter("src/main/resources/output")) {
            out.write("长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串长字符串");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件
     * 文件 -> FileReader -> FileWriter -> 复制的文件
     */
    @Test
    public void copyFile() {
        try (Reader reader = new FileReader("src/main/resources/source");
             Writer writer = new FileWriter("src/main/resources/source_cp")) {
            char[] buffer = new char[1024];
            while (true) {
                int rendLength = reader.read(buffer);
                if (rendLength != -1) {
                    writer.write(buffer, 0, rendLength);
                } else {
                    break;
                }
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节流
     * 源文件 -> 文件输入流 -> 文件输出流 -> 目标文件
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
     * 文件输入流读取 data.json
     */
    @Test
    public void getMockData() {
        try (InputStream inputStream = new FileInputStream("data.json");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.defaultCharset())
        ) {
            StringBuilder sb = new StringBuilder();
            char[] buffer = new char[1024];
            int len = 0;
            while ((len = inputStreamReader.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, len));
            }
//            JSONObject json = JSON.parseObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
