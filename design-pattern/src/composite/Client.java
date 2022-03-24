package composite;

import composite.Employee;

/**
 * 组合模式 / 部分整体模式
 */
public class Client {
    public static void main(String[] args) {
        Employee CEO = new Employee("John", "CEO");

        Employee headSales = new Employee("Robert", "Head Sales");
        Employee headMarketing = new Employee("Michel", "Head Marketing");

        CEO.add(headSales);
        CEO.add(headMarketing);
    }
}
