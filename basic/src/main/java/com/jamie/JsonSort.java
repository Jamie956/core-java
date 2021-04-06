package com.jamie;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class JsonSort {
    /**
     * double 排序
     */
    @Test
    public void listdoubleorder() {
        String jsonString = "[{\"name\":\"a\",\"value\":0.01}, {\"name\":\"b\",\"value\":0.06}, {\"name\":\"c\",\"value\":0.3}]";

        //1
        JSONArray json = JSONObject.parseArray(jsonString);
        json.sort((o1, o2) -> {
            double value1 = ((JSONObject)o1).getDoubleValue("value") * 10000;
            double value2 = ((JSONObject)o2).getDoubleValue("value") * 10000;
            return (int) (value2 - value1);
        });

        //2
        json.sort((a, b) -> (int)(((JSONObject)a).getDoubleValue("value") - ((JSONObject)b).getDoubleValue("value")));

        //3
//        json.sort(Comparator.comparingDouble(e -> e.getDoubleValue("value")));

    }
}
