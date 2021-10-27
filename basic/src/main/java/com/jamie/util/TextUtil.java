package com.jamie.util;

import cn.hutool.http.HtmlUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

        String[] srcLineArray = FileUtils.readFileToString(new File(srcPath), "UTF-8").split("\r\n");
        String[] targetLineArray = FileUtils.readFileToString(new File(targetPath), "UTF-8").split("\r\n");

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
        String[] lines = FileUtils.readFileToString(new File("src/main/resources/source"), "UTF-8").split("\r\n");

        Map<String, Integer> map = new HashMap<>();
        for (String line : lines) {
            Integer value = map.get(line) == null ? 1 : map.get(line) + 1;
            map.put(line, value);
        }

        Map<String, Integer> collect = map.entrySet().stream().filter(e -> e.getValue() > 1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        collect.forEach((key, value) -> System.out.println("重复行=" + key + "；重复次数=" + value));
    }

    /**
     * 统计非法数字
     */
    @Test
    public void inValNum() throws IOException {
        String text = FileUtils.readFileToString(new File("src/main/resources/source"), "UTF-8");
        String[] lines = text.split("\r\n");

        int count = 0;
        for (String line : lines) {
            try {
                Float.parseFloat(line);
            } catch (NumberFormatException e) {
                count++;
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> " + line);
                e.printStackTrace();
            }
        }
        System.out.println("count=" + count);
    }

    /**
     * 统计字符串byte 长度
     */
    @Test
    public void countByte() {
        int length = "撒打算打算大多数地方不回家撒打算大的".getBytes().length;
        System.out.println(length + "b");
    }

    /**
     * source.id join target.id
     */
    @Test
    public void join() throws IOException {
        String srcText = FileUtils.readFileToString(new File("src/main/resources/source"), "UTF-8");
        String[] srcLines = srcText.split("\r\n");
        String targetText = FileUtils.readFileToString(new File("src/main/resources/target"), "UTF-8");
        String[] targetLines = targetText.split("\r\n");

        Map<String, String> map = new HashMap<>();
        for (String targetLine : targetLines) {
            String[] split = targetLine.split("#split#");
            if (split.length > 1) {
                String contentId = split[0];
                String content = split[1];
                map.put(contentId, content);
            }
        }
        FileWriter fileWriter = new FileWriter("src/main/resources/output");
        for (String srcLine : srcLines) {
            String content = map.get(srcLine);
            content = HtmlUtil.removeHtmlTag(content, "img");
            if (content == null) {
                content = "";
            }
            String ret = srcLine + "^" + content + "\r\n";
            fileWriter.write(ret);
        }
        fileWriter.flush();
    }
}
