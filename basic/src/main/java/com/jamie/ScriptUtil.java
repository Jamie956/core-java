package com.jamie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ScriptUtil {

    /**
     * es data join mongodb files data
     */
    @Test
    public void dataJoin() throws IOException {
        String esHitsDataStr = FileUtils.readFileToString(new File("src/main/resources/data.json"), Charset.defaultCharset());
        JSONObject esHitsData = JSON.parseObject(esHitsDataStr);
        JSONArray esHits = esHitsData.getJSONArray("hits");

        String mongoFilesDataStr = FileUtils.readFileToString(new File("src/main/resources/data2.json"), Charset.defaultCharset());
        JSONArray mongoFilesData = JSON.parseArray(mongoFilesDataStr);

        Map<String, String> mongoFileIdMap = new HashMap<>(12);
        for (Object o : mongoFilesData) {
            JSONObject mongoFileData = JSON.parseObject(o.toString());
            String mongoId = mongoFileData.getString("_id");
            String fileName = mongoFileData.getString("filename");
            mongoFileIdMap.put(mongoId, fileName);
        }

        for (Object hit : esHits) {
            JSONObject hitJson = JSON.parseObject(hit.toString());
            String esFilePath = hitJson.getString("file_path");
            String esDocId = hitJson.getString("id");
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
            hitJson.put("files", files);

            System.out.println(String.format("{ \"index\": { \"_id\": %s }}", esDocId));
            System.out.println(hitJson.toJSONString());
        }
    }
}
