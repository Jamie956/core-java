package com.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Assert;

import java.io.File;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class FileTemplateDemo1 {
    public static void main(String[] args) throws ParseException {
        String dir = "src/main/java";
        String file = "com/example/template/demo1.ftl";
        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("name", "jamie");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dataMap.put("dateTime", sdf.parse("2022-02-02"));

        try {
            Configuration conf = new Configuration();
            conf.setDirectoryForTemplateLoading(new File(dir));

            Template template = conf.getTemplate(file);
            StringWriter writer = new StringWriter();
            template.process(dataMap, writer);
            String result = writer.toString();
            Assert.assertEquals("Hello jamie !\r\nHello jamie !\r\nm\r\njam\r\n2022-02-02", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
