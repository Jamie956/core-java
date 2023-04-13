package com.example;

import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class SerializeTest {
    @Data
    static class Student implements Serializable {
        private static final long serialVersionUID = -4307074831333148448L;
        private String name;
        private int age;
        //transient 代表字段不进行序列化
//    private transient int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    @Test
    public void serializeTest() {
        Student student = new Student("tim", 20);

        // 序列化
        byte[] bytes = new byte[1024];
        try (ByteArrayOutputStream bao = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bao)) {
            oos.writeObject(student);
            bytes = bao.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 反序列化
        try (ByteArrayInputStream bai = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bai)) {
            Student o = (Student) ois.readObject();
            Assert.assertEquals("tim", o.getName());
            Assert.assertEquals(20, o.getAge());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}