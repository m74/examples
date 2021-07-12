package ru.com.m74.spring.aop.example;

import org.springframework.stereotype.Component;

@Component
public class ExampleBean {

    public String sayHello() {
        return "Hello";
    }
}
