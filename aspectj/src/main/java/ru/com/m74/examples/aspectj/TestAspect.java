package ru.com.m74.examples.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Slf4j
public class TestAspect {

    @Around("execution(* TestController.hello(..))")
    public String advice(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed() + " Maxim!";
    }

    @Before("@annotation(Auditable)")
    public void logBefore() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info("Before: {}", request);
    }

    @After("@annotation(Auditable)")
    public void logAfter() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info("After: {}", request);
    }

//    @Around("@annotation(Auditable)")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        log.info("Around: " + request);
//        return pjp.proceed();
//    }
}
