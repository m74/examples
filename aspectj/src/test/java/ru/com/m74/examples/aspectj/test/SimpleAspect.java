package ru.com.m74.examples.aspectj.test;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class SimpleAspect {

    @Around("execution(* ru.com.m74.examples.aspectj.test.SimpleObject.test())")
    public boolean advice(ProceedingJoinPoint pjp) throws Throwable {
        log.info("!!! before");
        boolean result = (boolean) pjp.proceed();
        log.info("!!! after");
        return !result;
    }

}
