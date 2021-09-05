package com.jamie.util;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
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

    /**
     * 读取资源文件
     * 用ClassPathResource 读取
     */
    public static Properties load2(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        InputStream in = resource.getInputStream();

        Properties p = new Properties();
        p.load(in);
        return p;
    }

    @Test
    public void test1() throws IOException {
        Properties properties = load2("test.properties");
        System.out.println(properties.getProperty("user.name"));
    }
}
