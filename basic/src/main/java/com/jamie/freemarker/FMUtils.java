package com.jamie.freemarker;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Zjm
 * @Date: 2021/4/12 17:16
 */
public class FMUtils {
    public static void main(String[] args) {
        Map<String, String> dataMap = new HashMap<>(1);
        dataMap.put("name", "tim");

        String ret = convertStrTemp("欢迎：${name}", dataMap);
        System.out.println(ret);
    }

    public static String convertStrTemp(String content, Map<String, String> dataMap) {
        try {
            StringTemplateLoader stringLoader = new StringTemplateLoader();
            stringLoader.putTemplate("myTemplate", content);

            Configuration cfg = new Configuration();
            cfg.setTemplateLoader(stringLoader);
            Template template = cfg.getTemplate("myTemplate","utf-8");
            StringWriter writer = new StringWriter();
            template.process(dataMap, writer);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
