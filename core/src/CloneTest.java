
import org.junit.Test;

import java.io.*;

public class CloneTest {
    static class Student implements Cloneable {
        public Address address;

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Student(Address address) {
            this.address = address;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            // TODO Auto-generated method stub
            return super.clone();
        }
    }

    static class User implements Serializable {
        private static final long serialVersionUID = -3307269962764425802L;
        private Integer id;
        private String name;
        public Address address;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public User(Integer id, String name, Address address) {
            this.id = id;
            this.name = name;
            this.address = address;
        }

        public User() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public User(Address ad) {
            this.address = ad;
        }
    }

    static class Address implements Serializable {
        private static final long serialVersionUID = -4537716904357183030L;
        public String stress;

        public Address() {
        }

        public Address(String stress) {
            this.stress = stress;
        }

        public String getStress() {
            return stress;
        }

        public void setStress(String stress) {
            this.stress = stress;
        }
    }

    /**
     * 深克隆，引用类型也会被克隆
     * 对象 -> 对象流输出 -> 数组输出流 -> 数组输入流 -> 对象输出流
     */
    @Test
    public void deepClone() throws IOException {
        User user = new User(new Address("stress1"));
        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)
        ) {
            objectOutputStream.writeObject(user);
            byteArrayOutputStream.flush();
            byte[] bytes = byteArrayOutputStream.toByteArray();

            try (
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)
            ) {
                User clone = (User) objectInputStream.readObject();
                System.out.println(user == clone);
                //全局引用类型变量也能克隆
                System.out.println(user.getAddress() == clone.getAddress());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
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
