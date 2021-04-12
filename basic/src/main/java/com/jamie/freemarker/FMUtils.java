package com.jamie.freemarker;

import com.jamie.entity.User;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.StringWriter;
import java.util.*;

/**
 * @Author: Zjm
 * @Date: 2021/4/12 17:16
 */
public class FMUtils {

    @Test
    public void test1() {
        Map<String, String> dataMap = new HashMap<>(1);
        dataMap.put("name", "tim");

        String ret = strTemp("欢迎：${name}", dataMap);
        System.out.println(ret);
    }

    /**
     * 变量、函数、运算
     */
    @Test
    public void test2(){
        String dir = "src/main/java/com/jamie/freemarker";
        String file = "first.ftl";
        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("name", "jamie");
        dataMap.put("dateTime", new Date());

        String ret = fileTemp(dir, file, dataMap);
        System.out.println(ret);
    }

    /**
     * 集合遍历
     */
    @Test
    public void test3(){
        List<User> users = new ArrayList<>();
        users.add(new User(1, "tom"));
        users.add(new User(2, "tim"));
        users.add(new User(3, "to"));

        Map<String, Object> dataMap = new HashMap<>(1);
        dataMap.put("users", users);

        String dir = "src/main/java/com/jamie/freemarker";
        String file = "sec.ftl";

        String ret = fileTemp(dir, file, dataMap);
        System.out.println(ret);
    }

    /**
     * map 遍历
     */
    @Test
    public void test4(){
        Map<String,List<String>> kindsMap  = new HashMap<>();
        kindsMap.put("a", Arrays.asList("a1", "a2"));
        kindsMap.put("b", Arrays.asList("b1", "b2"));

        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("kindsMap", kindsMap);
        dataMap.put("kindsMap3", new HashMap<>(1));

        String dir = "src/main/java/com/jamie/freemarker";
        String file = "three.ftl";

        String ret = fileTemp(dir, file, dataMap);
        System.out.println(ret);
    }

    @Test
    public void test5(){
        String dir = "src/main/java/com/jamie/freemarker";
        String file = "four.ftl";

        String ret = fileTemp(dir, file, new HashMap<>(1));
        System.out.println(ret);
    }

    public static String strTemp(String content, Map<String, String> dataMap) {
        try {
            StringTemplateLoader stringLoader = new StringTemplateLoader();
            stringLoader.putTemplate("myTemplate", content);

            Configuration conf = new Configuration();
            conf.setTemplateLoader(stringLoader);
            Template template = conf.getTemplate("myTemplate","utf-8");
            StringWriter writer = new StringWriter();
            template.process(dataMap, writer);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String fileTemp(String dir, String file, Map<String, Object> dataMap) {
        try {
            Configuration conf = new Configuration();
            conf.setDirectoryForTemplateLoading(new File(dir));
            Template template = conf.getTemplate(file);

            StringWriter writer = new StringWriter();
            template.process(dataMap, writer);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
