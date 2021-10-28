package com.jamie.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HtmlUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class TextUtil {
    /**
     * 数据比对
     * source文件的每一行数据转成数组元素，逐行去target文件找是否存在
     */
    @Test
    public void lineCompare() throws IOException {
        String srcPath = "src/main/resources/source";
        String targetPath = "src/main/resources/target";

        String[] srcLineArray = FileUtils.readFileToString(new File(srcPath), Charset.defaultCharset()).split("\r\n");
        String[] targetLineArray = FileUtils.readFileToString(new File(targetPath), Charset.defaultCharset()).split("\r\n");

        int matchCount = 0;
        int notMatchCount = 0;
        List<String> notMatchList = new ArrayList<>();
        for (String srcLine : srcLineArray) {
            boolean isContain = Arrays.stream(targetLineArray).anyMatch(e -> e.contains(srcLine));
            if (isContain) {
                matchCount++;
            } else {
                notMatchCount++;
                notMatchList.add(srcLine);
            }
        }
        System.out.println("---------------总数统计---------------");
        System.out.println("匹配行数: " + matchCount);
        System.out.println("不匹配行数: " + notMatchCount);
        System.out.println("src在target找不到匹配的src行: " + notMatchList);
    }

    /**
     * 找重复
     */
    @Test
    public void findDup() throws IOException {
        String[] lines = FileUtils.readFileToString(new File("src/main/resources/source"), Charset.defaultCharset()).split("\r\n");

        Map<String, Integer> map = new HashMap<>();
        for (String line : lines) {
            Integer value = map.get(line) == null ? 1 : map.get(line) + 1;
            map.put(line, value);
        }

        Map<String, Integer> collect = map.entrySet().stream().filter(e -> e.getValue() > 1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        collect.forEach((key, value) -> System.out.println("重复行=" + key + "；重复次数=" + value));
    }

    /**
     * 发现异常行
     */
    @Test
    public void findInValidNumber() throws IOException {
        String[] lines = FileUtils.readFileToString(new File("src/main/resources/source"), Charset.defaultCharset()).split("\r\n");
        int count = 0;
        for (String line : lines) {
            try {
                //统计非法数字
                Float.parseFloat(line);
            } catch (NumberFormatException e) {
                count++;
                System.out.println("异常行：" + line);
            }
        }
        System.out.println("发现异常的总行数：" + count);
    }

    /**
     * source join target on source.id = target.id
     */
    @Test
    public void join() throws IOException {
        String[] srcLines = FileUtils.readFileToString(new File("src/main/resources/source"), Charset.defaultCharset()).split("\r\n");
        String[] targetLines = FileUtils.readFileToString(new File("src/main/resources/target"), Charset.defaultCharset()).split("\r\n");

        Map<String, String> map = Arrays.stream(targetLines).map(e -> e.split("#split#")).filter(e -> e.length > 1).collect(Collectors.toMap(e -> e[0], e -> e[1]));

        StringBuilder sb = new StringBuilder();
        for (String srcLine : srcLines) {
            String content = map.get(srcLine);
            content = HtmlUtil.removeHtmlTag(content, "img");
            if (content == null) {
                content = "";
            }
            String ret = srcLine + "^" + content + "\r\n";

            sb.append(ret);
        }
        FileUtils.writeStringToFile(new File("src/main/resources/output"), sb.toString(), Charset.defaultCharset());
    }

    @Data
    static class DateTest {
        private String id;
        private Date pubTime;
    }

    /**
     * 读取es查询文档的json 文件，检验非法日期并转为合法日期
     */
    @Test
    public void checkJsonInvalidDate() throws IOException {
        String dataStr = FileUtils.readFileToString(new File("src/main/resources/data.json"), Charset.defaultCharset());
        JSONObject data = JSON.parseObject(dataStr);
        JSONArray hits = data.getJSONArray("hits");

        StringBuilder sb = new StringBuilder();

        for (Object hit : hits) {
            JSONObject hitJson = JSON.parseObject(hit.toString());
            JSONObject source = hitJson.getJSONObject("_source");
            String pubTime = source.getString("pub_time");
            String id = source.getString("id");

            try {
                //检验异常非法日期
                DateTest dateTest = source.toJavaObject(DateTest.class);
            } catch (JSONException e) {
                //异常日期
                System.out.println("异常日期：" + id + "#" + pubTime);
                //修复日期
                Date fixDate = DateUtil.parse(pubTime);
                sb.append(String.format("\"%s\":\"%s\",\r\n", id, fixDate));
            }
        }
        FileUtils.writeStringToFile(new File("src/main/resources/output"), sb.toString(), Charset.defaultCharset());
    }

}
