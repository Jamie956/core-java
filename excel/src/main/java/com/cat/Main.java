package com.cat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;

public class Main {
    @Test
    public void esData2Excel() throws IOException {
//        String data = FileUtils.readFileToString(new File("src/main/resources/data.json"), "UTF-8");
//        List<JSONObject> collect = JSON.parseObject(data).getJSONArray("hits").stream()
//                .map(e -> JSON.parseObject(e.toString()).getJSONObject("_source")).collect(Collectors.toList());
//
//        JSONArray arr = JSON.parseArray(collect.toString());

        JSONArray jsonArray = new JSONArray();
        JSONObject j1 = new JSONObject();
        j1.put("name", "tim");
        j1.put("age", "11");
        JSONObject j2 = new JSONObject();
        j2.put("name", "tom");
        j2.put("age", "22");
        jsonArray.add(j1);
        jsonArray.add(j2);

        JsonExcelConvert.json2Excel(jsonArray, "src/main/resources/output.xlsx");
    }

    @Test
    public void excel2jsonTest() throws Exception {
        JsonExcelConvert.excel2jsonLine("D:\\2.xlsx",
                "src/main/resources/output.json");
    }

    @Test
    public void excel2jsonFileTest2() throws Exception {
        JSONArray jsonArray = JsonExcelConvert.excel2jsonFile("D:\\2.xlsx");
        System.out.println(jsonArray);
    }

    @Test
    public void excel2jsonFileTest() throws Exception {
        JSONArray items = JsonExcelConvert.excel2jsonFile("D:\\1.xlsx");
        JSONArray result = new JSONArray();
        for (Object o : items) {
            if (o instanceof JSONObject) {
                JSONObject item = (JSONObject) o;
                String category = item.getString("category");
                String categoryId = item.getString("categoryId");

                if (StringUtils.isNotBlank(category) && StringUtils.isNotBlank(categoryId)) {
                    String[] categoryArray = category.split(";");
                    String[] categoryIdArray = categoryId.split(";");

                    //检查
                    System.out.println(item.getString("id"));
                    System.out.println(categoryArray.length);
                    System.out.println(categoryIdArray.length);
                    System.out.println(categoryArray.length == categoryIdArray.length);
                    System.out.println("=======");

                    JSONArray categories = new JSONArray();
                    for (int i = 0; i < categoryArray.length; i++) {
                        String categoryElement = categoryArray[i];
                        String categoryIdArrayElement = categoryIdArray[i];

                        JSONObject categoryJson = new JSONObject();
                        categoryJson.put("id", categoryIdArrayElement);
                        categoryJson.put("name", categoryElement);

                        categories.add(categoryJson);
                    }
                    JSONObject newItem = new JSONObject();
                    newItem.put("id", item.getString("id"));
                    newItem.put("title", item.getString("title"));
                    newItem.put("url", item.getString("url"));
                    newItem.put("categories", categories);
                    result.add(newItem);
                } else {
                    result.add(item);
                }
            }
        }
        System.out.println(result);
    }

}
