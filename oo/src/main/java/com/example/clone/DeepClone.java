package com.example.clone;

import java.io.*;

/**
 * 深克隆，克隆对象的引用类型变量也被克隆
 * 对象 -> 对象流输出 -> 数组输出流 -> 数组输入流 -> 对象输出流
 */
public class DeepClone {
    public static void main(String[] args) throws IOException {
        DeepCloneObject user = new DeepCloneObject();
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(user);
            byteArrayOutputStream.flush();
            byte[] bytes = byteArrayOutputStream.toByteArray();

            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                 ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
                DeepCloneObject clone = (DeepCloneObject) objectInputStream.readObject();
                System.out.println(user == clone);
                // 克隆对象的引用类型变量也被克隆
                System.out.println(user.address == clone.address);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

class DeepCloneObject implements Serializable {
    private static final long serialVersionUID = -3307269962764425802L;
    public DeepCloneSubObject address;

    public DeepCloneObject() {
        this.address = new DeepCloneSubObject("ss");
    }
}


class DeepCloneSubObject implements Serializable {
    private static final long serialVersionUID = -4537716904357183030L;
    public String s;

    public DeepCloneSubObject(String s) {
        this.s = s;
    }
}