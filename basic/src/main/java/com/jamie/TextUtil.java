package com.jamie;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextUtil {

    /**
     * 数据比对
     * source文件的每一行数据转成数组元素，逐行去target文件找是否存在
     */
    public static void lineCompare(String srcPath, String targetPath) throws IOException {
        String srcText = FileUtils.readFileToString(new File(srcPath), "UTF-8");
        String[] srcLines = srcText.split("\r\n");
        String targetText = FileUtils.readFileToString(new File(targetPath), "UTF-8");

        int matchCount = 0;
        int notMatchCount = 0;
        List<String> notMatchList = new ArrayList<>();
        for (String line : srcLines) {
            if (targetText.contains(line)) {
                matchCount++;
                System.out.println("source文件当前行在target找到匹配数据: " + line);
            } else {
                notMatchCount++;
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>source文件当前行在target找不到匹配数据: " + line);
                notMatchList.add(line);
            }
        }

        System.out.println("---------------总数统计---------------");
        System.out.println("匹配数目: " + matchCount);
        System.out.println("不匹配数目: " + notMatchCount);
        System.out.println("不匹配list: " + notMatchList);
    }
    @Test
    public void testCompare() throws IOException {
        lineCompare("src/main/resources/source", "src/main/resources/target");
    }

    /**
     * 找重复
     */
    @Test
    public void findDup() throws IOException {
        String text = FileUtils.readFileToString(new File("src/main/resources/source"), "UTF-8");
        //每行转成数组
        String[] lines = text.split("\r\n");
        //每一行和整个数组比较，统计出现次数
        for (String line : lines) {
            int count = 0;
            for (String ele : lines) {
                if (ele.equals(line)) {
                    count++;
                }
            }
            if (count > 1) {
                System.out.println(line + " 总共出现了 " + count + "次");
            }
        }
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
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> "+line);
                e.printStackTrace();
            }
        }
        System.out.println("count="+count);
    }

    /**
     * 统计字符串byte 长度
     */
    @Test
    public void countByte() {
        int length = "撒打算打算大多数地方不回家撒打算大的".getBytes().length;
        System.out.println(length+"b");
    }

}
