package com.cat;

import com.alibaba.fastjson.JSONObject;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@UtilityClass
public class JsonUtil {
    /**
     * 检查两个 json list 变更字段值
     */
    public static void checkListJsonValue(List<JSONObject> l1, List<JSONObject> l2) {
        if (l2.isEmpty()) {
            return;
        }
        Map<String, JSONObject> map1 = l1.stream().collect(Collectors.toMap(e -> e.getString("id"), e -> e));
        Map<String, JSONObject> map2 = l2.stream().collect(Collectors.toMap(e -> e.getString("id"), e -> e));

        for (String key1 : map1.keySet()) {
            JSONObject j1 = map1.get(key1);
            JSONObject j2 = map2.get(key1);
            checkJsonValue(j1, j2);
        }
    }

    /**
     * 检查两个json 变更字段值
     */
    public static void checkJsonValue(JSONObject j1, JSONObject j2) {
        if (j1 == null || j2 == null) {
            return;
        }
        //比较同一个key 的value 值
        for (String k1 : j1.keySet()) {
            String v1 = j1.getString(k1);
            String v2 = j2.getString(k1);
            if (!v1.equals(v2)) {
                log.info(String.format("id=[%s] Excel json 与 ES 文档 key=[%s] 发生变更, Excel json [%s]=[%s] -> ES 文档 [%s]=[%s]", j1.getString("id"), k1, k1, v1, k1, v2));
            }
        }
    }
}
