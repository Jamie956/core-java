package com.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsTest {
    /**
     * map 遍历
     */
    @Test
    public void tt() throws IOException, TemplateException {
        Map<String, List<String>> kindsMap = new HashMap<>();
        kindsMap.put("a", Arrays.asList("a1", "a2"));
        kindsMap.put("b", Arrays.asList("b1", "b2"));

        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("kindsMap", kindsMap);
        dataMap.put("kindsMap3", new HashMap<>(1));

        String dir = "src/main/java";
        String file = "com/example/template/maps.ftl";

        Configuration conf = new Configuration();
        conf.setDirectoryForTemplateLoading(new File(dir));

        Template template = conf.getTemplate(file);
        StringWriter writer = new StringWriter();
        template.process(dataMap, writer);
        System.out.println(writer.toString());
    }
}
