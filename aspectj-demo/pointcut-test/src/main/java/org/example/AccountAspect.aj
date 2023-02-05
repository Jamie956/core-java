package org.example;

public aspect AccountAspect {
    // simple pointcut
    pointcut callWithDraw():call(* Account.withdraw(..));

    // before advice, call if pointcut true
    before(): callWithDraw() {
        System.out.println("before");
    }
}