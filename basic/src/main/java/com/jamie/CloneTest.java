package com.jamie;

import com.jamie.util.DeSerUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.Serializable;

public class CloneTest {
    @Data
    @AllArgsConstructor
    static class Student implements Cloneable {
        public Address address;

        @Override
        public Object clone() throws CloneNotSupportedException {
            // TODO Auto-generated method stub
            return super.clone();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class User implements Serializable {
        private static final long serialVersionUID = -3307269962764425802L;
        private Integer id;
        private String name;
        public Address address;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public User(Address ad) {
            this.address = ad;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Address implements Serializable {
        private static final long serialVersionUID = -4537716904357183030L;
        public String stress;
    }

    /**
     * 深克隆，引用类型也会被克隆
     */
    @Test
    public void deepClone() {
        try {
            User user = new User(new Address("stress1"));
            byte[] bytes = DeSerUtil.object2ByteArray(user);
            User clone = (User) DeSerUtil.byte2Object(bytes);

            System.out.println(user == clone);
            System.out.println(user.getAddress() == clone.getAddress());

        } catch (Exception e) {
            e.printStackTrace();
        }
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
