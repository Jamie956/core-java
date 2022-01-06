package com.jamie;

import cn.hutool.http.HtmlUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class ScriptUtil {
    @Test
    public void printEsDslTest() throws IOException {
        List<JSONObject> items = getJsonArray("data.json");
        List<JSONObject> categoryMaps = getJsonArray("data2.json");

        for (JSONObject item : items) {
            String snapshotId = item.getString("snapshotId");
            for (JSONObject categoryMap : categoryMaps) {
                String snapshotIdInMap = categoryMap.getString("id");
                JSONArray categoriesInMap = categoryMap.getJSONArray("categories");

                if (snapshotId.equals(snapshotIdInMap)) {
                    item.put("categories", categoriesInMap);
                }
            }
        }
        printEsDsl("comparative_test_detail_20220104", items, "id");
    }

    /**
     * 根据 Json 数据，生成ES DSL
     */
    public static void printEsDsl(String indexName, List<JSONObject> items, String docIdFieldName) {
        System.out.println(String.format("POST /%s/index/_bulk", indexName));
        for (JSONObject item : items) {
            System.out.println(String.format("{ \"index\": { \"_id\": %s }}", item.getString(docIdFieldName)));
            System.out.println(item.toJSONString());
        }
    }

    /**
     * ES hits 转 Json 集合
     */
    public static List<JSONObject> esHitsToJson(String fileName) throws IOException {
        List<JSONObject> result = new ArrayList<>();
        List<JSONObject> items = getJsonArray("data.json");
        for (JSONObject item : items) {
            JSONObject source = item.getJSONObject("_source");
            result.add(source);
        }
        return result;
    }

    /**
     * es data join mongodb files data
     * create es create DSL
     */
    @Test
    public void dataJoin() throws IOException {
        List<JSONObject> mongoList = getJsonArray("data2.json");
        Map<String, String> mongoFileIdMap = new HashMap<>(12);
        for (JSONObject mongoFileData : mongoList) {
            String mongoId = mongoFileData.getString("_id");
            String fileName = mongoFileData.getString("filename");
            mongoFileIdMap.put(mongoId, fileName);
        }

        List<JSONObject> esHits = readEsHitSource("data.json");
        for (JSONObject esHit : esHits) {
            String esFilePath = esHit.getString("file_path");
            String esDocId = esHit.getString("id");
            String[] esFilesPathSplit = StringUtils.split(esFilePath, ",");

            JSONArray files = new JSONArray();
            for (String path : esFilesPathSplit) {
                String[] pathSeparator = StringUtils.split(path, "/");
                String fileMgId = pathSeparator[2];
                String fileName = mongoFileIdMap.get(fileMgId);

                JSONObject file = new JSONObject();
                file.put("path", path);
                file.put("name", fileName);
                files.add(file);
            }
            esHit.put("files", files);

            System.out.println(String.format("{ \"index\": { \"_id\": %s }}", esDocId));
            System.out.println(esHit.toJSONString());
        }
    }

    /**
     * 数据比对
     * source文件的每一行数据转成数组元素，逐行去target文件找是否存在
     */
    @Test
    public void lineCompare() throws IOException {
        List<String> srcList = readFileLinesAsArray("source");
        List<String> targetList = readFileLinesAsArray("target");

        List<String> notMatchList = new LinkedList<>();
        List<String> matchList = new LinkedList<>();
        for (String srcLine : srcList) {
            if (targetList.contains(srcLine)) {
                matchList.add(srcLine);
            } else {
                notMatchList.add(srcLine);
            }
        }
        System.out.println("匹配行数: " + matchList.size());
        System.out.println("不匹配行数: " + notMatchList.size());
        System.out.println("不匹配行数据: " + notMatchList);
    }

    public static List<String> readFileLinesAsArray(String fileName) throws IOException {
        String[] split = FileUtils.readFileToString(new File("src/main/resources/" + fileName), Charset.defaultCharset()).split("\r\n");
        return Arrays.asList(split);
    }

    /**
     * 找重复
     */
    @Test
    public void findDup() throws IOException {
        List<String> lines = readFileLinesAsArray("source");

        Map<String, Integer> map = new HashMap<>(128);
        for (String line : lines) {
            Integer value = map.get(line) == null ? 1 : map.get(line) + 1;
            map.put(line, value);
        }

        Map<String, Integer> collect = map.entrySet().stream().filter(e -> e.getValue() > 1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        collect.forEach((key, value) -> System.out.println(String.format("[%s] 的重复次数 [%s]", key, value)));
    }

    /**
     * 发现异常行
     */
    @Test
    public void findInValidNumber() throws IOException {
        List<String> lines = readFileLinesAsArray("source");
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
        List<String> srcLines = readFileLinesAsArray("source");
        List<String> targetLines = readFileLinesAsArray("target");

        Map<String, String> map = targetLines.stream().map(e -> e.split("#split#")).filter(e -> e.length > 1).collect(Collectors.toMap(e -> e[0], e -> e[1]));

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
        writeToOutput(sb.toString());
    }

    /**
     * 输出
     */
    public void writeToOutput(String content) throws IOException {
        FileUtils.writeStringToFile(new File("src/main/resources/output"), content, Charset.defaultCharset());
    }

    @Data
    static class DateTest {
        private String id;
        private Date pubTime;
    }

    /**
     * 检查Es Json非法日期
     */
    @Test
    public void checkJsonInvalidDate() throws IOException {
        List<JSONObject> sourceList = readEsHitSource("source");
        for (JSONObject source : sourceList) {
            String pubTime = source.getString("pub_time");
            try {
                //检验异常非法日期
                DateTest dateTest = source.toJavaObject(DateTest.class);
            } catch (JSONException e) {
                //异常日期
                System.out.println("异常日期：" + pubTime);
            }
        }
    }

    /**
     * 获取es查询结果source
     */
    public static List<JSONObject> readEsHitSource(String fileName) throws IOException {
        String str = FileUtils.readFileToString(new File("src/main/resources/" + fileName), Charset.defaultCharset());
        JSONObject data = JSON.parseObject(str);
        JSONArray hits = data.getJSONObject("hits").getJSONArray("hits");

        List<JSONObject> result = new LinkedList<>();
        for (Object o : hits) {
            if (o instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) o;
                JSONObject source = jsonObject.getJSONObject("_source");
                result.add(source);
            }
        }
        return result;
    }

    /**
     * 读文件json array
     */
    public static List<JSONObject> getJsonArray(String fileName) throws IOException {
        String str = FileUtils.readFileToString(new File("src/main/resources/" + fileName), Charset.defaultCharset());
        JSONArray arr = JSON.parseArray(str);
        List<JSONObject> result = new LinkedList<>();
        for (Object o : arr) {
            if (o instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) o;
                result.add(jsonObject);
            }
        }
        return result;
    }

}
