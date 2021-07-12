package ru.com.m74.spring.aop.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping
    public String hello() {
        return "Hello";
    }
}
