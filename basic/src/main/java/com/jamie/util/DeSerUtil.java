package com.jamie.util;

import lombok.Data;

import java.io.*;
import java.util.Objects;

public class DeSerUtil {
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

    public static void main(String[] args) {
//        String path = "./studentserializable.txt";
//
//        Student student = new Student("tim", 20);
//        serializable(student, path);
//
//        Student studentDeser = (Student) deserializable(path);
//        System.out.println(studentDeser);

        Student student = new Student("tim", 20);
        byte[] bytes = object2ByteArray(student);
        String s = object2String(student);

        Object o = byte2Object(bytes);
//        byte[] bytes = ser2byteArray(student);
//        Object deserializable = deserializable(bytes);

    }

    /**
     * 对象序列化，转换成字符串
     *
     * @param object 对象
     * @return 字符串
     */
    public static String object2String(Object object) {
        return new String(Objects.requireNonNull(object2ByteArray(object)));
    }

    /**
     * 对象序列化，转换成字节数组
     * 对象 -> 对象输出流 -> 字节数组输出流 -> 字节数组
     *
     * @param object 对象
     * @return 字节数组
     */
    public static byte[] object2ByteArray(Object object) {
        try (ByteArrayOutputStream bao = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bao)) {
            oos.writeObject(object);
            return bao.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字节数组 反序列化，转成对象
     * 字节数组 -> 字节数组输入流 -> 对象输入流 -> 对象
     *
     * @param bytes 字节数组
     * @return 反序列化的对象
     */
    public static Object byte2Object(byte[] bytes) {
        try (ByteArrayInputStream bai = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bai)) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对象序列化，写到文件
     * 文件 <- 文件输出流 <- 对象输出流 <- 对象
     *
     * @param object 对象
     * @param path   写出路径
     */
    public static void object2File(Object object, String path) {
        try (FileOutputStream fos = new FileOutputStream(new File(path));
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件反序列化，转成对象
     * 文件 -> 文件输入流 -> 对象输入流 -> 对象
     *
     * @param path 文件路径
     * @return 反序列化的对象
     */
    public static Object file2Object(String path) {
        try (FileInputStream fis = new FileInputStream(new File(path));
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}