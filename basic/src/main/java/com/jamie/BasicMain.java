package com.jamie;

import com.alibaba.fastjson.JSONArray;
import com.jamie.entity.*;
import com.alibaba.fastjson.JSONObject;

import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Date;

public class BasicMain {

    /**
     * 反射获取成员变量、修改成员变量
     */
    @Test
    public void reflectFields() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        Field nameField = person.getClass().getDeclaredField("name");
        nameField.setAccessible(true);

        String a = (String) nameField.get(person);
        nameField.set(person, "jim");
        String b = (String) nameField.get(person);
    }

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

    @Test
    public void isDateTest() {
        boolean a = isDate("2021-03-01");
        boolean b = isDate("6363-34-60");
        boolean c = isDate("0000-00-00");
    }

    private static boolean isDate(String date) {
        try {
            Date.valueOf(date);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 取模
     */
    @Test
    public void asa(){
//        int a = 499 % 500;//499
//        int b = 500 % 500;//0
//        int c = 501 % 500;//1
//        int d = 999 % 500;//499
//        int e = 1000 % 500;//0
//        int f = 1001 % 500;//1

//        int g = 499 / 500;//0
//        int h = 500 / 500;//1
//        int i = 501 / 500;//1
//        int j = 999 / 500;//1
//        int k = 1000 / 500;//2
//        int l = 1001 / 500;//2

        int a = Math.floorDiv(499, 500);//0
        int b = Math.floorDiv(500, 500);//1
        int c = Math.floorDiv(501, 500);//1
        int d = Math.floorDiv(999, 500);//1
        int e = Math.floorDiv(1000, 500);//2
        int f = Math.floorDiv(1001, 500);//2
    }

    @Test
    public void random(){
        for (int i = 0; i < 100; i++) {
            int a = 1+ (int)(Math.random()*9);
            System.out.println(a);
        }
    }
}



