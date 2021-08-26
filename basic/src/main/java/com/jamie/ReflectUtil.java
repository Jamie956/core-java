package com.jamie;

import com.jamie.entity.Person;
import org.junit.Test;

import java.lang.reflect.Field;

public class ReflectUtil {
    /**
     * 反射获取成员变量
     */
    public static Object getFieldValue(Object o, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = o.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(o);
    }

    /**
     * 修改成员变量
     */
    public static void setFieldValue(Object o, String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
        Field field = o.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(o, value);
    }

    @Test
    public void getTest() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        Object name = getFieldValue(person, "name");
        String a = (String) name;
    }

    @Test
    public void setTest() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        setFieldValue(person, "name", "tim");
    }

}
