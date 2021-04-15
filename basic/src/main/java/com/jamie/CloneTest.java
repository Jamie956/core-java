package com.jamie;

import com.jamie.entity.Address;
import com.jamie.entity.Student;
import com.jamie.entity.User;
import org.junit.Test;

import java.io.*;

public class CloneTest {

    /**
     * 深克隆，引用类型也会被克隆
     */
    @Test
    public void deepClone() {
        try {
            User user = new User(new Address("stress1"));
            User clone = (User) deepClone(user);

            System.out.println(user == clone);
            System.out.println(user.getAddress() == clone.getAddress());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对象 -> 字节输出流 -> 对象
     * @param object
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deepClone(Object object) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        return ois.readObject();
    }

    /**
     * 浅克隆，不克隆引用类型
     * 对象实现Cloneable，重写clone 方法，调用父类方法
     */
    @Test
    public void shallowClone() {
        try {
            Student student = new Student(new Address("stress"));
            Student clone = (Student) student.clone();

            System.out.println(student == clone);
            System.out.println(student.getAddress() == clone.getAddress());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }


}
