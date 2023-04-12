package com.example;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Assert;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class StringTemplateTest {
    public static void main(String[] args) throws IOException, TemplateException {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("name", "tim");

        // load template
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate("myTemplate", "halo：${name}");
        Configuration conf = new Configuration();
        conf.setTemplateLoader(stringLoader);
        // get template and write to stream with data
        Template template = conf.getTemplate("myTemplate", "utf-8");
        StringWriter writer = new StringWriter();
        template.process(dataMap, writer);
        Assert.assertEquals("halo：tim", writer.toString());
    }
}
