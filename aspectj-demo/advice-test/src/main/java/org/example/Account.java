package org.example;

public class Account {
    int balance = 20;

    public boolean withdraw(int amount) {
        // exception
//        int i = 0/0;
        if (balance < amount) {
            return false;
        }
        balance = balance - amount;
        return true;
    }
}
