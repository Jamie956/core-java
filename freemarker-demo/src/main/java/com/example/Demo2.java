package com.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

public class Demo2 {
    @Test
    public void test5() throws IOException, TemplateException {
        String dir = "src/main/java";
        String file = "com/example/template/four.ftl";

        Configuration conf = new Configuration();
        conf.setDirectoryForTemplateLoading(new File(dir));
        Template template = conf.getTemplate(file);

        StringWriter writer = new StringWriter();
        template.process(new HashMap<>(1), writer);
        System.out.println(writer.toString());
    }
}
