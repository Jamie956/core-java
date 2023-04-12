package com.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo3 {
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

    /**
     * 集合遍历？？？？
     */
    @Test
    public void test3() throws IOException, TemplateException {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "tom"));
        users.add(new User(2, "tim"));
        users.add(new User(3, "to"));

        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("users", users);

        String dir = "src/main/java";
        String file = "com/example/template/sec.ftl";

//        String ret = fileTemp(dir, file, dataMap);
//        System.out.println(ret);

        Configuration conf = new Configuration();
        conf.setDirectoryForTemplateLoading(new File(dir));
        Template template = conf.getTemplate(file);

        StringWriter writer = new StringWriter();
        template.process(dataMap, writer);
        System.out.println(writer.toString());

    }
}
