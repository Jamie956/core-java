package com.jamie.deser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class StudentSerializable {
    private static final String PATH = "studentserializable.txt";

    public StudentSerializable(String name, Integer age) {
        Student s = new Student(name, age);
        File file = new File(PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // student实例序列化过程
        try {
            //创建文件输出流对象
            FileOutputStream fos = new FileOutputStream(file);
            // 将文件输出流对象转化为对象输出流对象
            ObjectOutputStream obj = new ObjectOutputStream(fos);
            // 将student实例写入对象输出流
            obj.writeObject(s);
            // 刷新缓冲区
            obj.flush();
            // 关闭对象输出流对象
            obj.close();
            // 关闭文件输出流对象
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


