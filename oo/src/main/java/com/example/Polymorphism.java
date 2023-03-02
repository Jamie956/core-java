package com.example;

public class Polymorphism {
    public static void main(String[] args) {
        Fruit fruit = new Apple();
        // fruit.eat() 就是多态的表现，父类引用指向子类对象
        fruit.eat();
    }
}

class Fruit {
    int num;
    public void eat() {
        System.out.println("eat fruit");
    }
}

class Apple extends Fruit {
    @Override
    public void eat() {
        super.num = 10;
        System.out.println("eat " + num + " apple");
    }
}