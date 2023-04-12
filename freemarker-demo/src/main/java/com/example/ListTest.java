package com.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTest {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User implements Serializable {
        private static final long serialVersionUID = -3307269962764425802L;
        private Integer id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

    }

    @Test
    public void tt() throws IOException, TemplateException {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "tom"));
        users.add(new User(2, "tim"));
        users.add(new User(3, "to"));

        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("users", users);

        String dir = "src/main/java";
        String file = "com/example/template/list.ftl";

        Configuration conf = new Configuration();
        conf.setDirectoryForTemplateLoading(new File(dir));
        Template template = conf.getTemplate(file);

        StringWriter writer = new StringWriter();
        template.process(dataMap, writer);
        Assert.assertEquals("3\n" +
                "        ListTest.User(id=1, name=tom)\n" +
                "        ListTest.User(id=2, name=tim)\n" +
                "        ListTest.User(id=3, name=to)\n", writer.toString());
    }
}
