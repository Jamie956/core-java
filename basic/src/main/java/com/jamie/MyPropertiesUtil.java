package com.jamie;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 读取资源文件工具，打包后可以读
 */
public class MyPropertiesUtil {
    public static void main(String[] args) throws IOException {
        Properties properties = load("test.properties");
        System.out.println(properties.getProperty("user.name"));
    }

    public static Properties load(String properieName) throws IOException {
        Properties properties = new Properties();
        properties.load(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(properieName), "UTF-8"));
        return properties;
    }
}
