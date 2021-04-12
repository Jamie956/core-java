package com.jamie.freemarker;


import com.jamie.entity.User;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class Testss {

    public static void main(String[] args) {

    }

    //字符串所为模板
    @Test
    public void stringTemplate() {
        try {
            StringTemplateLoader stringLoader = new StringTemplateLoader();
            stringLoader.putTemplate("myTemplate", "欢迎：${name}");

            Map<String, String> dataMap = new HashMap<>(1);
            dataMap.put("name", "tim");

            Configuration cfg = new Configuration();
            cfg.setTemplateLoader(stringLoader);
            Template template = cfg.getTemplate("myTemplate","utf-8");
            StringWriter writer = new StringWriter();
            template.process(dataMap, writer);
            System.out.println(writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            List<User> users = new ArrayList<User>();
            users.add(new User(1, "tom"));
            users.add(new User(2, "tim"));
            users.add(new User(3, "to"));

            //遍历
            Map<String,List<String>> kindsMap  = new HashMap<>();
            List<String> aList = new ArrayList<String>();
            aList.add("a1");
            aList.add("a2");

            List<String> bList = new ArrayList<String>();
            bList.add("b1");
            bList.add("b2");

            kindsMap.put("a",aList);
            kindsMap.put("b", bList);

            //判空
            Map<String, List<User>> kindsMap3  = new HashMap<>();

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("name", "jamie");
            dataMap.put("dateTime", new Date());
            dataMap.put("users", users);
            dataMap.put("kindsMap", kindsMap);
            dataMap.put("kindsMap3", kindsMap3);

            Configuration configuration = new Configuration();
            configuration.setDirectoryForTemplateLoading(new File("src/main/java/com/jamie/freemarker"));
            Template template = configuration.getTemplate("hello.ftl");

            StringWriter writer = new StringWriter();
            template.process(dataMap, writer);

            System.out.println(writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
