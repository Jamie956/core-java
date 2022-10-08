package com.cat;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Properties;

/**
 * 读取资源文件工具，打包后可以读
 */
public class ResourceUtil {
    /**
     * 从线程加载器读取资源文件
     * @param fileName 文件名
     * @return 资源对象
     * @throws IOException IO
     */
    public static Properties load(String fileName) throws IOException {
        Properties properties = new Properties();
        properties.load(new InputStreamReader(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)), Charset.defaultCharset()));
        return properties;
    }
    @Test
    public void test0() throws IOException {
        Properties properties = load("test.properties");
        System.out.println(properties.getProperty("user.name"));
    }

}
