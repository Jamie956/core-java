package com.cat;

import lombok.Data;
import org.junit.Test;

import java.io.*;

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

    /**
     * 对象 -> 对象输出流 -> 字节数组输出流 -> 字节数组 -> 字符串
     */
    @Test
    public void object2String() {
        Student student = new Student("tim", 20);
        try (ByteArrayOutputStream bao = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bao)) {
            oos.writeObject(student);

            byte[] bytes = bao.toByteArray();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (byte aByte : bytes) {
                sb.append(aByte).append(",");
            }
            sb.append("]");
            System.out.println(sb.toString());

            String str = new String(bytes);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节数组 反序列化，转成对象
     * 字节数组 -> 字节数组输入流 -> 对象输入流 -> 对象
     */
    @Test
    public void byte2Object() {
        byte[] bytes = {-84,-19,0,5,115,114,0,32,99,111,109,46,106,97,109,105,101,46,117,116,105,108,46,68,101,83,101,114,85,116,105,108,36,83,116,117,100,101,110,116,-60,58,50,66,-63,92,-72,-32,2,0,2,73,0,3,97,103,101,76,0,4,110,97,109,101,116,0,18,76,106,97,118,97,47,108,97,110,103,47,83,116,114,105,110,103,59,120,112,0,0,0,20,116,0,3,116,105,109};
        try (ByteArrayInputStream bai = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bai)) {
            Object o = ois.readObject();
            System.out.println(o);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对象序列化，写到文件
     * 文件 <- 文件输出流 <- 对象输出流 <- 对象
     */
    @Test
    public void object2File() {
        Student student = new Student("tim", 20);
        try (FileOutputStream fos = new FileOutputStream(new File("src/main/resources/output"));
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件反序列化，转成对象
     * 文件 -> 文件输入流 -> 对象输入流 -> 对象
     */
    @Test
    public void file2Object() {
        try (FileInputStream fis = new FileInputStream(new File("src/main/resources/output"));
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object o = ois.readObject();
            System.out.println(o);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}