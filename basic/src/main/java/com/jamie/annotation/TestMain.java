package com.jamie.annotation;

public class TestMain {
    /**
     * 获取注解的值
     */
    public static void main(String[] args) {
        Student student = new Student();
        Role studentAnno = student.getClass().getAnnotation(Role.class);
        System.out.println(studentAnno.value());

        Teacher teacher = new Teacher();
        Role teacherAnno = teacher.getClass().getAnnotation(Role.class);
        System.out.println(teacherAnno.value());
    }
}

@Role(value = "admin")
class Student {
}

@Role(value = "user")
class Teacher {
}