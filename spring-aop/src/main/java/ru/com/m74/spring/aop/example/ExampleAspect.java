package ru.com.m74.spring.aop.example;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExampleAspect {

    @Pointcut("execution( * ExampleBean.sayHello(..))")
    private void pointcut() {
    }

    @Pointcut("execution( * ExampleController.*(..))")
    private void controller() {
    }

    @Around("pointcut()")
    public Object beforeAdvice(ProceedingJoinPoint pjp) throws Throwable {
        String str = (String) pjp.proceed();
        log.info("Original value: {}", str);
        return str + " Maxim!";
    }

    @Around("controller()")
    public Object adviceController(ProceedingJoinPoint pjp) throws Throwable {
        String str = (String) pjp.proceed();
        log.info("Original value: {}", str);
        return str + " Maxim!";
    }
}
