package org.example;

public aspect AccountAspect {
    // defined a pointcut, return true if call Account.withdraw and args not null and account instance exist
    pointcut callWithDraw123(int amount, Account acc):
            call(boolean withdraw(int)) && args(amount) && target(acc);

    // before advice, call if pointcut true
    before(int amount, Account acc): callWithDraw123(amount, acc) {
        System.out.println("before");
    }

    // around advice, call if pointcut true
    boolean around(int amount, Account acc): callWithDraw123(amount, acc) {
        System.out.println("around");
        return proceed(amount, acc);
    }

    // after advice, call if pointcut true
    after(int amount, Account balance): callWithDraw123(amount, balance) {
        System.out.println("after");
    }
}