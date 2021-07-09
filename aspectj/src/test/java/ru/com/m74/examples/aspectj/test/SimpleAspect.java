package ru.com.m74.examples.aspectj.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class SimpleAspect {

    @Around("execution(* ru.com.m74.examples.aspectj.test.AspectTestObject.test())")
    public boolean advice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("!!! before");
        boolean result = (boolean) pjp.proceed();
        System.out.println("!!! after");
        return !result;
    }

}
