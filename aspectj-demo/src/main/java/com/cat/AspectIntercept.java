package com.cat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Aspect 切面
 * Order 执行顺序
 * Component spring bean
 * @author zjm
 */
@Aspect
@Order(5)
@Component
public class AspectIntercept {

    /**
     * 方法切点
     */
    @Pointcut("execution(public void com.cat.ProxyTarget.hi())")
    public void pointcut() {
    }

    /**
     * 注解切点
     */
    @Pointcut("@annotation(CheckUser)")
    public void CheckUserPointCut(){

    }

    /**
     * 注解切点的前置处理
     */
    @Before("CheckUserPointCut()")
    public void checkBefore(JoinPoint joinPoint) {
        System.out.println("annotation pointcut before");
    }

    /**
     * 方法切点的前置处理
     */
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("doBefore");
    }

    /**
     * 方法切点的环绕处理
     */
    @Around("pointcut()")
    public Object filterModelInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around 前");
        Object proceed = joinPoint.proceed();
        System.out.println("around 后");
        return proceed;
    }

}