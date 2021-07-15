package com.jamie.deser;

import com.jamie.entity.Person;

import java.io.*;

public class TestDemo {
    public static void main(String[] args) {
//        String path = "./studentserializable.txt";
//
//        Student student = new Student("tim", 20);
//        serializable(student, path);
//
//        Student studentDeser = (Student) deserializable(path);
//        System.out.println(studentDeser);

        Student student = new Student("tim", 20);
        byte[] bytes = ser2byteArray(student);
        Object deserializable = deserializable(bytes);

    }

    /**
     * 将 对象序列化 且写到内存
     *
     * @param object 对象
     */
    public static byte[] ser2byteArray(Object object) {
        try (ByteArrayOutputStream bao = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bao)) {
            oos.writeObject(object);
            byte[] bytes = bao.toByteArray();
            System.out.println("将 对象序列化 且写到内存: " + new String(bytes));
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将 字符串反序列化，转成对象
     *
     * @param serByte 序列化byte[]
     * @return 反序列化的对象
     */
    public static Object deserializable(byte[] serByte) {
        try (ByteArrayInputStream bai = new ByteArrayInputStream(serByte);
             ObjectInputStream ois = new ObjectInputStream(bai)) {
            Object o = ois.readObject();
            System.out.println("将 字符串反序列化，转成对象: " + o);
            return o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将 对象序列化 且写到文件
     *
     * @param object 对象
     * @param path   写出路径
     */
    public static void serializable(Object object, String path) {
        try (FileOutputStream fos = new FileOutputStream(new File(path));
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件的文本反序列化，转成对象
     *
     * @param path 文件路径
     * @return 反序列化的对象
     */
    public static Object deserializable(String path) {
        try (FileInputStream fis = new FileInputStream(new File(path));
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对象 序列化
     *
     * @param object
     * @return
     */
    public ByteArrayOutputStream serBAO(Object object) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)
        ) {
            oos.writeObject(object);
            return baos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param baos
     * @return
     */
    public Object deBAO(ByteArrayOutputStream baos) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
             ObjectInputStream ois = new ObjectInputStream(bais);) {
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}