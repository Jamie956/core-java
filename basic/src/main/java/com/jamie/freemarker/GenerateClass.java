package com.jamie.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class GenerateClass {

    /**
     * 根据实体名，创建entity, model, service, mapper 类
     *
     * @throws IOException
     */
    @Test
    public void createClass() throws IOException {
        String text = FileUtils.readFileToString(new File("src/main/resources/tableinfo"), "UTF-8");
        String[] lines = text.split(System.getProperty("line.separator", "\n"));

        List<String> entities = new ArrayList<>();
        for (String line : lines) {
            String[] words = line.split(",");
            entities.add(words[0]);
        }

        for (String entity : entities) {
            createClass(entity, "Mapper", "com.ccr.qc.business");
            createClass(entity, "Service", "com.ccr.qc.business");
            createClass(entity, "Entity", "com.ccr.qc.business");
            createClass(entity, "Model", "com.ccr.qc.business");
        }
    }

    public void createClass(String entity, String type, String classPath) {
        try {
            String resourcePath = new File("").getCanonicalPath() + "/src/main/resources/";

            Map<String, Object> dataMap = new HashMap<>(2);
            dataMap.put("entity", entity);
            dataMap.put("class_path", classPath);

            Configuration configuration = new Configuration();
            configuration.setDirectoryForTemplateLoading(new File(resourcePath));
            Template template = configuration.getTemplate(type.toLowerCase() + ".ftl");

            String outDir = resourcePath + type.toLowerCase();

            File path = new File(outDir);
            if (!path.isDirectory()) {
                boolean ret = path.mkdir();
                System.out.println("创建文件夹： " + ret);
            }
            File docFile = new File(outDir + "/" + entity + type + ".java");

            if ("Entity".equals(type)) {
                docFile = new File(outDir + "/" + entity + ".java");
            }

            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据实体名创建 controller类
     */
    @Test
    public void createController() {
        try {
            String text = FileUtils.readFileToString(new File("src/main/resources/tableinfo"), "UTF-8");
            String[] lines = text.split(System.getProperty("line.separator", "\n"));

            Map<String, String> entityMap = new HashMap<>();
            for (String line : lines) {
                String[] words = line.split(",");
                entityMap.put(words[0], words[1]);
            }

            Map<String, Object> dataMap = new HashMap<>(1);
            dataMap.put("entityMap", entityMap);

            Configuration configuration = new Configuration();
            configuration.setDirectoryForTemplateLoading(new File("/src/main/resources/"));
            Template template = configuration.getTemplate("controller2.ftl");

            StringWriter writer = new StringWriter();
            template.process(dataMap, writer);

            System.out.println(writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
