package com.jamie;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Objects;

public class DataHandle {
    public JSONObject getMockData() {
        try (
                InputStream inputStream = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("data.json"));
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.defaultCharset())
        ) {
            StringBuilder sb = new StringBuilder();
            char[] buffer = new char[1024];
            int len = 0;
            while ((len = inputStreamReader.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, len));
            }
            return JSON.parseObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Data
    static class DateTest {
        private String id;
        private Date pubTime;
    }

    /**
     * 读取es 查询文档 json 文件，检验非法日期并转为合法日期
     */
    @Test
    public void checkJsonInvalidDate() {
        JSONObject data = getMockData();
        JSONArray hits = data.getJSONArray("hits");
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
//                System.out.println("异常日期：" + id + "#" + pubTime);
                Date fixDate = DateUtil.parse(pubTime);
                System.out.println(String.format("\"%s\":\"%s\",", id, fixDate));
            }
        }
    }
}
