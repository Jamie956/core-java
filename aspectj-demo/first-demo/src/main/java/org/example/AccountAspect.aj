package org.example;

public aspect AccountAspect {
    final int MIN_BALANCE = 10;

    // defined a pointcut, return true if call Account.withdraw and args not null and account instance exist
    pointcut callWithDraw(int amount, Account acc):
            call(boolean Account.withdraw(int)) && args(amount) && target(acc);

    // before advice, call if pointcut true
    before(int amount, Account acc): callWithDraw(amount, acc) {
        System.out.println("before");
    }

    // around advice, call if pointcut true
    boolean around(int amount, Account acc):
            callWithDraw(amount, acc) {
        System.out.println("around");
        if (acc.balance < amount) {
            return false;
        }
        return proceed(amount, acc);
    }

    // after advice, call if pointcut true
    after(int amount, Account balance): callWithDraw(amount, balance) {
        System.out.println("after");
    }
}