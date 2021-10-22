package com.jamie;

import com.jamie.component.AppConfig;
import com.jamie.component.Book;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCTest {

    /**
     * 扫描@Component，创建bean 并获取实例（变量有autowired注解的component 也会被实例化）
     */
    @Test
    public void scanComponentAnnotationBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.jamie.component");
        context.refresh();
        context.getBean(Book.class);
        context.close();
    }

    /**
     * 扫描@Configuration，使用有@Bean注解的方法名获取实例
     */
    @Test
    public void getConfigurationBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = (User) context.getBean("user");
        System.out.println(user.getAge());
        context.close();
    }

    /**
     * 读取xml，获取bean
     */
    @Test
    public void getXmlBean(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ioc-bean.xml");
        User user = (User) context.getBean("user");
        System.out.println(user.getName());
        context.close();
    }

    /**
     * bean 的生命周期
     */
    @Test
    public void beanLifeCycle() {
        System.out.println("获取xml context 配置之前");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-life.xml");
        System.out.println("获取xml context 配置之后");

        Person person = (Person) context.getBean("person");
        System.out.println(person.getName());
        System.out.println("关闭容器之前");
        context.close();
        System.out.println("关闭容器之后");

    }
}