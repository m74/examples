package ru.com.m74.examples.aspectj;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    @Auditable
    public String hello() {
        return "Hello";
    }
}
