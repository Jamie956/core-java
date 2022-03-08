package com.cat.pattern.composite;

import java.util.List;

/**
 * 组合模式（Composite Pattern），又叫部分整体模式，
 * 依据树形结构来组合对象，用来表示部分以及整体层次。
 */
public class Client {
    public static void main(String[] args) {
        Employee CEO = new Employee("John", "CEO", 30000);

        Employee headSales = new Employee("Robert", "Head Sales", 20000);
        Employee headMarketing = new Employee("Michel", "Head Marketing", 20000);

        Employee clerk1 = new Employee("Laura", "Marketing", 10000);
        Employee clerk2 = new Employee("Bob", "Marketing", 10000);

        Employee salesExecutive1 = new Employee("Richard", "Sales", 10000);
        Employee salesExecutive2 = new Employee("Rob", "Sales", 10000);

        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        System.out.println(CEO);
        List<Employee> subordinates = CEO.getSubordinates();
        subordinates.forEach(e -> System.out.println("\t"+e));
        subordinates.forEach(e -> {
            List<Employee> subordinates1 = e.getSubordinates();
            subordinates1.forEach(x -> System.out.println("\t\t"+x));
        });
    }

}
